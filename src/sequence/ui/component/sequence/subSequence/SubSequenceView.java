package sequence.ui.component.sequence.subSequence;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuInfoController;

public class SubSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private View summarizedView;
	private JPopupMenu popup;

	public SubSequenceView(Model model, View summarizedView) {
		super(model);
		setRenderingModel(new SubSequenceRenderingModel());
		this.summarizedView = summarizedView;
		setRenderer(new SubSequenceRenderer(this));
		popup = new JPopupMenu();
		JMenuItem exportMenu = new JMenuItem("Export");
		exportMenu.addActionListener(new SubSequenceMenuExportController(getModel(), this));		
		popup.add(exportMenu);
	    JMenuItem infoItem = new JMenuItem("Info");
	    infoItem.addActionListener(new SubSequenceMenuInfoController(getModel(), this));
	    popup.add(infoItem);
	}
	
	public View getSummarizedView() {
		return summarizedView;
	}

	public JPopupMenu getPopup() {
		return popup;
	}
	
	public void modelChanged(Model m) {
		removeAll();
		((SubSequenceRenderer)getRenderer()).initialize();
		((SequenceContainer) getSummarizedView().getParent()).revalidate();
		getSummarizedView().getParent().repaint();
	}
}
