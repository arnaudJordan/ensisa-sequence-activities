package sequence.ui.component.sequence.summarizedSequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;

/**
 * The Class SummarizedSequenceView.
 */
public class SummarizedSequenceView extends View {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The selected activities. */
	private final List<Activity> selectedActivities;
	
	/** The popup. */
	private final JPopupMenu popup;

	/**
	 * Instantiates a new summarized sequence view.
	 *
	 * @param model the model
	 */
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

	/**
	 * Gets the popup.
	 *
	 * @return the popup
	 */
	public JPopupMenu getPopup() {
		return popup;
	}

	/**
	 * Gets the selected activities.
	 *
	 * @return the selected activities
	 */
	public List<Activity> getSelectedActivities() {
		return selectedActivities;
	}
	
	/**
	 * Gets the sorted selected activities.
	 *
	 * @return the sorted selected activities
	 */
	public List<Activity> getSortedSelectedActivities() {
		if (!selectedActivities.isEmpty()) {
			if (selectedActivities.size() == 1) {
				final Activity selectedActivity = selectedActivities.get(0);
				final Sequence sequence = (Sequence) getModel();
				return sequence.getActivitiesInPhase(sequence
						.getPhaseOfActivity(selectedActivity));
			} else {
				Collections.sort(selectedActivities,
						new Comparator<Activity>() {
							@Override
							public int compare(final Activity a1,
									final Activity a2) {
								return a1.getId() < a2.getId() ? -1 : 1;
							}
						});
			}
		}
		return selectedActivities;
	}
}
