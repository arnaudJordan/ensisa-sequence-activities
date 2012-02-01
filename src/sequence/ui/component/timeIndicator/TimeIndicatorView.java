package sequence.ui.component.timeIndicator;

import sequence.model.Phase;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.utilities.Scaleable;
import sequence.utilities.Timeable;

public class TimeIndicatorView extends View implements Timeable, Scaleable {
	private static final long serialVersionUID = 1L;

	public TimeIndicatorView(Model model) {
		super(model);
		setRenderer(new TimeIndicatorRenderer(this));
		setRenderingModel(new TimeIndicatorRenderingModel());
		setToolTipText(((Phase) model).getName());
	}

	@Override
	public float getHScale() {
		return ((TimeIndicatorRenderingModel) getRenderingModel()).getHScale();
	}

	@Override
	public float getVScale() {
		return ((TimeIndicatorRenderingModel) getRenderingModel()).getVScale();
	}

	@Override
	public int getStartTime() {
		return ((Phase) getModel()).getDate();
	}

	@Override
	public int getStopTime() {
		return ((Phase) getModel()).getStopTime();
	}

	@Override
	public int getDuration() {
		return ((Phase) getModel()).getStopTime()-((Phase) getModel()).getDate();
	}

}
