package sequence.processor;

public class SafeProcessor extends Processor {
	public void Undo()
	{
		if(didCommands.isEmpty()) return;
		super.Undo();
	}
	public void Redo()
	{
		if(unDidCommands.isEmpty()) return;
		super.Redo();
	}
}
