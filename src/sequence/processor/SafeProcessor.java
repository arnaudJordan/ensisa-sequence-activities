package sequence.processor;

/**
 * SafeProcessor is a <code>Processor</code> wich check if
 * <code>didCommands</code> and <code>unDidCommans</code> is empty before
 * <code>Undo()</code> or <code>Redo()</code>.
 * 
 * @see Processor
 */
public class SafeProcessor extends Processor {
	
	/* (non-Javadoc)
	 * @see sequence.processor.Processor#Undo()
	 */
	public void Undo()
	{
		if(didCommands.isEmpty()) return;
		super.Undo();
	}
	
	/* (non-Javadoc)
	 * @see sequence.processor.Processor#Redo()
	 */
	public void Redo()
	{
		if(unDidCommands.isEmpty()) return;
		super.Redo();
	}
}
