package sequence.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CustomLayout implements LayoutManager {
	private static final int VGAP = 10;
	
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
		int HMargin = targetInsets.left + targetInsets.right;
		int currentHeight = targetInsets.top;
		for(int i=0 ; i < target.getComponentCount(); i++) {
			Component c = target.getComponent(i);
			if (c.isVisible()) {
				if(c instanceof JButton){
					System.out.println(c.getWidth());
					c.setBounds(target.getWidth() - (targetInsets.right + targetInsets.left)/2 - c.getWidth()/2, targetInsets.top - c.getPreferredSize().height/2, c.getPreferredSize().width, c.getPreferredSize().height);
				}else if(c instanceof JLabel)
					c.setBounds(HMargin, currentHeight, c.getPreferredSize().width, c.getPreferredSize().height);
				else
					c.setBounds(HMargin, currentHeight, c.getPreferredSize().width, c.getPreferredSize().height);
				if(c.getY() < currentHeight)
					currentHeight += c.getPreferredSize().height + VGAP - (currentHeight - c.getY());
				else
					currentHeight += c.getPreferredSize().height + VGAP;
			}
		}
	}
}
