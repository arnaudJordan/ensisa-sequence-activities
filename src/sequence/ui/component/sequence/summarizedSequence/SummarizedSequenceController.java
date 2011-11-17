package sequence.ui.component.sequence.summarizedSequence;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

public class SummarizedSequenceController extends Controller implements ActionListener  {
	private Rectangle selection;
	private Point anchor;
	
	public SummarizedSequenceController(Model model, View view) {
		super(model, view);
	}
	
	public void mousePressed(MouseEvent e) {
		checkPopup(e);
		anchor = e.getPoint();
		selection = new Rectangle(anchor);
		drawSelection(getView().getGraphics());
		getView().repaint();
	}
	 
	public void mouseDragged(MouseEvent e) {
		selection.setBounds( (int)Math.min(anchor.x,e.getX()), (int)Math.min(anchor.y,e.getY()),
		(int)Math.abs(e.getX()-anchor.x), (int)Math.abs(e.getY()-anchor.y));
		drawSelection(getView().getGraphics());
		getView().repaint();
		
	}
	 
	public void mouseReleased(MouseEvent e) {
		checkPopup(e);
		selection = null;
	}

	public void actionPerformed(ActionEvent e) {
		SummarizedSequenceView view = (SummarizedSequenceView) getView();
		List<Activity> selectedActivities = view.getSelectedActivities();
		if(!selectedActivities.isEmpty()) {
			Collections.sort(selectedActivities, new Comparator<Activity>() {
				public int compare(Activity a1, Activity a2) {
					return a1.getId() < a2.getId() ? -1 : 1;
				}
			});
			Sequence subSequenceModel = new Sequence(((Sequence) view.getModel()).getWorkflowID(), selectedActivities);
			SubSequenceView subView = ((SequenceContainer) view.getParent()).getSubSequenceView();
			subView.setModel(subSequenceModel);
			getView().getParent().add(subView, BorderLayout.SOUTH);
		}
	}
	
	protected void drawSelection(Graphics g) {
		if (selection!=null){
			Graphics2D g2d = (Graphics2D)g;
			g2d.draw(selection);
		}
	}
	
	protected void checkPopup(MouseEvent e) {
		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
			((SummarizedSequenceView)getView()).getPopup().show(e.getComponent(), e.getX(), e.getY());
		}
		System.out.println(((SummarizedSequenceView) getView()).getSelectedActivities());
	}
}
