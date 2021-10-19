package seedu.command;

import seedu.contact.Contact;
import seedu.parser.IndexParser;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;
import seedu.ui.UserInputTextUi;


public class DeleteContactCommand extends Command {
    private final int contactIndex;

    public DeleteContactCommand(int contactIndex) {
        this.contactIndex = contactIndex;
    }

    public int getDeletedIndex() {
        return contactIndex;
    }

    private void deleteOnConfirmation(Contact deletedContact) {
        // ask for confirmation to delete from user
        TextUi.confirmDeleteMessage(deletedContact, contactIndex);
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        if (userConfirmation.equalsIgnoreCase("y")) {
            this.contactList.deleteContact(contactIndex);
            String deletedName = deletedContact.getName();
            int contactListSize = contactList.getListSize();
            TextUi.deleteContactMessage(deletedName, contactListSize);
        } else {
            assert !userConfirmation.equalsIgnoreCase("y");
            TextUi.cancelDeleteContactMessage();
        }
    }

    public void execute() {
        try {
            // throws IndexOutOfBoundsException if index is outside of the range
            Contact deletedContact = IndexParser.getContactFromIndex(contactIndex, contactList);
            assert contactIndex >= 0 && contactIndex < contactList.getListSize();
            deleteOnConfirmation(deletedContact);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
