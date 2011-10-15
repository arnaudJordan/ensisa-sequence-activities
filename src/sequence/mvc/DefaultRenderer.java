package sequence.mvc;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class DefaultRenderer implements Renderer {
	
	private final static Dimension DEFAULT_DIMENSION = new Dimension(200,200);
    private View view;
    
    public DefaultRenderer(View view) {
        this.view = view;
    }
    
    public View getView() {
        return this.view;
    }
    
    public void setView(View view) {
        this.view = view;
    }

    public void renderView(Graphics2D g) { 
    	RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
        g.setRenderingHints(rh);
    }

    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }

    public Dimension getPreferredSize() {
        return DEFAULT_DIMENSION;
    }

    public Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
}
