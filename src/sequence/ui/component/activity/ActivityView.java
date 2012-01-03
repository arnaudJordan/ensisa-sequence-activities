package sequence.ui.component.activity;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.controller.ActivityMenuColorController;
import sequence.ui.component.activity.controller.ActivityMenuEditController;
import sequence.ui.component.activity.controller.ActivityMenuStripedController;
import sequence.utilities.Scaleable;
import sequence.utilities.Timeable;

public class ActivityView extends View implements Timeable, Scaleable {
	private static final long serialVersionUID = 1L;
	public JPopupMenu popup;
	private boolean selected;

	public ActivityView(Model model) {
		super(model);
		setRenderer(new ActivityContractedRenderer(this));
		setRenderingModel(new ActivityRenderingModel());
		popup = new JPopupMenu();
	    JMenuItem colorItem = new JMenuItem("Color");
	    colorItem.addActionListener(new ActivityMenuColorController(model, this));
	    popup.add(colorItem);
	    JMenuItem stripedItem = new JMenuItem("Striped");
	    stripedItem.addActionListener(new ActivityMenuStripedController(model, this));
	    popup.add(stripedItem);
	    JMenuItem editItem = new JMenuItem("Edit");
	    editItem.addActionListener(new ActivityMenuEditController(model, this));
	    popup.add(editItem);
	    selected = true;
	}
	
	public void select() {
		selected = true;
		((ActivityRenderingModel) getRenderingModel()).setOpaque();
	}
	
	public void deselect() {
		selected = false;
		((ActivityRenderingModel) getRenderingModel()).setTransparent();
	}

	public boolean isSelected() {
		return selected;
	}

	@Override
	public int getStartTime() {
		return ((Activity) getModel()).getActivitytime().getStartTime();
	}

	@Override
	public int getStopTime() {
		return ((Activity) getModel()).getActivitytime().getStopTime();
	}

	@Override
	public int getDuration() {
		return ((Activity) getModel()).getActivitytime().getDuration();
	}

	@Override
	public float getHScale() {
		return ((ActivityRenderingModel) getRenderingModel()).getHScale();
	}

	@Override
	public float getVScale() {
		return ((ActivityRenderingModel) getRenderingModel()).getVScale();
	}
	
	public void modelChanged(Model m) {
		setToolTipText(((Activity) getModel()).toToolTip());
		repaint();
	}
}
