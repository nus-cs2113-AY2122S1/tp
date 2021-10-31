//@@author ashrafjfr

package seedu.command;

import seedu.contact.Contact;
import seedu.parser.IndexParser;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;

public class ViewContactCommand extends Command {
    private final int contactIndex;

    public ViewContactCommand(int contactIndex) {
        this.contactIndex = contactIndex;
    }

    public int getIndex() {
        return contactIndex;
    }

    public void execute() {
        try {
            Contact viewingContact = IndexParser.getContactFromIndex(contactIndex, contactList);
            assert contactIndex >= -1 && contactIndex < contactList.getListSize();
            TextUi.viewContactMessage(viewingContact, contactIndex);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
