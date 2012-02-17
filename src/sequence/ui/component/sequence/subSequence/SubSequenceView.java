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

public class SubSequenceView extends View implements ThresholdListener {
	private static final long serialVersionUID = 1L;
	private final View summarizedView;
	private final JPopupMenu popup;

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

	public View getSummarizedView() {
		return summarizedView;
	}

	public JPopupMenu getPopup() {
		return popup;
	}
	
	public void addThresholdListener(final ThresholdListener tl) {
		if (tl == null)
			return;
		OptionEventDispatcher.add(tl);
	}

	public void removeThresholdListener(final ThresholdListener tl) {
		OptionEventDispatcher.remove(tl);
	}

	@Override
	public void modelChanged(final Model m) {
		removeAll();
		((SubSequenceRenderer) getRenderer()).initialize();
		((SequenceContainer) getSummarizedView().getParent()).revalidate();
		getSummarizedView().getParent().repaint();
	}

	@Override
	public void thresholdChanged(final int threshold) {
		((SubSequenceRenderingModel) getRenderingModel()).setDurationThreshold(threshold);
	}
}
