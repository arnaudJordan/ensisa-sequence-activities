package sequence.processor.command;

import sequence.mvc.Model;

public abstract class Command implements ICommand {
	protected ICommand undo;
	protected Model model;

	@Override
	public abstract void Do();

	@Override
	public void Undo() {
		undo.Do();
	}
}
