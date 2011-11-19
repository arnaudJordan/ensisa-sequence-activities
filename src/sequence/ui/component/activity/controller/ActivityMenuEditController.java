package sequence.ui.component.activity.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.window.EditActivityWindow;
import sequence.ui.window.MainWindow;

public class ActivityMenuEditController extends Controller implements
		ActionListener {

	public ActivityMenuEditController(Model model, View view) {
		super(model, view);
	}

	public void actionPerformed(ActionEvent e) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new EditActivityWindow((MainWindow) getView().getTopLevelAncestor(),(Activity) getView().getModel(), (Sequence) ((SubSequenceView)getView().getParent()).getModel()).setVisible(true);
			}
		});
	}
}
