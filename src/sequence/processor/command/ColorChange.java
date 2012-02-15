package sequence.processor.command;

import java.awt.Color;

import sequence.ui.component.activity.ActivityRenderingModel;

public class ColorChange extends Command {
	private final Color color;

	public ColorChange(final ActivityRenderingModel model, final Color color) {
		this.model = model;
		this.color = color;
		undo = new ColorChange(model, model.getColor(), this);
	}

	public ColorChange(final ActivityRenderingModel model, final Color color,
			final ColorChange colorChange) {
		this.model = model;
		this.color = color;
		undo = colorChange;
	}

	@Override
	public void Do() {
		((ActivityRenderingModel) model).setColor(color);
	}
}
