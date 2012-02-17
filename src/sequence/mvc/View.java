package sequence.mvc;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public abstract class View extends JComponent implements ModelListener {
	private static final long serialVersionUID = 1L;

	private Model model;
	private Renderer renderer;
	private RenderingModel renderingModel;

	public View(final Model model) {
		initialize(model);
	}

	private void initialize(final Model model) {
		setModel(model);
		// this.setRenderer(null);
		// this.setRenderingModel(null);
	}

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

	public Model getModel() {
		return model;
	}

	public void setRenderer(final Renderer renderer) {
		this.renderer = renderer;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderingModel(final RenderingModel renderingModel) {
		if (renderingModel == null)
			return;
		if (this.renderingModel != null)
			this.renderingModel.removeModelListener(this);
		this.renderingModel = renderingModel;
		this.renderingModel.addModelListener(this);
	}

	public RenderingModel getRenderingModel() {
		return renderingModel;
	}

	@Override
	public Dimension getMinimumSize() {
		return renderer.getMinimumSize();
	}

	@Override
	public Dimension getPreferredSize() {
		return renderer.getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return renderer.getMaximumSize();
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (model != null && renderer != null)
			renderer.renderView((Graphics2D) g);
	}

	@Override
	public void modelChanged(final Model m) {
		this.repaint();
	}

	public BufferedImage createImage() {
		final BufferedImage image = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		paint(image.getGraphics());
		return image;
	}

	@Override
	public BufferedImage createImage(final int width, final int height) {
		final BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_4BYTE_ABGR);
		paint(image.getGraphics());
		return image;
	}
}
