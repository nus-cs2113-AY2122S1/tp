package seedu.command;

import seedu.contact.Contact;
import seedu.parser.IndexParser;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;


public class DeleteContactCommand extends Command {
    private final int contactIndex;

    public DeleteContactCommand(int deletedIndex) {
        this.contactIndex = deletedIndex;
    }

    public int getDeletedIndex() {
        return contactIndex;
    }

    private void deleteOnConfirmation(Contact deletedContact) {
        // ask for confirmation to delete from user
        String userDeleteConfirmation = TextUi.getUserDeleteConfirmation(deletedContact, contactIndex);
        if (userDeleteConfirmation.equalsIgnoreCase("y")) {
            this.contactList.deleteContact(contactIndex);
            TextUi.deleteContactMessage(deletedContact.getName(), contactList.getListSize());
        } else {
            TextUi.cancelDeleteContactMessage();
        }
    }

    public void execute() {
        try {
            // throws IndexOutOfBoundsException if index is outside of the range
            Contact deletedContact = IndexParser.getContactFromIndex(contactIndex, contactList);
            deleteOnConfirmation(deletedContact);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
