package sequence.processor.command;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class that allows multiple commands to be processed at once.
 */
public class CommandList implements ICommand {
	
	/** The commands. */
	private final List<ICommand> commands;

	/**
	 * Instantiates a new command list.
	 */
	public CommandList() {
		this(new ArrayList<ICommand>());
	}

	/**
	 * Instantiates a new command list.
	 *
	 * @param commands the commands
	 */
	public CommandList(final List<ICommand> commands) {
		this.commands = commands;
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.ICommand#Do()
	 */
	@Override
	public void Do() {
		for (final ICommand command : commands)
			command.Do();
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.ICommand#Undo()
	 */
	@Override
	public void Undo() {
		for (final ICommand command : commands)
			command.Undo();
	}

	/**
	 * Adds the.
	 *
	 * @param command the command
	 */
	public void Add(final ICommand command) {
		commands.add(command);
	}
}
