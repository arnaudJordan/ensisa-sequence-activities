package sequence.ui.window;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sequence.model.Sequence;
import sequence.mvc.View;
import sequence.processor.Processor;
import sequence.processor.SafeProcessor;
import sequence.processor.command.AddSequence;
import sequence.processor.command.RemoveSequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceRenderingModel;
import sequence.ui.utilities.MDIDesktopPane;
import sequence.ui.utilities.WindowController;
import sequence.utilities.Config;
import sequence.utilities.EventDispatcher;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Config config;
	
	/* Model */
	private List<SequenceContainer> sequenceContainers;
	private Processor processor;
	
	/* Window elements */
	private MDIDesktopPane mainPane;
	private JSlider scaleSlider;
	private JTextField thresholdField;

	public MainWindow(String title) throws HeadlessException {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowController(this));

		this.processor=new SafeProcessor();
		try {
			setConfig(Config.deserialize());
		}catch(Exception e){
			setConfig(new Config());
		}
		this.setPreferredSize(config.getWindowSize());
		this.setLocation(config.getWindowLocation());
		this.setLookAndFeel();
		
		this.sequenceContainers = new ArrayList<SequenceContainer>();
		this.mainPane = new MDIDesktopPane();
		
		this.setupScaleSlider();
		this.setupThresholdField();
		
		this.setJMenuBar(new MenuBar(this));
		
		JScrollPane scrollPane = new JScrollPane(this.getMainPane());
		scrollPane.setWheelScrollingEnabled(true);
		this.add(scrollPane);

		this.pack();
		this.setVisible(true);
	}
	
	private void setupScaleSlider() {
		this.scaleSlider = new JSlider(JSlider.HORIZONTAL, 100, 300, 100);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(100), new JLabel("100%"));
		labelTable.put(new Integer(200), new JLabel("200%"));
		labelTable.put(new Integer(300), new JLabel("300%"));
		this.scaleSlider.setLabelTable(labelTable);
		this.scaleSlider.setMajorTickSpacing(100);
		this.scaleSlider.setPaintLabels(true);
		this.scaleSlider.setPaintTicks(true);
		this.scaleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				EventDispatcher.scaleChanged((float) (scaleSlider.getValue()) / 100);
			}
		});
	}

	private void setupThresholdField() {
		this.thresholdField = new JTextField("0", 2);
		this.thresholdField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				updateThresholdField(actionEvent);
			}
		});
		this.thresholdField.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent focusEvent) {
				updateThresholdField(focusEvent);
			}
			public void focusGained(FocusEvent focusEvent) {
			}
		});
	}
	
	private void updateThresholdField(AWTEvent e) {
		Object source = e.getSource();
		JTextField s = (JTextField) source;
		for (SequenceContainer current : sequenceContainers) {
			for (SequenceContainer subSequence : current.getChilds()) {
				((SubSequenceRenderingModel) subSequence.getView()
						.getRenderingModel())
						.setDurationThreshold(Integer.parseInt(s
								.getText()));
			}
		}
		SubSequenceRenderingModel.CURRENT_DURATION_THRESHOLD = Integer
				.parseInt(s.getText());
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(config.getStyle());
		} catch (Exception e) {
		}
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
	public JSlider getScaleSlider() {
		return scaleSlider;
	}

	public JTextField getThresholdField() {
		return thresholdField;
	}

	public void add(Sequence sequence)
	{
		getProcessor().Do(new AddSequence(sequence, this));
	}
	public void remove(SequenceContainer sequenceContainer)
	{
		getProcessor().Do(new RemoveSequence((Sequence) sequenceContainer.getView().getModel(), this));
	}
	public void remove(Sequence sequence) {
		for(SequenceContainer sc : sequenceContainers)
		{
			if(sc.getView().getModel().equals(sequence))
			{
				this.sequenceContainers.remove(sc);
		        this.mainPane.remove(sc);
		        config.removeOpenedFile(sequence.getFile());
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
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new MainWindow("Sequence activities");
			}
		});
	}
	public MDIDesktopPane getMainPane() {
		return mainPane;
	}
	public View getSequenceContainers(Sequence selectedSequence) {
		for(SequenceContainer s : sequenceContainers)
		{
			if(s.getView().getModel().equals(selectedSequence))
				return s.getView();
		}
		return null;
	}
}