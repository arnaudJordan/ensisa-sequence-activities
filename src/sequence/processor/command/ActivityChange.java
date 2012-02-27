package sequence.processor.command;

import sequence.model.activity.Activity;

/**
 * The command that is used to process a change in an activitie's model.
 */
public class ActivityChange extends Command {
	
	/** The new model. */
	private final Activity newModel;

	/**
	 * Instantiates a new activity change.
	 *
	 * @param model the model
	 * @param newModel the new model
	 */
	public ActivityChange(final Activity model, final Activity newModel) {
		this.model = model;
		this.newModel = newModel;
		undo = new ActivityChange(model, new Activity(model), this);
	}

	/**
	 * Instantiates a new activity change.
	 *
	 * @param model the model
	 * @param newModel the new model
	 * @param undo the undo
	 */
	public ActivityChange(final Activity model, final Activity newModel,
			final ActivityChange undo) {
		this.model = model;
		this.newModel = newModel;
		this.undo = undo;
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Do()
	 */
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
