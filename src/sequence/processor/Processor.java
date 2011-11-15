package sequence.processor;

import java.util.Stack;

public class Processor {
	private Stack<Command> commands;
	
	public Processor()
	{
		this.commands=new Stack<Command>();
	}
	public void Do(Command command)
	{
		commands.push(command);
		command.Do();
	}
	public void Undo()
	{
		commands.pop().Undo();
	}
}
