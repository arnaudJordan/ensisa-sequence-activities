package sequence.processor;

public class SafeProcessor extends Processor {
	public void Undo()
	{
		if(didCommands.isEmpty()) return;
		Command command = didCommands.pop();
		command.Undo();
		unDidCommands.push(command);
	}
	public void Redo()
	{
		if(unDidCommands.isEmpty()) return;
		Command command = unDidCommands.pop();
		command.Do();
		didCommands.push(command);
	}
}
