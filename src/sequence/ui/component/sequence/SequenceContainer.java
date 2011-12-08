package sequence.ui.component.sequence;

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
import sequence.model.Sequence;
import sequence.processor.AddSubSequence;
import sequence.processor.RemoveSubSequence;
import sequence.ui.component.sequence.subSequence.SubSequenceContainer;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.ui.window.MainWindow;
import sequence.utilities.CustomLayout;

public class SequenceContainer extends JPanel {
   private static final long serialVersionUID = 1L;
   private MainWindow mainWindow;
   private SummarizedSequenceView summarizedSequenceView;
   private List<SubSequenceContainer> subSequenceContainers;
   
   public SequenceContainer(final Sequence sequence, final MainWindow mainWindow)
   {
       this.mainWindow = mainWindow;
       summarizedSequenceView = new SummarizedSequenceView(sequence, this);
       new SummarizedSequenceController(sequence, summarizedSequenceView);
       
       subSequenceContainers = new ArrayList<SubSequenceContainer>();
       
       //setBackground(Color.WHITE);
       setBorder(BorderFactory.createTitledBorder(((Sequence)summarizedSequenceView.getModel()).getWorkflowID()));
       setLayout(new CustomLayout());
       
       JLabel label = new JLabel("Summarized sequence :");
       ImageIcon icon = new ImageIcon("icons/dialog-close.png");
       JButton button = new JButton(icon);
       button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
       final SequenceContainer sc = this;
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               mainWindow.removeSequence(sc);
           }
       });
       add(label);
       add(button);
       add(summarizedSequenceView);
   }
   
   public void addSubSequence(Sequence model) {
	   mainWindow.getProcessor().Do(new AddSubSequence((Sequence) model, this));
   }
   
   public void removeSubSequence(Sequence model) {
	   mainWindow.getProcessor().Do(new RemoveSubSequence((Sequence) model, this));	
   }
   
   public SummarizedSequenceView getSummarizedSequenceView() {
       return summarizedSequenceView;
   }
   
   public List<SubSequenceContainer> getSubSequenceContainers() {
	   return subSequenceContainers;
}

public Dimension getPreferredSize() {
       int Hinsets = 2*(getInsets().left + getInsets().right);
       return new Dimension(mainWindow.getWidth() - Hinsets, getMinimumSize().height);
   }
}
