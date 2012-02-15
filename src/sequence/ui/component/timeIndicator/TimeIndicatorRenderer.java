package sequence.ui.component.timeIndicator;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.Phase;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.utilities.drawer.TimeIndicatorDrawer;

public class TimeIndicatorRenderer extends DefaultRenderer implements Renderer {
	protected TimeIndicatorDrawer tid;
	
	public TimeIndicatorRenderer(View view) {
		super(view);
		this.tid=new TimeIndicatorDrawer();
	}

	@Override
	public void renderView(Graphics2D g) {
		super.renderView(g);
        renderTimeIndicator(g);
	}
	
	private void renderTimeIndicator(Graphics2D g) {
		renderBackground(g);
	}

	private void renderBackground(Graphics2D g) {
		Phase phase = (Phase) ((TimeIndicatorView)getView()).getModel();
		if(phase != null)
			tid.Draw(g, (int)(getView().getSize().getWidth()), (int)(getView().getSize().getHeight()), ((TimeIndicatorRenderingModel)getView().getRenderingModel()).getColor());
	}
	
	public void setBackgroundDrawer(TimeIndicatorDrawer tid)
	{
		this.tid=tid;
		getView().repaint();
	}
	public TimeIndicatorDrawer getBackgroundDrawer() {
		return this.tid;
	}
	@Override
	public Dimension getPreferredSize() {
		Phase phase = (Phase) ((TimeIndicatorView)getView()).getModel();
		TimeIndicatorRenderingModel renderingModel = ((TimeIndicatorRenderingModel)getView().getRenderingModel());
		if(phase != null && renderingModel != null)
			return new Dimension((int)(((TimeIndicatorView) this.getView()).getDuration()* renderingModel.getHScale()) + 1, (int)(((TimeIndicatorRenderingModel)getView().getRenderingModel()).getHeight() * renderingModel.getVScale()));
		return super.getPreferredSize();
	}

	
}
