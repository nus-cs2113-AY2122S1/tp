package seedu.command;

import seedu.ui.TextUi;

public class PersonalContactCommand extends Command {
    public void execute() {
        TextUi.viewPersonalContactMessage(this.personalContact);
    }
}
