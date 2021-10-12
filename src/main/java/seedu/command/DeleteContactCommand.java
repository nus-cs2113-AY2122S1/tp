package seedu.command;

import seedu.contact.Contact;
import seedu.parser.FailedCommandType;
import seedu.ui.TextUi;
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

    public void deleteContact() {
        try {
            Contact deletedContact = contactList.getContactAtIndex(deletedIndex);
            this.contactList.deleteContact(deletedIndex);
            TextUi.deleteContactMessage(deletedContact.getName(), contactList.getListSize());
        } catch (IndexOutOfBoundsException e) {
            TextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }

    public void execute() {
        Contact deletedContact = contactList.getContactAtIndex(deletedIndex);
        // ask for confirmation to delete from user
        String userDeleteConfirmation = TextUi.getUserDeleteConfirmation(deletedContact, deletedIndex);
        if (userDeleteConfirmation.equalsIgnoreCase("y")) {
            deleteContact();
        } else {
            TextUi.cancelDeleteContactMessage();
        }
    }
}
