package sequence.processor;

import java.util.Stack;

public class Processor {
	private Stack<Command> didCommands;
	private Stack<Command> unDidCommands;
	
	public Processor()
	{
		this.didCommands=new Stack<Command>();
		this.unDidCommands=new Stack<Command>();
	}
	public void Do(Command command)
	{
		command.Do();
		didCommands.push(command);
		System.out.println("Do");
	}
	public void Undo()
	{
		Command command = didCommands.pop();
		command.Undo();
		unDidCommands.push(command);
		System.out.println("unDo");
	}
	public void Redo()
	{
		Command command = unDidCommands.pop();
		command.Do();
		didCommands.push(command);
	}
}
