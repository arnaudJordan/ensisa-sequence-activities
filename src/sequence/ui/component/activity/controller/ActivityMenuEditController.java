package sequence.ui.component.activity.controller;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.processor.ColorChange;
import sequence.processor.CommandList;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.window.EditActivityWindow;
import sequence.ui.window.InfoWindow;
import sequence.ui.window.MainWindow;

public class ActivityMenuEditController extends Controller implements
		ActionListener {

	public ActivityMenuEditController(Model model, View view) {
		super(model, view);
	}

	public void actionPerformed(ActionEvent e) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new EditActivityWindow((MainWindow) getView().getTopLevelAncestor(),(Activity) getView().getModel()).setVisible(true);
			}
		});
	}
}
