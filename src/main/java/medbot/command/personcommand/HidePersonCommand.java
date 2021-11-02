package medbot.command.personcommand;

import medbot.command.Command;

public abstract class HidePersonCommand extends Command {
    protected int personId;

    public HidePersonCommand(int personId) {
        this.personId = personId;
    }
}
