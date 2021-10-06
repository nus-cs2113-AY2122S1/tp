package seedu.command;

import seedu.contact.Contact;
import seedu.ui.TextUi;

public class DeleteContactCommand extends Command {
    private final int deletedIndex;

    public DeleteContactCommand(int deletedIndex) {
        this.deletedIndex = deletedIndex;
    }

    public int getDeletedIndex() {
        return deletedIndex;
    }

    public void execute() {
        try {
            Contact deletedContact = contactList.getContactAtIndex(deletedIndex);
            this.contactList.deleteContact(deletedIndex);
            TextUi.deleteContactMessage(deletedContact.getName(), contactList.getListSize());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            TextUi.numOutOfRangeMessage(contactList.getListSize() - 1);
        }
    }
}
