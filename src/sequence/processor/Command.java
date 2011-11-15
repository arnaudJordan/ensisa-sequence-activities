package sequence.processor;

import sequence.mvc.Model;

public abstract class Command {
	protected Command undo;
	protected Model model;
	
	public abstract void Do();
	public void Undo()
	{
		undo.Do();
	}
}
