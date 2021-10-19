package medbot.command;

import medbot.exceptions.MedBotException;
import medbot.Ui;
import medbot.list.PersonList;

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
     * @param personList the patientList that will be modified
     */
    public abstract void execute(PersonList personList, Ui ui) throws MedBotException;
}
