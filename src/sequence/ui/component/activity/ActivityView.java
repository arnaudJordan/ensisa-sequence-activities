package sequence.ui.component.activity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
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
import sequence.utilities.OptionEventDispatcher;
import sequence.utilities.ScaleListener;
import sequence.utilities.Scaleable;
import sequence.utilities.Timeable;

public class ActivityView extends View implements BackgroundListener,
		ScaleListener, Scaleable, Timeable {
	private static final long serialVersionUID = 1L;
	public JPopupMenu popup;
	private boolean selected;
	private List<ActivityView> associatedActivities;

	public ActivityView(final Model model) {
		super(model);
		setRenderer(new ActivityRenderer(this));
		setRenderingModel(new ActivityRenderingModel());
		addBackgroundListener(this);
		associatedActivities = new ArrayList<ActivityView>();
	}

	public ActivityView(final ActivityView activityView) {
		super(activityView.getModel());
		setRenderer(new ActivityRenderer(this));
		setRenderingModel(new ActivityRenderingModel(
				((ActivityRenderingModel) activityView.getRenderingModel())
						.getColor(),
				ActivityRenderingModel.CURRENT_SCALE));
		addBackgroundListener(this);
		addScaleListener(this);
		activityView.getAssociatedActivities().add(this);
		popup = new JPopupMenu();
		final JMenuItem colorItem = new JMenuItem("Color");
		colorItem.addActionListener(new ActivityMenuColorController(
				activityView.getModel(), activityView));
		popup.add(colorItem);
		final JMenuItem editItem = new JMenuItem("Edit");
		editItem.addActionListener(new ActivityMenuEditController(activityView
				.getModel(), this));
		popup.add(editItem);
		final JMenuItem deleteItem = new JMenuItem("Delete");
		deleteItem.addActionListener(new ActivityMenuDeleteController(
				activityView.getModel(), this));
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

	public void setSelected(final boolean selected) {
		this.selected = selected;
	}

	public List<ActivityView> getAssociatedActivities() {
		return associatedActivities;
	}

	public void addBackgroundListener(final BackgroundListener bl) {
		if (bl == null)
			return;
		OptionEventDispatcher.add(bl);
	}

	public void removeBackgroundListener(final BackgroundListener bl) {
		OptionEventDispatcher.remove(bl);
	}

	public void addScaleListener(final ScaleListener sl) {
		OptionEventDispatcher.add(sl);
	}

	public void removeScaleListener(final ScaleListener sl) {
		OptionEventDispatcher.remove(sl);
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
	public void modelChanged(final Model m) {
		setToolTipText(((Activity) getModel()).toToolTip());
		if (associatedActivities != null && m instanceof ActivityRenderingModel) {
			final Color c = ((ActivityRenderingModel) m).getColor();
			for (final ActivityView av : associatedActivities)
				((ActivityRenderingModel) av.getRenderingModel()).setColor(c);
		}
		if (m instanceof Activity) {
			revalidate();
			repaint();
		}
		if (getParent() != null)
			getParent().repaint();
	}

	@Override
	public void backgroundChanged(final BackgroundDrawer bd) {
		((ActivityRenderer) getRenderer()).setBackgroundDrawer(bd);
	}

	@Override
	public void scaleChanged(final float scale) {
		((ActivityRenderingModel) getRenderingModel()).setScale(scale);
		if (getParent() != null) {
			((JComponent) getParent()).revalidate();
			getParent().repaint();
		}
	}
}
