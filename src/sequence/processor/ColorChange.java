package sequence.processor;

import java.awt.Color;
import sequence.ui.component.activity.ActivityRenderingModel;

public class ColorChange extends Command {
	private Color color;
	private Color oldColor;
	
	public ColorChange(ActivityRenderingModel model, Color color)
	{
		this.model=model;
		this.color=color;
		this.oldColor=model.getColor();
	}
	@Override
	public void Do() {
		((ActivityRenderingModel) this.model).setColor(color);
	}
	
	public void Undo() {
		((ActivityRenderingModel) this.model).setColor(oldColor);
	}

}
