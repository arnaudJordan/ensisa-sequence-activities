package sequence.utilities;
 
import java.awt.*;
 
public class BetterSizeFlowLayout extends FlowLayout
{
    public BetterSizeFlowLayout()
    {
        super();
    }
 
    public BetterSizeFlowLayout(int align)
    {
        super(align);
    }
 
    public BetterSizeFlowLayout(int align, int hgap, int vgap)
    {
        super(align, hgap, vgap);
    }
 
    public Dimension minimumLayoutSize(Container target)
    {
    	//int width = contentMaximumWidth(target);
    	int width = target.getParent().getWidth()-10;
        return new Dimension(width, contentMaximumHeight(target, width));
    }
    
    public Dimension preferredLayoutSize(Container target)
    {
    	//int width=contentMaximumWidth(target);
    	int width = target.getParent().getWidth()-10;
    	int parentWidth = target.getParent().getWidth()-10;
    	if(parentWidth>width)
    		return new Dimension(parentWidth, contentMaximumHeight(target, width));
    	else
    		return new Dimension(width, contentMaximumHeight(target, width));
    }
    private int contentMaximumWidth(Container target)
    {
    	int width = 0; 
    	for(int i=0;i< target.getComponentCount();i++)
    	{
    		if(width<target.getComponent(i).getWidth())
    			width = target.getComponent(i).getWidth();
    	}
    	return width;
    }
    private int contentMaximumHeight(Container target, int maxWidth)
    {
    	int heith = 0; 
    	int lineWidth=0;
		for(int i=0;i< target.getComponentCount();i++)
    	{
    		int width=target.getComponent(i).getWidth();
    		if(lineWidth==0)
    			heith += target.getComponent(i).getHeight() + getVgap();
    		
    		lineWidth+=width + getHgap();
    		
    		if(lineWidth>=maxWidth)
    			lineWidth=0;
    	}
    	return heith;
    }
}