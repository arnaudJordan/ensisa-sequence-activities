package sequence.processor.command;

import java.awt.Color;
import sequence.ui.component.activity.ActivityRenderingModel;

public class ColorChange extends Command {
	private Color color;
	
	public ColorChange(ActivityRenderingModel model, Color color)
	{
		this.model=model;
		this.color=color;
		this.undo=new ColorChange(model, model.getColor(), this);
	}
	public ColorChange(ActivityRenderingModel model, Color color, ColorChange colorChange) {
		this.model=model;
		this.color=color;
		this.undo=colorChange;
	}
	@Override
	public void Do() {
		((ActivityRenderingModel) this.model).setColor(color);
	}
}
