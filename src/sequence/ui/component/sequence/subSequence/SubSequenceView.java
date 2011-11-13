package sequence.ui.component.sequence.subSequence;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.mvc.Model;
import sequence.mvc.View;

public class SubSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private JPopupMenu popup;

	public SubSequenceView(Model model) {
		super(model);
		setRenderingModel(new SubSequenceRenderingModel());
		setRenderer(new SubSequenceRenderer(this));
		popup = new JPopupMenu();
	    JMenuItem exportItem = new JMenuItem("Export");
	    exportItem.addActionListener(new SubSequenceController(getModel(), this));
	    popup.add(exportItem);
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}
	
	public void modelChanged(Model m) {
		removeAll();
		((SubSequenceRenderer)getRenderer()).initialize();
	}
}
