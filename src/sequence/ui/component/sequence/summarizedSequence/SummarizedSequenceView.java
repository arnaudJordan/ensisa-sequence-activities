package sequence.ui.component.sequence.summarizedSequence;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;

public class SummarizedSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private Container parent;
	private List<Activity> selectedActivities;
	private JPopupMenu popup;

	public SummarizedSequenceView(Model model, Container parent) {
		super(model);
		this.parent = parent;
		this.selectedActivities = new ArrayList<Activity>();		
		setRenderer(new SummarizedSequenceRenderer(this));
		popup = new JPopupMenu();
	    JMenuItem exportItem = new JMenuItem("Develop");
	    exportItem.addActionListener(new SummarizedSequenceController(getModel(), this));
	    popup.add(exportItem);
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}

	public Container getParent() {
		return parent;
	}

	public List<Activity> getSelectedActivities() {
		return selectedActivities;
	}
}
