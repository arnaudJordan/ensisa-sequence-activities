package sequence.ui.component.sequence;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.util.List;

import sequence.model.Activity;
import sequence.model.Sequence;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityController;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.utilities.BetterSizeFlowLayout;
import sequence.utilities.ColorFactory;

public class SequenceRenderer extends DefaultRenderer implements Renderer {
	
	public SequenceRenderer(View view) {
		super(view);
		getView().setLayout(new FlowLayout());
		initialize();
	}

	public void initialize() {
		Sequence sequence = (Sequence)getView().getModel();
		ColorFactory colorFactory = new ColorFactory(sequence);
		for(Activity current : sequence) {
			if(((SequenceRenderingModel)getView().getRenderingModel()).getDurationThreshold() <= current.getActivitytime().getDuration()) {
				ActivityView activityView = new ActivityView(current);
				activityView.setRenderingModel(new ActivityRenderingModel(colorFactory.createColor(current)));
				ActivityController activityController = new ActivityController(current, activityView);
				getView().add(activityView);
			}
		}
	}

	public void renderView(Graphics2D g) {
		super.renderView(g);
        renderSequence(g);
	}
	
	private void renderSequence(Graphics2D g) {
	}
	
	public Dimension getPreferredSize() {
		int width = getView().getParent().getWidth()-10;
    	int parentWidth = getView().getParent().getWidth()-10;
    	if(parentWidth>width)
    		return new Dimension(parentWidth, contentMaximumHeight(getView(), width));
    	else
    		return new Dimension(width, contentMaximumHeight(getView(), width));
	}
	
	private int contentMaximumHeight(Container target, int maxWidth) {
    	int height = 0; 
    	int lineWidth=0;
		for(int i=0 ; i<target.getComponentCount() ; i++) {
    		int width=target.getComponent(i).getWidth();
    		if(lineWidth==0)
    			height += target.getComponent(i).getHeight() + ((FlowLayout) getView().getLayout()).getVgap();
    		
    		lineWidth+=width + ((FlowLayout) getView().getLayout()).getHgap();
    		
    		if(lineWidth>=maxWidth)
    			lineWidth=0;
    	}
    	return height;
    }
}
