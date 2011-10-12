package sequence.ui.component.activity;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityView extends View {

	public JPopupMenu popup;

	public ActivityView(Model model) {
		super(model);
		setRenderer(new ActivityRenderer(this));
		setRenderingModel(new ActivityRenderingModel());
		popup = new JPopupMenu();
	    JMenuItem colorItem = new JMenuItem("Color");
	    colorItem.addActionListener(new ActivityMenuItemController(model, this));
	    popup.add(colorItem);
	}
}
