package sequence.ui.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class CustomLayout implements LayoutManager {
	private static final int VGAP = 10;
	private static final int BORDER_TEXT_SPACING = 2;
	private static final int BORDER_STROKE_SIZE = 1;
	
	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	@Override
	public void removeLayoutComponent(Component comp) {
	}

	@Override
	public Dimension preferredLayoutSize(Container target) {
		return minimumLayoutSize(target);
	}

	public Dimension minimumLayoutSize(Container target) {
		int currentHeight = target.getInsets().top;
		for(int i=0 ; i < target.getComponentCount(); i++) {
			Component c = target.getComponent(i);
			if (c.isVisible()) {
				if(c.getY() < currentHeight)
					currentHeight += c.getPreferredSize().height + VGAP - (currentHeight - c.getY());
				else
					currentHeight += c.getPreferredSize().height + VGAP;
			}
		}
		return new Dimension(target.getWidth(), currentHeight);
	}

	@Override
	public void layoutContainer(Container target) {
		Insets targetInsets = target.getInsets();
		int leftMargin = targetInsets.left;
		int HMargin = targetInsets.left + targetInsets.right;
		int currentHeight = targetInsets.top;
		int topMargin = 0;
		int strokeSize = BORDER_STROKE_SIZE;
		Border border = ((JComponent) target).getBorder();
		if(border != null && border instanceof TitledBorder) {
			if(UIManager.getLookAndFeel().getName().equals("Nimbus"))
				strokeSize = 2;
			else
				strokeSize = 0;
			int position = ((TitledBorder) border).getTitlePosition();
			Font borderTitleFont = ((TitledBorder) border).getTitleFont();
			FontMetrics metrics = target.getGraphics().getFontMetrics(borderTitleFont);
			if(!((TitledBorder) border).getTitle().isEmpty()) {
				if(UIManager.getLookAndFeel().getName().equals("Nimbus"))
					strokeSize = 4;
				else
					strokeSize = 2;
				if(position == TitledBorder.ABOVE_TOP)
					topMargin += metrics.getHeight() + strokeSize/2 + BORDER_TEXT_SPACING;
				else if(position == TitledBorder.DEFAULT_POSITION)
					topMargin += metrics.getHeight()/2;
			}
		}
		for(int i=0 ; i < target.getComponentCount(); i++) {
			Component c = target.getComponent(i);
			if (c.isVisible()) {
				if(c instanceof JButton){
					c.setBounds(target.getWidth() - c.getWidth() - strokeSize, topMargin, c.getPreferredSize().width, c.getPreferredSize().height);
				}else if(c instanceof JLabel)
					c.setBounds(leftMargin, currentHeight, c.getPreferredSize().width, c.getPreferredSize().height);
				else
					c.setBounds(leftMargin, currentHeight, c.getPreferredSize().width - HMargin, c.getPreferredSize().height);
				if(c.getY() < currentHeight)
					currentHeight += c.getPreferredSize().height + VGAP - (currentHeight - c.getY());
				else
					currentHeight += c.getPreferredSize().height + VGAP;
			}
		}
	}
}
