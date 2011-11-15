package sequence.processor;

import sequence.mvc.Model;

public abstract class Command {
	private Command undo;
	private Model model;
	
	public abstract void Do();
	public void Undo()
	{
		undo.Do();
	}
}
