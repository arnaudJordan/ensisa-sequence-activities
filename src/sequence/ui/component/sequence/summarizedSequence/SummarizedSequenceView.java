package sequence.ui.component.sequence.summarizedSequence;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;

public class SummarizedSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private final List<Activity> selectedActivities;
	private final JPopupMenu popup;

	public SummarizedSequenceView(final Model model) {
		super(model);
		selectedActivities = new ArrayList<Activity>();
		setRenderer(new SummarizedSequenceRenderer(this));
		popup = new JPopupMenu();
		final JMenuItem exportItem = new JMenuItem("Develop");
		exportItem.addActionListener(new SummarizedSequenceController(
				getModel(), this));
		final JMenuItem exportMenu = new JMenuItem("Export");
		exportMenu.addActionListener(new SubSequenceMenuExportController(
				getModel(), this));
		popup.add(exportMenu);
		popup.add(exportItem);
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public List<Activity> getSelectedActivities() {
		return selectedActivities;
	}
}
