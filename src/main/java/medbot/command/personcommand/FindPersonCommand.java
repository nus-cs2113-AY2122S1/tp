package medbot.command.personcommand;


import medbot.command.Command;

public abstract class FindPersonCommand extends Command {
    protected String[] parameters;

    public FindPersonCommand(String[] parameters) {
        this.parameters = parameters;
    }
}
