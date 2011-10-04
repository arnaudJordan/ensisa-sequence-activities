package sequence.ui.component.sequence;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import sequence.model.Activity;
import sequence.model.Sequence;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;

public class SequenceRenderer extends DefaultRenderer implements Renderer {
	
	public SequenceRenderer(View view) {
		super(view);
	}

	public void renderView(Graphics2D g) {
		super.renderView(g);
        renderSequence(g);
	}
	
	private void renderSequence(Graphics2D g) {
		getView().setLayout(new FlowLayout());
		for(Activity current : ((Sequence)getView().getModel()).getActivities()) {
			boolean flag = false;
			if(getView().getComponentCount() != 0) {
				int i = 0;
				while(!flag && i < getView().getComponents().length) {
					if(((Activity)(((ActivityView)getView().getComponents()[i]).getModel())).getId() != current.getId())
						i++;
					else
						flag = true;
				}
			}
			if(!flag)
				getView().add(new ActivityView(current));
		}
		System.out.println(getView().getComponentCount());
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 50);
	}
}
