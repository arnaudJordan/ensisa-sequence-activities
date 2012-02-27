package sequence.processor.command;

/**
 * The null command.
 */
public class Null extends Command {

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Do()
	 */
	@Override
	public void Do() {
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Undo()
	 */
	@Override
	public void Undo() {
	}

}
