//@@author lezongmun

package seedu.command;

import org.w3c.dom.Text;
import seedu.contact.Contact;
import seedu.parser.IndexParser;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;
import seedu.ui.UserInputTextUi;


public class DeleteContactCommand extends Command {
    private static String ACKNOWLEDGE_DELETE = "y";
    private static int DELETE_ALL_CONTACTS = -2;

    private final int contactIndex;

    public DeleteContactCommand(int contactIndex) {
        this.contactIndex = contactIndex;
    }

    public int getDeletedIndex() {
        return contactIndex;
    }


    private void deleteSelectedContact() throws IndexOutOfBoundsException {
        // throws IndexOutOfBoundsException if index is outside of the range
        Contact deletedContact = IndexParser.getContactFromIndex(contactIndex, contactList);
        // index must be within range since no exceptions thrown
        assert contactIndex >= 0 && contactIndex < contactList.getListSize();
        // ask for confirmation to delete from user
        TextUi.confirmDeleteContactMessage(deletedContact, contactIndex);
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        if (userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE)) {
            this.contactList.deleteContact(contactIndex);
            String deletedName = deletedContact.getName();
            int contactListSize = contactList.getListSize();
            TextUi.deleteContactMessage(deletedName, contactListSize);
        } else {
            assert !userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE);
            TextUi.cancelDeleteContactMessage();
        }
    }

    private void deleteAllContacts() {
        TextUi.confirmDeleteAllMessage();
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        if (userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE)) {
            int contactListSize = contactList.getListSize();
            this.contactList.deleteAllContacts();
            TextUi.deleteAllContactsMessage(contactListSize);
        } else {
            assert !userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE);
            TextUi.cancelDeleteContactMessage();
        }
    }

    public void execute() {
        try {
            if (contactIndex == DELETE_ALL_CONTACTS) {
                deleteAllContacts();
            } else {
                deleteSelectedContact();
            }
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
