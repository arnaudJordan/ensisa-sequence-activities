package sequence.mvc;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener {

	public static final int LEFT_MOUSE_BUTTON = MouseEvent.BUTTON1;
	public static final int MIDDLE_MOUSE_BUTTON = MouseEvent.BUTTON2;
	public static final int RIGHT_MOUSE_BUTTON = MouseEvent.BUTTON3;
	public static final int CTRL = InputEvent.CTRL_MASK;
	public static final int SHIFT = InputEvent.SHIFT_MASK;

	private Model model;
	private View view;

	public Controller(final Model model, final View view) {
		setModel(model);
		setView(view);
		this.view.addKeyListener(this);
		this.view.addMouseListener(this);
		this.view.addMouseMotionListener(this);
		this.view.addMouseWheelListener(this);
	}

	public void setModel(final Model model) {
		this.model = model;
	}

	public Model getModel() {
		return model;
	}

	public void setView(final View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

	@Override
	public void mouseWheelMoved(final MouseWheelEvent e) {

	}

	@Override
	public void mouseDragged(final MouseEvent e) {

	}

	@Override
	public void mouseMoved(final MouseEvent e) {

	}

	@Override
	public void mouseClicked(final MouseEvent e) {

	}

	@Override
	public void mouseEntered(final MouseEvent e) {

	}

	@Override
	public void mouseExited(final MouseEvent e) {

	}

	@Override
	public void mousePressed(final MouseEvent e) {

	}

	@Override
	public void mouseReleased(final MouseEvent e) {

	}

	@Override
	public void keyPressed(final KeyEvent e) {

	}

	@Override
	public void keyReleased(final KeyEvent e) {

	}

	@Override
	public void keyTyped(final KeyEvent e) {

	}
}
