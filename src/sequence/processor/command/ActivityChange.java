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
		final Activity oldmodel = (Activity) model;
		oldmodel.setState(newModel.getState());
		oldmodel.setType(newModel.getType());
		oldmodel.setDiscipline(newModel.getDiscipline());
		oldmodel.setActuator(newModel.getActuator());
		oldmodel.setAction(newModel.getAction());
		oldmodel.setUsedInstrument(newModel.getUsedInstrument());
		oldmodel.setTreatedStructure(newModel.getTreatedStructure());
		oldmodel.setNote(newModel.getNote());
		oldmodel.setActivitytime(newModel.getActivitytime());
	}
}
