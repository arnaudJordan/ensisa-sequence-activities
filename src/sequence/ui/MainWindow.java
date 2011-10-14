package sequence.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.SequenceView;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Config config;
	private List<SequenceView> sequenceViews;
	private JPanel mainPane;
	private JSlider scaleSlider;

	public MainWindow(String title) throws HeadlessException {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setPreferredSize(new Dimension(800, 600));

		setConfig(new Config());
		try {
			setConfig(getConfig().deserialize());
		}catch(Exception e){
			e.printStackTrace();
		}

		this.setJMenuBar(new MenuBar(this));

		this.sequenceViews = new ArrayList<SequenceView>();
		this.mainPane=new JPanel();
		
		this.mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.PAGE_AXIS));
		JScrollPane scrollPane = new JScrollPane(this.mainPane);
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane);
		
		this.setupScaleSlider();

		this.init();
		this.pack();
		this.setVisible(true);
	}
	public void init()
	{
		File lastOpenedFile = getConfig().getLastOpenedFile();
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
				addSequence(new SequenceView(sequence));

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
						Component[] components = current.getComponents();
						for(int i=0; i<components.length ; i++)
							((ActivityRenderingModel)((ActivityView)components[i]).getRenderingModel()).setScale((float)(scaleSlider.getValue()) / 100);
					}
					mainPane.revalidate();
				}
			}
		});

		scaleSliderPane.add(this.scaleSlider);
		this.add(scaleSliderPane, BorderLayout.PAGE_END);
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
		this.sequenceViews.add(sequenceView);
		mainPane.add(sequenceView);
		pack();
		setVisible(true);
	}


}
