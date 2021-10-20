package medbot.command;


public abstract class FindPersonCommand extends Command {
    protected String[] parameters;

    public FindPersonCommand(String[] parameters) {
        this.parameters = parameters;
    }
}
