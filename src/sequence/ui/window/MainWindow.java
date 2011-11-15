package sequence.ui.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import sequence.processor.Processor;
import sequence.processor.SafeProcessor;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.SequenceController;
import sequence.ui.component.sequence.SequenceRenderingModel;
import sequence.ui.component.sequence.SequenceView;
import sequence.utilities.Config;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Config config;
	
	/* Model */
	private List<SequenceView> sequenceViews;
	private List<SequenceContainer> sequenceContainer;
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
		setConfig(new Config());
		try {
			setConfig(getConfig().deserialize());
		}catch(Exception e){
			e.printStackTrace();
		}

		this.setJMenuBar(new MenuBar(this));

		this.sequenceViews = new ArrayList<SequenceView>();
		this.sequenceContainer = new ArrayList<SequenceContainer>();
		this.mainPane=new JPanel();
		
		this.mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.PAGE_AXIS));
		JScrollPane scrollPane = new JScrollPane(this.mainPane);
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
		
		File[] lastOpenedFiles = getConfig().getLastOpenedFiles();
		for(int i=0; i <lastOpenedFiles.length;i++)
		{
			if(lastOpenedFiles[i]==null)
				return;
			try{
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				SequenceHandler sequenceHandler = new SequenceHandler();
				parser.parse(lastOpenedFiles[i], sequenceHandler);

				Sequence sequence = sequenceHandler.getSequence();
				SequenceView view= new SequenceView(sequence);
				new SequenceController(sequence, view);
				addSequence(view);
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
					for(SequenceView current : sequenceViews) {
						for(int i=0; i<current.getComponentCount() ; i++) {
							((ActivityRenderingModel)((ActivityView)current.getComponent(i)).getRenderingModel()).setScale((float)(scaleSlider.getValue()) / 100);
							((JComponent)current.getComponent(i)).revalidate();
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
				for(SequenceView current : sequenceViews) {
					((SequenceRenderingModel)current.getRenderingModel()).setDurationThreshold(Integer.parseInt(s.getText()));
					current.revalidate();
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

	public List<SequenceView> getSequence() {
		return sequenceViews;
	}

	public void setSequence(List<SequenceView> sequenceViews) {
		this.sequenceViews = sequenceViews;
	}
	public void addSequence(SequenceView sequenceView)
	{
		SequenceContainer sc = new SequenceContainer(sequenceView, this);
		this.sequenceViews.add(sequenceView);
		this.sequenceContainer.add(sc);
		mainPane.add(sc);
		this.validate();
		pack();
		setVisible(true);
	}
	public void removeSequence(SequenceContainer sequenceContainer)
	{
		this.sequenceContainer.remove(sequenceContainer);
		this.sequenceViews.remove(sequenceContainer.getSequenceView());
		this.mainPane.remove(sequenceContainer);
		this.pack();
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

}
