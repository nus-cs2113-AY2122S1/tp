package medbot.command.personcommand;


import medbot.command.Command;

public abstract class ViewPersonCommand extends Command {
    protected int personId;

    public ViewPersonCommand(int personId) {
        this.personId = personId;
    }
}
