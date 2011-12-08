package sequence.ui.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.processor.AddSequence;
import sequence.processor.Processor;
import sequence.processor.RemoveSequence;
import sequence.processor.SafeProcessor;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceRenderingModel;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.utilities.Config;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Config config;
	
	/* Model */
	private List<SequenceContainer> sequenceContainers;
	private Processor processor;
	
	/* Window elements */
	private JPanel mainPane;
	private JSlider scaleSlider;
	private JTextField thresholdField;

	public MainWindow(String title) throws HeadlessException {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setPreferredSize(new Dimension(800, 600));

		this.processor=new SafeProcessor();
		try {
			setConfig(Config.deserialize());
		}catch(Exception e){
			e.printStackTrace();
			setConfig(new Config());
		}

		this.setJMenuBar(new MenuBar(this));

		this.sequenceContainers = new ArrayList<SequenceContainer>();
		this.mainPane=new JPanel();
		
		this.getMainPane().setLayout(new BoxLayout(this.getMainPane(), BoxLayout.PAGE_AXIS));
		JScrollPane scrollPane = new JScrollPane(this.getMainPane());
		scrollPane.setWheelScrollingEnabled(true);
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane);
		
		this.setupScaleSlider();
		this.setupThresholdField();
		
		this.init();
		this.pack();
		this.setVisible(true);
	}
	public void init()
	{
		
		String laf = getConfig().getStyle();
		if(laf!=null)
		try {
			UIManager.setLookAndFeel(laf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		else
		{
			for (UIManager.LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(lf.getName()))
					try
					{
						UIManager.setLookAndFeel(lf.getClassName());
					}
					catch (Exception e) {}
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
		
		List<File> lastOpenedFiles = getConfig().getLastOpenedFiles();
		for(File file:lastOpenedFiles)
		{
			try{
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				SequenceHandler sequenceHandler = new SequenceHandler(file);
				parser.parse(file, sequenceHandler);				
				
				Sequence sequence = sequenceHandler.getSequence();
				addSequence(sequence);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	private void setupScaleSlider()
	{
		JPanel scaleSliderPane = new JPanel();
		
		this.scaleSlider = new JSlider(JSlider.HORIZONTAL,100,300,100);
		this.scaleSlider.setMajorTickSpacing(100);
		this.scaleSlider.setPaintLabels(true);
		this.scaleSlider.setPaintTicks(true);
		this.scaleSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent changeEvent)
			{
				Object source = changeEvent.getSource();
				JSlider s = (JSlider) source;
				if (!s.getValueIsAdjusting());
				{
					for(SequenceContainer current : sequenceContainers) {
						for(JPanel subSequence : current.getSubSequenceContainers()) {
							for(int i=0; i<subSequence.getComponentCount() ; i++) {
								if(subSequence.getComponent(i) instanceof SubSequenceView) {
									((ActivityRenderingModel)((ActivityView)subSequence.getComponent(i)).getRenderingModel()).setScale((float)(scaleSlider.getValue()) / 100);
									((JComponent)subSequence.getComponent(i)).revalidate();
								}
							}
						}
					}
				}
			}
		});

		scaleSliderPane.add(this.scaleSlider);
		this.add(scaleSliderPane, BorderLayout.PAGE_END);
	}
	
	private void setupThresholdField()
	{
		JPanel thresholdFieldPane = new JPanel();
		JLabel thresholdFieldLabel = new JLabel("Minimal duration threshold : ");
		
		this.thresholdField = new JTextField("0", 2);
		this.thresholdField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				Object source = actionEvent.getSource();
				JTextField s = (JTextField) source;
				for(SequenceContainer current : sequenceContainers) {
					for(SubSequenceContainer subSequence : current.getSubSequenceContainers()) {
						((SubSequenceRenderingModel)subSequence.getSubSequenceView().getRenderingModel()).setDurationThreshold(Integer.parseInt(s.getText()));
						current.revalidate();
					}
				}
			}
		});
		thresholdFieldPane.add(thresholdFieldLabel);
		thresholdFieldPane.add(this.thresholdField);
		this.add(thresholdFieldPane, BorderLayout.PAGE_START);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public List<SequenceContainer> getSequenceContainers() {
		return sequenceContainers;
	}
	public void addSequence(Sequence sequence)
	{
		getProcessor().Do(new AddSequence(sequence, this));
	}
	public void removeSequence(SequenceContainer sequenceContainer)
	{
		getProcessor().Do(new RemoveSequence((Sequence) sequenceContainer.getSummarizedSequenceView().getModel(), this));
	}
	public void removeSequence(Sequence model) {
		for(SequenceContainer sc : sequenceContainers)
		{
			if(sc.getSummarizedSequenceView().getModel().equals(model))
			{
				this.sequenceContainers.remove(sc);
		        this.mainPane.remove(sc);
		        config.removeOpenedFile(model.getFile());
		        try {
					Config.serialize(config);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        break;
			}
		}		
	}
	
	public Processor getProcessor() {
		return processor;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new MainWindow("Sequence activities");
			}
		});
	}
	public JPanel getMainPane() {
		return mainPane;
	}
	
}