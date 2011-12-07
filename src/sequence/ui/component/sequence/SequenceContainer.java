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
import javax.swing.JSeparator;

import sequence.model.Sequence;
import sequence.processor.AddSubSequence;
import sequence.processor.RemoveSubSequence;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.ui.window.MainWindow;

public class SequenceContainer extends JPanel {
   private static final long serialVersionUID = 1L;
   private MainWindow mainWindow;
   private SummarizedSequenceView summarizedSequenceView;
   private List<SubSequenceView> subSequenceViews;
   private List<JPanel> subSequenceContainers;
   
   public SequenceContainer(final Sequence sequence, final MainWindow mainWindow)
   {
       this.mainWindow = mainWindow;
       summarizedSequenceView = new SummarizedSequenceView(sequence, this);
       new SummarizedSequenceController(sequence, summarizedSequenceView);
       subSequenceViews = new ArrayList<SubSequenceView>();
       
       //setBackground(Color.WHITE);
       setBorder(BorderFactory.createTitledBorder(((Sequence)summarizedSequenceView.getModel()).getWorkflowID()));
       //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
       
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
       //label.setAlignmentY(Component.LEFT_ALIGNMENT);
       add(label);
       //button.setAlignmentY(Component.RIGHT_ALIGNMENT);
       add(button);
       add(summarizedSequenceView);
       subSequenceContainers = new ArrayList<JPanel>();
   }

   public void initSubSequence(Sequence model) {
	   JPanel subSequenceContainer = new JPanel();
	   
	   //subSequenceContainer.setLayout(new BoxLayout(subSequenceContainer, BoxLayout.PAGE_AXIS));
	   
	   final SubSequenceView subSequenceView = new SubSequenceView(model, this);
       new SubSequenceMenuExportController(model, subSequenceView);
       subSequenceViews.add(subSequenceView);
	   
       JLabel label = new JLabel("Sub sequence :");
	   ImageIcon icon = new ImageIcon("icons/dialog-close.png");
	   JButton button = new JButton(icon);
       button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
       final SequenceContainer sc = this;
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   sc.removeSubSequence((Sequence) subSequenceView.getModel());
           }
       });
       JSeparator separator = new JSeparator();
	   
       //label.setAlignmentY(Component.LEFT_ALIGNMENT);
       subSequenceContainer.add(label);
       //button.setAlignmentY(Component.RIGHT_ALIGNMENT);
       subSequenceContainer.add(button);
       subSequenceContainer.add(separator);
       subSequenceContainer.add(subSequenceView);
       subSequenceContainer.setVisible(true);
       
       add(subSequenceContainer);
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

   public List<SubSequenceView> getSubSequenceViews() {
       return subSequenceViews;
   }
   
   public List<JPanel> getSubSequenceContainers() {
	return subSequenceContainers;
}

public Dimension getPreferredSize() {
       int Hinsets = 2*(getInsets().left + getInsets().right);
       return new Dimension(mainWindow.getWidth() - Hinsets, getMinimumSize().height);
   }
}
