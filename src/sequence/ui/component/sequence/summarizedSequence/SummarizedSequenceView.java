package sequence.ui.component.sequence.summarizedSequence;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuImageExportController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuSVGExportController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuTikzExportController;

public class SummarizedSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private List<Activity> selectedActivities;
	private JPopupMenu popup;

	public SummarizedSequenceView(Model model) {
		super(model);
		this.selectedActivities = new ArrayList<Activity>();		
		setRenderer(new SummarizedSequenceRenderer(this));
		popup = new JPopupMenu();
	    JMenuItem exportItem = new JMenuItem("Develop");
	    exportItem.addActionListener(new SummarizedSequenceController(getModel(), this));
	    
	    JMenu exportMenu = new JMenu("Export");
		JMenuItem imageExportMenu = new JMenuItem("to image");
		imageExportMenu.addActionListener(new SubSequenceMenuImageExportController(getModel(), this));
		exportMenu.add(imageExportMenu);
		
		JMenuItem svgExportMenu = new JMenuItem("to svg");
		svgExportMenu.addActionListener(new SubSequenceMenuSVGExportController(getModel(), this));
		exportMenu.add(svgExportMenu);
		
		JMenuItem tikzExportMenu = new JMenuItem("to tikz");
		tikzExportMenu.addActionListener(new SubSequenceMenuTikzExportController(getModel(), this));
		exportMenu.add(tikzExportMenu);
		
		popup.add(exportMenu);
	    
	    popup.add(exportItem);
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}

	public List<Activity> getSelectedActivities() {
		return selectedActivities;
	}
}
