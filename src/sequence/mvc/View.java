package sequence.mvc;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * The Class View.
 */
public abstract class View extends JComponent implements ModelListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The model. */
	private Model model;
	
	/** The renderer. */
	private Renderer renderer;
	
	/** The rendering model. */
	private RenderingModel renderingModel;

	/**
	 * Instantiates a new view.
	 *
	 * @param model the model
	 */
	public View(final Model model) {
		initialize(model);
	}

	/**
	 * Initialize.
	 *
	 * @param model the model
	 */
	private void initialize(final Model model) {
		setModel(model);
		// this.setRenderer(null);
		// this.setRenderingModel(null);
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(final Model model) {
		if (model == null)
			return;
		if (this.model != null)
			this.model.removeModelListener(this);
		final Model oldModel = this.model;
		this.model = model;
		this.model.addModelListener(this);
		if (oldModel != null && oldModel != model)
			modelChanged(model);
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
	 * Sets the renderer.
	 *
	 * @param renderer the new renderer
	 */
	public void setRenderer(final Renderer renderer) {
		this.renderer = renderer;
	}

	/**
	 * Gets the renderer.
	 *
	 * @return the renderer
	 */
	public Renderer getRenderer() {
		return renderer;
	}

	/**
	 * Sets the rendering model.
	 *
	 * @param renderingModel the new rendering model
	 */
	public void setRenderingModel(final RenderingModel renderingModel) {
		if (renderingModel == null)
			return;
		if (this.renderingModel != null)
			this.renderingModel.removeModelListener(this);
		this.renderingModel = renderingModel;
		this.renderingModel.addModelListener(this);
	}

	/**
	 * Gets the rendering model.
	 *
	 * @return the rendering model
	 */
	public RenderingModel getRenderingModel() {
		return renderingModel;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getMinimumSize()
	 */
	@Override
	public Dimension getMinimumSize() {
		return renderer.getMinimumSize();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return renderer.getPreferredSize();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getMaximumSize()
	 */
	@Override
	public Dimension getMaximumSize() {
		return renderer.getMaximumSize();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (model != null && renderer != null)
			renderer.renderView((Graphics2D) g);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.ModelListener#modelChanged(sequence.mvc.Model)
	 */
	@Override
	public void modelChanged(final Model m) {
		this.repaint();
	}

	/**
	 * Creates the image.
	 *
	 * @return the buffered image
	 */
	public BufferedImage createImage() {
		final BufferedImage image = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		paint(image.getGraphics());
		return image;
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#createImage(int, int)
	 */
	@Override
	public BufferedImage createImage(final int width, final int height) {
		final BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_4BYTE_ABGR);
		paint(image.getGraphics());
		return image;
	}
}
