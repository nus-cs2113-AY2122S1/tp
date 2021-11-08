package medbot.command.personcommand;

import medbot.command.Command;

public abstract class ShowPersonCommand extends Command {
    protected int personId;

    public ShowPersonCommand(int personId) {
        this.personId = personId;
    }
}
