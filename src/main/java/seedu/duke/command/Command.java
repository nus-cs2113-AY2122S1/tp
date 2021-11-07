package seedu.duke.command;

import seedu.duke.exceptions.LotsException;
import seedu.duke.PeopleManager;

public class Command {

    protected PeopleManager peopleManager;

    public Command() {
    }

    /**
     * Initialise the person list to be edited by the user.
     */
    public void setData(PeopleManager manager) {
        peopleManager = manager;
    }

    /**
     * Execute different set of instructions in the subclass based on the user's command.
     */
    public void execute() throws LotsException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     * Execute different set of instructions in the subclass based on the contents of the file.
     */
    public void executeFromFile() throws LotsException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

}
