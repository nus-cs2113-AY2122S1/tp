package seedu.command;

import seedu.contact.Contact;
import seedu.parser.IndexParser;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;

public class ViewCommand extends Command {
    private final int contactIndex;

    public ViewCommand(int contactIndex) {
        this.contactIndex = contactIndex;
    }

    public int getIndex() {
        return contactIndex;
    }

    public void execute() {
        try {
            Contact viewingContact = IndexParser.getContactFromIndex(contactIndex, contactList);
            TextUi.viewContactMessage(viewingContact, contactIndex);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
