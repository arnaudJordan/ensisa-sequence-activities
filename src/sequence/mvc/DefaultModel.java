package sequence.mvc;

import java.util.ArrayList;
import java.util.List;

public class DefaultModel implements Model
{
	private List<ModelListener> listeners;

    public DefaultModel()
    {
    	this.listeners = new ArrayList<ModelListener>();        
    }
        
    @Override
	public void addModelListener(ModelListener l)
    {
    	this.listeners.add(l);
    }

    @Override
	public void removeModelListener(ModelListener l)
    {
    	this.listeners.remove(l);
    }
        
    @Override
	public void modelChange()
    {
    	for(ModelListener m : this.listeners)
        	m.modelChanged(this);
    }
}