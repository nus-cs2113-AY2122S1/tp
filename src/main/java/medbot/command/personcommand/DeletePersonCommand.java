package medbot.command.personcommand;


import medbot.command.Command;

public abstract class DeletePersonCommand extends Command {
    protected int personId;

    public DeletePersonCommand(int personId) {
        this.personId = personId;
    }
}
