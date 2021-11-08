package medbot.command;

import medbot.Scheduler;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;

public abstract class Command {
    /**
     * Returns if the command type is the exitCommand.
     *
     * @return boolean value of whether the command is the exitCommand
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Abstract method that executes the command.
     *
     * @param scheduler the scheduler that will be read or modified
     * @param ui Ui class instance used to print output messages
     */
    public abstract void execute(Scheduler scheduler, Ui ui) throws MedBotException;
}
