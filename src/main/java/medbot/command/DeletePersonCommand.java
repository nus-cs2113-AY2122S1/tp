package medbot.command;


public abstract class DeletePersonCommand extends Command {
    protected int personId;

    public DeletePersonCommand(int personId) {
        this.personId = personId;
    }
}
