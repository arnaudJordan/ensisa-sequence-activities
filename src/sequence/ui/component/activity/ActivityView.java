package sequence.ui.component.activity;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.controller.ActivityMenuColorController;
import sequence.ui.component.activity.controller.ActivityMenuEditController;

public class ActivityView extends View {
	private static final long serialVersionUID = 1L;
	public JPopupMenu popup;

	public ActivityView(Model model) {
		super(model);
		setRenderer(new ActivityContractedRenderer(this));
		setRenderingModel(new ActivityRenderingModel());
		setToolTipText(((Activity) model).toToolTip());
		popup = new JPopupMenu();
	    JMenuItem colorItem = new JMenuItem("Color");
	    colorItem.addActionListener(new ActivityMenuColorController(model, this));
	    popup.add(colorItem);
	    JMenuItem editItem = new JMenuItem("Edit");
	    editItem.addActionListener(new ActivityMenuEditController(model, this));
	    popup.add(editItem);
	}
}
