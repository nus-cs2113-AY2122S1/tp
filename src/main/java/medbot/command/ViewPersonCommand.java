package medbot.command;


public abstract class ViewPersonCommand extends Command {
    protected int personId = 0;

    public ViewPersonCommand(int personId) {
        this.personId = personId;
    }
}
