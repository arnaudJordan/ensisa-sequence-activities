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
import sequence.utilities.TimeLayout;

public class TimeLineRenderer extends DefaultRenderer implements Renderer {
	private float scale=1;
	
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
			int sequenceSize = phases.getLastPhase().getDate() - phases.get(0).getDate()+50;
			scale = (float) (getView().getSize().getWidth()/sequenceSize);
		}
	}

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
	
	public Dimension getPreferredSize() {
		int Hinsets = 2*(getView().getParent().getInsets().left + getView().getParent().getInsets().right);
    	return new Dimension(getView().getParent().getWidth() - Hinsets, (int) getView().getLayout().minimumLayoutSize(getView()).getHeight());
    }
}