package sequence.processor.command;

import java.util.ArrayList;
import java.util.List;

public class CommandList implements ICommand {
	private List<ICommand> commands;
	
	public CommandList()
	{
		this(new ArrayList<ICommand>());
	}
	public CommandList(List<ICommand> commands)
	{
		this.commands=commands;
	}
	@Override
	public void Do() {
		for(ICommand command : commands)
			command.Do();
	}
	@Override
	public void Undo() {
		for(ICommand command : commands)
			command.Undo();
	}
	
	public void Add(ICommand command)
	{
		this.commands.add(command);
	}
}
