/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence.processor.command;

import sequence.model.activity.Activity;

/**
 * 
 * @author jordan
 */
public class ActivityChange extends Command {
	private final Activity newModel;

	public ActivityChange(final Activity model, final Activity newModel) {
		this.model = model;
		this.newModel = newModel;
		undo = new ActivityChange(model, new Activity(model), this);
	}

	public ActivityChange(final Activity model, final Activity newModel,
			final ActivityChange undo) {
		this.model = model;
		this.newModel = newModel;
		this.undo = undo;
	}

	@Override
	public void Do() {
		final Activity activity = (Activity) model;
		activity.setState(newModel.getState());
		activity.setType(newModel.getType());
		activity.setDiscipline(newModel.getDiscipline());
		activity.setActuator(newModel.getActuator());
		activity.setAction(newModel.getAction());
		activity.setUsedInstrument(newModel.getUsedInstrument());
		activity.setTreatedStructure(newModel.getTreatedStructure());
		activity.setNote(newModel.getNote());
		activity.setActivitytime(newModel.getActivitytime());
	}
}
