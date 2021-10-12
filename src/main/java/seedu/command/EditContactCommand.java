package seedu.command;

import seedu.contact.Contact;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;

public class EditContactCommand extends Command {
    String[] contactDetails;
    int contactIndex;

    public EditContactCommand(String[] contactDetails, int contactIndex) {
        this.contactDetails = contactDetails;
        this.contactIndex = contactIndex;
    }

    public void execute() {
        try {
            contactList.editContact(contactDetails, contactIndex);
            TextUi.editContactMessage(contactList.getContactAtIndex(contactIndex).getName());
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.invalidIndexMessage();
        }
    }
}
