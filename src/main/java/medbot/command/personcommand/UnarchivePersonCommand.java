package medbot.command.personcommand;

import medbot.command.Command;

public abstract class UnarchivePersonCommand extends Command {
    protected int personId;

    public UnarchivePersonCommand(int personId) {
        this.personId = personId;
    }
}
