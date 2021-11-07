package seedu.command;

import seedu.ui.TextUi;

public class PersonalContactCommand extends Command {
    /**
     * This method executes the process to view details of the personal contact.
     */
    public void execute() {
        TextUi.viewPersonalContactMessage(this.personalContact);
    }
}
