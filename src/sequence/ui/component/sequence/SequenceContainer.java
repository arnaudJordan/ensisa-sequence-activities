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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sequence.model.Sequence;
import sequence.mvc.View;
import sequence.processor.command.AddSubSequence;
import sequence.processor.command.RemoveSubSequence;
import sequence.ui.component.timeLine.TimeLineView;
import sequence.ui.utilities.CustomLayout;
import sequence.ui.window.MainWindow;

public class SequenceContainer extends JPanel {
   private static final long serialVersionUID = 1L;
   private View view;
   private List<SequenceContainer> childs;
   
   public SequenceContainer(final View view, final String label, final SequenceContainer parent) {
       this(view, "", label, parent);
   }
   
   public SequenceContainer(final View view, final String label) {
       this.view = view;
       childs = new ArrayList<SequenceContainer>();
       
       setBackground(Color.WHITE);
       setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       setLayout(new CustomLayout());
       
       JLabel l = new JLabel(label);
       
       add(l);
       add(new TimeLineView(((Sequence) view.getModel()).getPhases()));
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
       final SequenceContainer sc = this;
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   int response = JOptionPane.showConfirmDialog(sc,
						"Are you sur you want to close this sub sequence?",
						"Close sub sequence", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (response == JOptionPane.NO_OPTION)
					return;
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
	   return new Dimension(getParent().getWidth(), getLayout().minimumLayoutSize(this).height);
   }
}
