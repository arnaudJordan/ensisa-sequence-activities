package sequence.ui.component.sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sequence.model.Phase;
import sequence.model.Sequence;
import sequence.mvc.View;
import sequence.processor.AddSubSequence;
import sequence.processor.RemoveSubSequence;
import sequence.ui.component.timeIndicator.TimeIndicatorView;
import sequence.ui.window.MainWindow;
import sequence.utilities.CustomLayout;

public class SequenceContainer extends JPanel {
   private static final long serialVersionUID = 1L;
   private View view;
   private List<SequenceContainer> childs;
   
   public SequenceContainer(final View view, final String label, final SequenceContainer parent) {
       this(view, "", label, parent);
   }
   
   public SequenceContainer(final View view, final String title, final String label, final MainWindow parent) {
       this.view = view;
       childs = new ArrayList<SequenceContainer>();
       
       setBackground(Color.WHITE);
       setBorder(BorderFactory.createTitledBorder(title));
       setLayout(new CustomLayout());
       
       JLabel l = new JLabel(label);
       ImageIcon icon = new ImageIcon("icons/dialog-close.png");
       JButton button = new JButton(icon);
       button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
       final SequenceContainer sc = this;
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               parent.remove(sc);
           }
       });
       add(l);
       add(button);
       super.add(this.view);
   }
   
   public SequenceContainer(final View view, final String title, final String label, final SequenceContainer parent) {
       this.view = view;
       childs = new ArrayList<SequenceContainer>();
       
       setBackground(Color.WHITE);
       setBorder(BorderFactory.createTitledBorder(title));
       setLayout(new CustomLayout());
       
       JLabel l = new JLabel(label);
       ImageIcon icon = new ImageIcon("icons/dialog-close.png");
       JButton button = new JButton(icon);
       button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               parent.remove((Sequence) view.getModel());
           }
       });
       add(l);
       add(button);
       super.add(this.view);
   }
   
   public void add(Sequence sequence) {
	   ((MainWindow) getTopLevelAncestor()).getProcessor().Do(new AddSubSequence(sequence, this));
   }

   public void remove(Sequence sequence) {
	   ((MainWindow) getTopLevelAncestor()).getProcessor().Do(new RemoveSubSequence(sequence, this));	
   }

   public View getView() {
	   return view;
   }

   public List<SequenceContainer> getChilds() {
	   return childs;
   }

   public Dimension getPreferredSize() {
	   int Hinsets = 2*(getInsets().left + getInsets().right);
	   return new Dimension(getParent().getWidth() - Hinsets, getLayout().minimumLayoutSize(this).height);
   }
}
