package sequence.ui.component.sequence.subSequence;

import java.awt.Container;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.mvc.Model;
import sequence.mvc.View;

public class SubSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private Container parent;
	private JPopupMenu popup;

	public SubSequenceView(Model model, Container parent) {
		super(model);
		this.parent = parent;
		setRenderingModel(new SubSequenceRenderingModel());
		setRenderer(new SubSequenceRenderer(this));
		popup = new JPopupMenu();
	    JMenuItem exportItem = new JMenuItem("Export");
	    exportItem.addActionListener(new SubSequenceController(getModel(), this));
	    popup.add(exportItem);
	}
	
	public Container getParent() {
		return parent;
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}
	
	public void modelChanged(Model m) {
		removeAll();
		((SubSequenceRenderer)getRenderer()).initialize();
	}
}
