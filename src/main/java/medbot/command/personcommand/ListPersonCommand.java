package medbot.command.personcommand;

import medbot.command.Command;

public abstract class ListPersonCommand extends Command {
    private boolean getHidden;

    public ListPersonCommand(boolean getHidden) {
        this.getHidden = getHidden;
    }

    public boolean getHidden() {
        return getHidden;
    }
}
