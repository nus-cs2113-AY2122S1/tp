package seedu.command;

import seedu.exception.InvalidFlagException;
import seedu.ui.ExceptionTextUi;

//@@author ng-andre
public class SearchContactCommand extends Command {
    String query;
    int detailIndex;

    public SearchContactCommand(String query, int detailIndex) {
        this.query = query;
        this.detailIndex = detailIndex;
    }

    public void execute() {
        try {
            contactList.searchContact(query, detailIndex);
        } catch (InvalidFlagException e) {
            ExceptionTextUi.invalidFlagMessage();
        }
    }
}
