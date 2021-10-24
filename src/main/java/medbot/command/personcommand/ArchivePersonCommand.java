package medbot.command.personcommand;

import medbot.command.Command;

public abstract class ArchivePersonCommand extends Command {
    protected int personId;

    public ArchivePersonCommand(int personId) {
        this.personId = personId;
    }
}
