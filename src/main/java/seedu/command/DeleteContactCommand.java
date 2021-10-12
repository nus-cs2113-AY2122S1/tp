package seedu.command;

import seedu.contact.Contact;
import seedu.parser.FailedCommandType;
import seedu.parser.IndexParser;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;
import seedu.command.FailedCommand;

public class DeleteContactCommand extends Command {
    private final int deletedIndex;
    private final String confirmMessage = "";

    public DeleteContactCommand(int deletedIndex) {
        this.deletedIndex = deletedIndex;
    }

    public int getDeletedIndex() {
        return deletedIndex;
    }

    private void deleteOnConfirmation(Contact deletedContact) {
        // ask for confirmation to delete from user
        String userDeleteConfirmation = TextUi.getUserDeleteConfirmation(deletedContact, deletedIndex);
        if (userDeleteConfirmation.equalsIgnoreCase("y")) {
            this.contactList.deleteContact(deletedIndex);
            TextUi.deleteContactMessage(deletedContact.getName(), contactList.getListSize());
        } else {
            TextUi.cancelDeleteContactMessage();
        }
    }

    public void execute() {
        try {
            // throws IndexOutOfBoundsException if index is outside of the range
            Contact deletedContact = IndexParser.getContactFromIndex(deletedIndex, contactList);
            // Index is within range, insert assert here
            deleteOnConfirmation(deletedContact);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
