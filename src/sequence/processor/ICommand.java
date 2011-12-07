package sequence.processor;

public interface ICommand {

	public abstract void Do();

	public abstract void Undo();

}