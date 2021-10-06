package seedu.command;

import seedu.contact.Contact;
import seedu.ui.TextUi;

public class ViewCommand extends Command{
    private final int index;

    public ViewCommand(int index) {
        this.index = index;
    }

    public void execute() {
        Contact viewingContact = contactList.getContactAtIndex(index);
        TextUi.viewContactMessage(viewingContact, index);
    }
}
