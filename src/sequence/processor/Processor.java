package sequence.processor;

import java.util.Stack;

public class Processor {
	protected Stack<ICommand> didCommands;
	protected Stack<ICommand> unDidCommands;
	
	public Processor()
	{
		this.didCommands=new Stack<ICommand>();
		this.unDidCommands=new Stack<ICommand>();
	}
	public void Do(ICommand command)
	{
		command.Do();
		didCommands.push(command);
	}
	public void Undo()
	{
		ICommand command = didCommands.pop();
		command.Undo();
		unDidCommands.push(command);
	}
	public void Redo()
	{
		ICommand command = unDidCommands.pop();
		command.Do();
		didCommands.push(command);
	}
}
