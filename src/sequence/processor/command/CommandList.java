package sequence.processor.command;

import java.util.ArrayList;
import java.util.List;

public class CommandList implements ICommand {
	private final List<ICommand> commands;

	public CommandList() {
		this(new ArrayList<ICommand>());
	}

	public CommandList(final List<ICommand> commands) {
		this.commands = commands;
	}

	@Override
	public void Do() {
		for (final ICommand command : commands)
			command.Do();
	}

	@Override
	public void Undo() {
		for (final ICommand command : commands)
			command.Undo();
	}

	public void Add(final ICommand command) {
		commands.add(command);
	}
}
