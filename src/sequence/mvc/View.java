package sequence.mvc;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public abstract class View extends JComponent {

	private Model model;
	private Renderer renderer;
	private RenderingModel renderingModel;

	public View(Model model) {
		initialize(model);
	}

	private void initialize(Model model) {
		this.model = model;
		this.setRenderer(null);
		this.setRenderingModel(null);
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
	
	public RenderingModel getRenderingModel() {
		return renderingModel;
	}

	public void setRenderingModel(RenderingModel renderingModel) {
		this.renderingModel = renderingModel;
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
}
