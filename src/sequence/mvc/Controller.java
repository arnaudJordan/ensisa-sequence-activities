package sequence.mvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller implements KeyListener, ModelListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public static final int LEFT_MOUSE_BUTTON   = MouseEvent.BUTTON1;
    public static final int MIDDLE_MOUSE_BUTTON = MouseEvent.BUTTON2;
    public static final int RIGHT_MOUSE_BUTTON  = MouseEvent.BUTTON3;
	
	private Model model;
    private View view;
            
    public Controller(Model model, View view) {
        this.setModel(model);
        this.setView(view);
        this.view.addKeyListener(this);
        this.view.addMouseListener(this);
        this.view.addMouseMotionListener(this);
        this.view.addMouseWheelListener(this);
        this.model.addModelListener(this);
        this.view.getRenderingModel().addModelListener(this);
    }

	public void setModel(Model model) {
		this.model = model;
	}

	public Model getModel() {
		return model;
	}

	public void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void modelChanged(Model m) {
		this.view.repaint();
	}
}

