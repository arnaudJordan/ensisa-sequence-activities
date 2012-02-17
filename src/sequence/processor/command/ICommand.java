package sequence.processor.command;

public interface ICommand {

	public abstract void Do();

	public abstract void Undo();

}