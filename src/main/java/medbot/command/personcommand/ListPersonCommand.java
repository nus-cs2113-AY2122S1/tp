package medbot.command.personcommand;

import medbot.command.Command;

public abstract class ListPersonCommand extends Command {
    private boolean getArchived;

    public ListPersonCommand(boolean getArchived) {
        this.getArchived = getArchived;
    }

    public boolean getArchived() {
        return getArchived;
    }
}
