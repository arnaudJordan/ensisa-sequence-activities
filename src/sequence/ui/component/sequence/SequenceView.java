package sequence.ui.component.sequence;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.mvc.Model;
import sequence.mvc.View;

public class SequenceView extends View {
	private static final long serialVersionUID = 1L;
	private JPopupMenu popup;

	public SequenceView(Model model) {
		super(model);
		setRenderingModel(new SequenceRenderingModel());
		setRenderer(new SequenceRenderer(this));
		popup = new JPopupMenu();
	    JMenuItem exportItem = new JMenuItem("Export");
	    exportItem.addActionListener(new SequenceController(getModel(), this));
	    popup.add(exportItem);
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}
	
	public void modelChanged(Model m) {
		removeAll();
		((SequenceRenderer)getRenderer()).initialize();
	}
}
