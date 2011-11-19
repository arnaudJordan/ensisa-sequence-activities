/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence.processor;

import sequence.model.activity.Activity;

/**
 *
 * @author jordan
 */
public class ActivityChange extends Command {
	private Activity newModel;
	
	public ActivityChange(Activity model, Activity newModel)
	{
		this.model=model;
		this.newModel=newModel;
		this.undo=new ActivityChange(newModel, model, this);
	}
	public ActivityChange(Activity model, Activity newModel, ActivityChange undo) {
		this.model=model;
		this.newModel=newModel;
		this.undo=undo;
	}
	@Override
	public void Do() {
		Activity oldmodel = (Activity) this.model;
		Activity tempModel = new Activity(oldmodel);
		oldmodel.setNote(newModel.getNote());
	}
}
