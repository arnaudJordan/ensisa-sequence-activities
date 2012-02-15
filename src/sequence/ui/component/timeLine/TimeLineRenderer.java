package sequence.ui.component.timeLine;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.Phase;
import sequence.model.Phases;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.timeIndicator.TimeIndicatorRenderingModel;
import sequence.ui.component.timeIndicator.TimeIndicatorView;
import sequence.ui.utilities.TimeLayout;

public class TimeLineRenderer extends DefaultRenderer implements Renderer {
	private float scale = 1;
	private int height = 10;
	
	public TimeLineRenderer(View view) {
		super(view);
		getView().setLayout(new TimeLayout());
		setScale();
		initialize();
	}

	public void initialize() {
		Phases phases = (Phases)getView().getModel();
		for(Phase phase : phases) {
			TimeIndicatorView timeIndicatorView = new TimeIndicatorView(phase);
			TimeIndicatorRenderingModel renderingModel = (TimeIndicatorRenderingModel) timeIndicatorView.getRenderingModel();
			renderingModel.setHScale(scale);
			getView().add(timeIndicatorView);
		}
	}
	
	public void setScale() {
		Phases phases = (Phases)getView().getModel();
		if(phases.size()>0)
		{
			int sequenceSize = phases.getLastPhase().getStopTime() - phases.get(0).getDate();
			scale = (float) (getView().getSize().getWidth()/sequenceSize);
		}
	}

	@Override
	public void renderView(Graphics2D g) {
		super.renderView(g);
		setScale();
        renderTimeLine(g);
	}
	
	private void renderTimeLine(Graphics2D g) {
		for(int i=0 ; i<getView().getComponentCount() ; i++) {
			TimeIndicatorView timeIndicatorView = (TimeIndicatorView) getView().getComponent(i);
			((TimeIndicatorRenderingModel) timeIndicatorView.getRenderingModel()).setHScale(scale);
		}
		getView().revalidate();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getView().getParent().getWidth(), height);
    }
}
