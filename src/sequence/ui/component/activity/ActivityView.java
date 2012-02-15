package sequence.ui.component.activity;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.controller.ActivityMenuColorController;
import sequence.ui.component.activity.controller.ActivityMenuDeleteController;
import sequence.ui.component.activity.controller.ActivityMenuEditController;
import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.utilities.BackgroundListener;
import sequence.utilities.EventDispatcher;
import sequence.utilities.Scaleable;
import sequence.utilities.Timeable;

public class ActivityView extends View implements BackgroundListener, Scaleable, Timeable {
	private static final long serialVersionUID = 1L;	
	public JPopupMenu popup;
	private boolean selected;
	private ActivityView associatedActivity;

	public ActivityView(Model model) {
		super(model);
		setRenderer(new ActivityRenderer(this));
		setRenderingModel(new ActivityRenderingModel());
		addBackgroundListener(this);
	}
	
	public ActivityView(ActivityView activityView) {
		super(activityView.getModel());
		setRenderer(new ActivityRenderer(this));
		setRenderingModel(new ActivityRenderingModel(((ActivityRenderingModel) activityView.getRenderingModel()).getColor()));
		addBackgroundListener(this);
		activityView.setAssociatedActivity(this);
		popup = new JPopupMenu();
	    JMenuItem colorItem = new JMenuItem("Color");
	    colorItem.addActionListener(new ActivityMenuColorController(activityView.getModel(), activityView));
	    popup.add(colorItem);
	    JMenuItem editItem = new JMenuItem("Edit");
	    editItem.addActionListener(new ActivityMenuEditController(activityView.getModel(), this));
	    popup.add(editItem);
	    JMenuItem deleteItem = new JMenuItem("Delete");
	    deleteItem.addActionListener(new ActivityMenuDeleteController(activityView.getModel(), this));
	    popup.add(deleteItem);
	    selected = true;
	}
	
	public void select() {
		setSelected(true);
		((ActivityRenderingModel) getRenderingModel()).setOpaque();
	}
	
	public void deselect() {
		setSelected(false);
		((ActivityRenderingModel) getRenderingModel()).setTransparent();
	}

	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public ActivityView getAssociatedActivity() {
		return associatedActivity;
	}

	public void setAssociatedActivity(ActivityView associatedActivity) {
		this.associatedActivity = associatedActivity;
	}

	public void addBackgroundListener(BackgroundListener bl) {
		if (bl == null)
			return;
		EventDispatcher.add(bl);
	}
	
	public void removeBackgroundListener(BackgroundListener bl) {
		EventDispatcher.remove(bl);
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
	
	@Override
	public void modelChanged(Model m) {
		setToolTipText(((Activity) getModel()).toToolTip());
		if(associatedActivity != null && m instanceof ActivityRenderingModel) {
			Color c = ((ActivityRenderingModel) m).getColor();
			((ActivityRenderingModel) associatedActivity.getRenderingModel()).setColor(c);
		}
		if(getParent() != null)
			getParent().repaint();
	}

	@Override
	public void backgroundChanged(BackgroundDrawer bd) {
		((ActivityRenderer) getRenderer()).setBackgroundDrawer(bd);
	}
}
