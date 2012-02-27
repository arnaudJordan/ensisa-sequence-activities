package sequence.mvc;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * The Class Controller.
 */
public class Controller implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener {

	/** The Constant LEFT_MOUSE_BUTTON. */
	public static final int LEFT_MOUSE_BUTTON = MouseEvent.BUTTON1;
	
	/** The Constant MIDDLE_MOUSE_BUTTON. */
	public static final int MIDDLE_MOUSE_BUTTON = MouseEvent.BUTTON2;
	
	/** The Constant RIGHT_MOUSE_BUTTON. */
	public static final int RIGHT_MOUSE_BUTTON = MouseEvent.BUTTON3;
	
	/** The Constant CTRL. */
	public static final int CTRL = InputEvent.CTRL_MASK;
	
	/** The Constant SHIFT. */
	public static final int SHIFT = InputEvent.SHIFT_MASK;

	/** The model. */
	private Model model;
	
	/** The view. */
	private View view;

	/**
	 * Instantiates a new controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public Controller(final Model model, final View view) {
		setModel(model);
		setView(view);
		this.view.addKeyListener(this);
		this.view.addMouseListener(this);
		this.view.addMouseMotionListener(this);
		this.view.addMouseWheelListener(this);
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(final Model model) {
		this.model = model;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(final View view) {
		this.view = view;
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(final MouseWheelEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(final MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(final MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(final MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(final MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(final MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(final MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(final MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(final KeyEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(final KeyEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(final KeyEvent e) {

	}
}
