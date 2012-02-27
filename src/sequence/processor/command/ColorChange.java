package sequence.processor.command;

import java.awt.Color;

import sequence.ui.component.activity.ActivityRenderingModel;

/**
 * The command that is used to process a change of an activitie's color.
 */
public class ColorChange extends Command {
	
	/** The color. */
	private final Color color;

	/**
	 * Instantiates a new color change.
	 *
	 * @param model the model
	 * @param color the color
	 */
	public ColorChange(final ActivityRenderingModel model, final Color color) {
		this.model = model;
		this.color = color;
		undo = new ColorChange(model, model.getColor(), this);
	}

	/**
	 * Instantiates a new color change.
	 *
	 * @param model the model
	 * @param color the color
	 * @param colorChange the color change
	 */
	public ColorChange(final ActivityRenderingModel model, final Color color,
			final ColorChange colorChange) {
		this.model = model;
		this.color = color;
		undo = colorChange;
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Do()
	 */
	@Override
	public void Do() {
		((ActivityRenderingModel) model).setColor(color);
	}
}
