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

/**
 * The layout that is used by sequence containers.
 */
public class CustomLayout implements LayoutManager {
	
	/** The Constant VGAP. */
	private static final int VGAP = 10;
	
	/** The Constant BORDER_TEXT_SPACING. */
	private static final int BORDER_TEXT_SPACING = 2;
	
	/** The Constant BORDER_STROKE_SIZE. */
	private static final int BORDER_STROKE_SIZE = 1;

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
	 */
	@Override
	public void addLayoutComponent(final String name, final Component comp) {
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
	 */
	@Override
	public void removeLayoutComponent(final Component comp) {
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension preferredLayoutSize(final Container target) {
		return minimumLayoutSize(target);
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension minimumLayoutSize(final Container target) {
		int currentHeight = target.getInsets().top;
		for (int i = 0; i < target.getComponentCount(); i++) {
			final Component c = target.getComponent(i);
			if (c.isVisible()) {
				if (c.getY() < currentHeight)
					currentHeight += c.getPreferredSize().height - VGAP;
				else
					currentHeight += c.getPreferredSize().height + VGAP;
			}
		}
		return new Dimension(target.getWidth(), currentHeight);
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	@Override
	public void layoutContainer(final Container target) {
		final Insets targetInsets = target.getInsets();
		final int leftMargin = targetInsets.left;
		final int HMargin = targetInsets.left + targetInsets.right;
		int currentHeight = targetInsets.top;
		int topMargin = 0;
		int strokeSize = BORDER_STROKE_SIZE;
		final Border border = ((JComponent) target).getBorder();
		if (border != null && border instanceof TitledBorder) {
			if (UIManager.getLookAndFeel().getName().equals("Nimbus"))
				strokeSize = 2;
			else
				strokeSize = 0;
			final int position = ((TitledBorder) border).getTitlePosition();
			final Font borderTitleFont = ((TitledBorder) border).getTitleFont();
			final FontMetrics metrics = target.getGraphics().getFontMetrics(
					borderTitleFont);
			if (!((TitledBorder) border).getTitle().isEmpty()) {
				if (UIManager.getLookAndFeel().getName().equals("Nimbus"))
					strokeSize = 4;
				else
					strokeSize = 2;
				if (position == TitledBorder.ABOVE_TOP)
					topMargin += metrics.getHeight() + strokeSize / 2
							+ BORDER_TEXT_SPACING;
				else if (position == TitledBorder.DEFAULT_POSITION)
					topMargin += metrics.getHeight() / 2;
			}
		}
		for (int i = 0; i < target.getComponentCount(); i++) {
			final Component c = target.getComponent(i);
			if (c.isVisible()) {
				if (c instanceof JButton) {
					c.setBounds(target.getWidth() - c.getWidth() - strokeSize,
							topMargin, c.getPreferredSize().width,
							c.getPreferredSize().height);
				} else if (c instanceof JLabel)
					c.setBounds(leftMargin, currentHeight,
							c.getPreferredSize().width,
							c.getPreferredSize().height);
				else
					c.setBounds(leftMargin, currentHeight,
							c.getPreferredSize().width - HMargin,
							c.getPreferredSize().height);
				if (c.getY() < currentHeight)
					currentHeight += c.getPreferredSize().height - VGAP;
				else
					currentHeight += c.getPreferredSize().height + VGAP;
			}
		}
	}
}
