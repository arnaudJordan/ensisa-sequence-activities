package sequence.ui.component.sequence.subSequence;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuInfoController;
import sequence.utilities.OptionEventDispatcher;
import sequence.utilities.ThresholdListener;

/**
 * The Class SubSequenceView.
 */
public class SubSequenceView extends View implements ThresholdListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The summarized view. */
	private final View summarizedView;
	
	/** The popup. */
	private final JPopupMenu popup;

	/**
	 * Instantiates a new sub sequence view.
	 *
	 * @param model the model
	 * @param summarizedView the summarized view
	 */
	public SubSequenceView(final Model model, final View summarizedView) {
		super(model);
		setRenderingModel(new SubSequenceRenderingModel());
		this.summarizedView = summarizedView;
		setRenderer(new SubSequenceRenderer(this));
		addThresholdListener(this);
		popup = new JPopupMenu();
		final JMenuItem exportMenu = new JMenuItem("Export");
		exportMenu.addActionListener(new SubSequenceMenuExportController(
				getModel(), this));
		popup.add(exportMenu);
		final JMenuItem infoItem = new JMenuItem("Info");
		infoItem.addActionListener(new SubSequenceMenuInfoController(
				getModel(), this));
		popup.add(infoItem);
	}

	/**
	 * Gets the summarized view.
	 *
	 * @return the summarized view
	 */
	public View getSummarizedView() {
		return summarizedView;
	}

	/**
	 * Gets the popup.
	 *
	 * @return the popup
	 */
	public JPopupMenu getPopup() {
		return popup;
	}
	
	/**
	 * Adds the threshold listener.
	 *
	 * @param tl the tl
	 */
	public void addThresholdListener(final ThresholdListener tl) {
		if (tl == null)
			return;
		OptionEventDispatcher.add(tl);
	}

	/**
	 * Removes the threshold listener.
	 *
	 * @param tl the tl
	 */
	public void removeThresholdListener(final ThresholdListener tl) {
		OptionEventDispatcher.remove(tl);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.View#modelChanged(sequence.mvc.Model)
	 */
	@Override
	public void modelChanged(final Model m) {
		removeAll();
		((SubSequenceRenderer) getRenderer()).initialize();
		((SequenceContainer) getSummarizedView().getParent()).revalidate();
		getSummarizedView().getParent().repaint();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.ThresholdListener#thresholdChanged(int)
	 */
	@Override
	public void thresholdChanged(final int threshold) {
		((SubSequenceRenderingModel) getRenderingModel()).setDurationThreshold(threshold);
	}
}
