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
import javax.swing.SwingConstants;
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
	private final List<SequenceContainer> sequenceContainers;
	private final Processor processor;

	/* Window elements */
	private final MDIDesktopPane mainPane;
	private JSlider scaleSlider;
	private JTextField thresholdField;

	public MainWindow(final String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowController(this));

		processor = new SafeProcessor();
		try {
			setConfig(Config.deserialize());
		} catch (final Exception e) {
			setConfig(new Config());
		}
		setPreferredSize(config.getWindowSize());
		this.setLocation(config.getWindowLocation());
		setLookAndFeel();

		sequenceContainers = new ArrayList<SequenceContainer>();
		mainPane = new MDIDesktopPane();

		setupScaleSlider();
		setupThresholdField();

		setJMenuBar(new MenuBar(this));

		final JScrollPane scrollPane = new JScrollPane(getMainPane());
		scrollPane.setWheelScrollingEnabled(true);
		this.add(scrollPane);

		pack();
		setVisible(true);
	}

	private void setupScaleSlider() {
		scaleSlider = new JSlider(SwingConstants.HORIZONTAL, 100, 300, 100);
		final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(100), new JLabel("100%"));
		labelTable.put(new Integer(200), new JLabel("200%"));
		labelTable.put(new Integer(300), new JLabel("300%"));
		scaleSlider.setLabelTable(labelTable);
		scaleSlider.setMajorTickSpacing(100);
		scaleSlider.setPaintLabels(true);
		scaleSlider.setPaintTicks(true);
		scaleSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent changeEvent) {
				EventDispatcher.scaleChanged((float) (scaleSlider.getValue()) / 100);
			}
		});
	}

	private void setupThresholdField() {
		thresholdField = new JTextField("0", 2);
		thresholdField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				updateThresholdField(actionEvent);
			}
		});
		thresholdField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(final FocusEvent focusEvent) {
				updateThresholdField(focusEvent);
			}

			@Override
			public void focusGained(final FocusEvent focusEvent) {
			}
		});
	}

	private void updateThresholdField(final AWTEvent e) {
		final Object source = e.getSource();
		final JTextField s = (JTextField) source;
		for (final SequenceContainer current : sequenceContainers) {
			for (final SequenceContainer subSequence : current.getChilds()) {
				((SubSequenceRenderingModel) subSequence.getView()
						.getRenderingModel()).setDurationThreshold(Integer
						.parseInt(s.getText()));
			}
		}
		SubSequenceRenderingModel.CURRENT_DURATION_THRESHOLD = Integer
				.parseInt(s.getText());
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(config.getStyle());
		} catch (final Exception e) {
		}
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(final Config config) {
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

	public void add(final Sequence sequence) {
		getProcessor().Do(new AddSequence(sequence, this));
	}

	public void remove(final SequenceContainer sequenceContainer) {
		getProcessor().Do(
				new RemoveSequence((Sequence) sequenceContainer.getView()
						.getModel(), this));
	}

	public void remove(final Sequence sequence) {
		for (final SequenceContainer sc : sequenceContainers) {
			if (sc.getView().getModel().equals(sequence)) {
				sequenceContainers.remove(sc);
				mainPane.remove(sc);
				config.removeOpenedFile(sequence.getFile());
				try {
					Config.serialize(config);
				} catch (final IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public Processor getProcessor() {
		return processor;
	}

	public static void main(final String[] args) {
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow("Sequence activities");
			}
		});
	}

	public MDIDesktopPane getMainPane() {
		return mainPane;
	}

	public View getSequenceContainers(final Sequence selectedSequence) {
		for (final SequenceContainer s : sequenceContainers) {
			if (s.getView().getModel().equals(selectedSequence))
				return s.getView();
		}
		return null;
	}
}