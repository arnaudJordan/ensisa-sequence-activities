package sequence.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class SelectRectangle extends JPanel implements MouseMotionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private Rectangle selection;
	private Point anchor;
	private Container context;
	 
	public SelectRectangle(){
		addMouseListener(this);
		addMouseMotionListener(this);
		this.context = getParent();
	}
	
	public SelectRectangle(Container context){
		addMouseListener(this);
		addMouseMotionListener(this);
		this.context = context;
	}
	 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(selection!=null) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.draw(selection);
		}
	}
	
	public boolean contains(Component c) {
		for(int i=0 ; i<context.getComponentCount() ; i++) {
			if(c.equals(context.getComponent(i)))
				return true;
		}
		return false;
	}
	
	private void selectComponents(Point p) {
		Component component = context.getComponentAt(p);
		if(!contains(component) && !component.equals(context))
			add(component);
		for(int i=0 ; i<getComponentCount() ; i++) {
			if(!selection.intersects(getComponent(i).getBounds()));
			remove(getComponent(i));
		}
	}
	
	public void mousePressed(MouseEvent e) {
		anchor = e.getPoint();
		selection = new Rectangle(anchor);
		selectComponents(e.getPoint());
	}
	 
	public void mouseDragged(MouseEvent e) {
		selection.setBounds( (int)Math.min(anchor.x,e.getX()), (int)Math.min(anchor.y,e.getY()),
		(int)Math.abs(e.getX()-anchor.x), (int)Math.abs(e.getY()-anchor.y));
		selectComponents(e.getPoint());
		repaint();
	}
	 
	public void mouseReleased(MouseEvent e) {
		selection = null;
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
}