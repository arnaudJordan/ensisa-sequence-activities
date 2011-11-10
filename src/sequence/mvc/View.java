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

	public View(Model model) {
		initialize(model);
	}

	private void initialize(Model model) {
		this.setModel(model);
		this.setRenderer(null);
		this.setRenderingModel(null);
	}
	
	public void setModel(Model model) {
		if(model == null) return;
		if(this.model != null)
			this.model.removeModelListener(this);
		this.model = model;
		this.model.addModelListener(this);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	public Renderer getRenderer() {
		return renderer;
	}
	
	public void setRenderingModel(RenderingModel renderingModel) {
		if(renderingModel == null) return;
		if(this.renderingModel != null)
			this.renderingModel.removeModelListener(this);
		this.renderingModel = renderingModel;
		this.renderingModel.addModelListener(this);
	}
	
	public RenderingModel getRenderingModel() {
		return renderingModel;
	}

	public Dimension getMinimumSize() {               
		return renderer.getMinimumSize();
    }

    public Dimension getPreferredSize() {
     	return renderer.getPreferredSize();
    }
    
    public Dimension getMaximumSize() {
    	return renderer.getMaximumSize();
    }
	
	public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	if (model != null && this.renderer != null)
    		this.renderer.renderView((Graphics2D)g);
    }
	
	public void modelChanged(Model m) {
		this.repaint();
	}
	public BufferedImage createImage()
	{
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		this.paint(image.getGraphics());
		return image;
	}
}
