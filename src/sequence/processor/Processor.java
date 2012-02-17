package sequence.processor;

import java.util.Stack;

import sequence.processor.command.ICommand;

/**
 * Processor which can execute a <code>Commande</code>.
 * 
 * @see command.Command
 */
public class Processor {

	/** The did commands. */
	protected Stack<ICommand> didCommands;

	/** The undid commands. */
	protected Stack<ICommand> unDidCommands;

	/**
	 * Instantiates a new processor.
	 */
	public Processor() {
		didCommands = new Stack<ICommand>();
		unDidCommands = new Stack<ICommand>();
	}

	/**
	 * Do a command.
	 * 
	 * @param command
	 *            the command
	 */
	public void Do(final ICommand command) {
		System.out.println("Do : " + command.getClass().toString());
		command.Do();
		didCommands.push(command);
	}

	/**
	 * Undo a command.
	 */
	public void Undo() {
		final ICommand command = didCommands.pop();
		command.Undo();
		unDidCommands.push(command);
	}

	/**
	 * Redo a command.
	 */
	public void Redo() {

		final ICommand command = unDidCommands.pop();
		command.Do();
		didCommands.push(command);
	}
}
