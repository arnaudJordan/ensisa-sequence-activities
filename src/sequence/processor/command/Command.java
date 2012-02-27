package sequence.processor.command;

import sequence.mvc.Model;

/**
 * The abstract class that every commands should extend.
 */
public abstract class Command implements ICommand {
	
	/** The undo. */
	protected ICommand undo;
	
	/** The model. */
	protected Model model;

	/* (non-Javadoc)
	 * @see sequence.processor.command.ICommand#Do()
	 */
	@Override
	public abstract void Do();

	/* (non-Javadoc)
	 * @see sequence.processor.command.ICommand#Undo()
	 */
	@Override
	public void Undo() {
		undo.Do();
	}
}
