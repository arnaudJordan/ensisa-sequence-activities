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

/**
 * The Class ActivityView.
 */
public class ActivityView extends View implements BackgroundListener,
		ScaleListener, Scaleable, Timeable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The popup. */
	public JPopupMenu popup;
	
	/** The selected. */
	private boolean selected;
	
	/** The associated activities. */
	private List<ActivityView> associatedActivities;

	/**
	 * Instantiates a new activity view.
	 *
	 * @param model the model
	 */
	public ActivityView(final Model model) {
		super(model);
		setRenderer(new ActivityRenderer(this));
		setRenderingModel(new ActivityRenderingModel());
		addBackgroundListener(this);
		associatedActivities = new ArrayList<ActivityView>();
	}

	/**
	 * Instantiates a new activity view.
	 *
	 * @param activityView the activity view
	 */
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

	/**
	 * Select.
	 */
	public void select() {
		setSelected(true);
		((ActivityRenderingModel) getRenderingModel()).setOpaque();
	}

	/**
	 * Deselect.
	 */
	public void deselect() {
		setSelected(false);
		((ActivityRenderingModel) getRenderingModel()).setTransparent();
	}

	/**
	 * Checks if is selected.
	 *
	 * @return true, if is selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(final boolean selected) {
		this.selected = selected;
	}

	/**
	 * Gets the associated activities.
	 *
	 * @return the associated activities
	 */
	public List<ActivityView> getAssociatedActivities() {
		return associatedActivities;
	}

	/**
	 * Adds the background listener.
	 *
	 * @param bl the bl
	 */
	public void addBackgroundListener(final BackgroundListener bl) {
		if (bl == null)
			return;
		OptionEventDispatcher.add(bl);
	}

	/**
	 * Removes the background listener.
	 *
	 * @param bl the bl
	 */
	public void removeBackgroundListener(final BackgroundListener bl) {
		OptionEventDispatcher.remove(bl);
	}

	/**
	 * Adds the scale listener.
	 *
	 * @param sl the sl
	 */
	public void addScaleListener(final ScaleListener sl) {
		OptionEventDispatcher.add(sl);
	}

	/**
	 * Removes the scale listener.
	 *
	 * @param sl the sl
	 */
	public void removeScaleListener(final ScaleListener sl) {
		OptionEventDispatcher.remove(sl);
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Timeable#getStartTime()
	 */
	@Override
	public int getStartTime() {
		return ((Activity) getModel()).getActivitytime().getStartTime();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Timeable#getStopTime()
	 */
	@Override
	public int getStopTime() {
		return ((Activity) getModel()).getActivitytime().getStopTime();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Timeable#getDuration()
	 */
	@Override
	public int getDuration() {
		return ((Activity) getModel()).getActivitytime().getDuration();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Scaleable#getHScale()
	 */
	@Override
	public float getHScale() {
		return ((ActivityRenderingModel) getRenderingModel()).getHScale();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Scaleable#getVScale()
	 */
	@Override
	public float getVScale() {
		return ((ActivityRenderingModel) getRenderingModel()).getVScale();
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.View#modelChanged(sequence.mvc.Model)
	 */
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

	/* (non-Javadoc)
	 * @see sequence.utilities.BackgroundListener#backgroundChanged(sequence.ui.utilities.drawer.BackgroundDrawer)
	 */
	@Override
	public void backgroundChanged(final BackgroundDrawer bd) {
		((ActivityRenderer) getRenderer()).setBackgroundDrawer(bd);
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.ScaleListener#scaleChanged(float)
	 */
	@Override
	public void scaleChanged(final float scale) {
		((ActivityRenderingModel) getRenderingModel()).setScale(scale);
		if (getParent() != null) {
			((JComponent) getParent()).revalidate();
			getParent().repaint();
		}
	}
}
