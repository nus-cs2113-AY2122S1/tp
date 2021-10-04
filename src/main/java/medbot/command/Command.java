package medbot.command;

import medbot.MedBotException;
import medbot.PatientList;
import medbot.Ui;

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
     * @param patientList the patientList that will be modified
     */
    public abstract void execute(PatientList patientList, Ui ui) throws MedBotException;
}
