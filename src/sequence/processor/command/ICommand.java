package sequence.processor.command;

/**
 * The Interface ICommand.
 */
public interface ICommand {

	/**
	 * Do.
	 */
	public abstract void Do();

	/**
	 * Undo.
	 */
	public abstract void Undo();

}