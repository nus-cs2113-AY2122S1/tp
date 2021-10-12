package seedu.command;

import seedu.contact.Contact;
import seedu.parser.FailedCommandType;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;
import seedu.command.FailedCommand;

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
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
