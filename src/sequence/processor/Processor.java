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
	public Processor()
	{
		this.didCommands=new Stack<ICommand>();
		this.unDidCommands=new Stack<ICommand>();
	}
	
	/**
	 * Do a command.
	 *
	 * @param command the command
	 */
	public void Do(ICommand command)
	{
		System.out.println("Do : " + command.getClass().toString());
		command.Do();
		didCommands.push(command);
	}
	
	/**
	 * Undo a command.
	 */
	public void Undo()
	{
		ICommand command = didCommands.pop();
		command.Undo();
		unDidCommands.push(command);
	}
	
	/**
	 * Redo a command.
	 */
	public void Redo()
	{
		
		ICommand command = unDidCommands.pop();
		command.Do();
		didCommands.push(command);
	}
}
