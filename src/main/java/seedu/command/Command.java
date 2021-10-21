package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactList;

public abstract class Command {
    protected ContactList contactList;
    protected Contact personalContact;

    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    public void setPersonalContact(Contact personalContact) {
        this.personalContact = personalContact;
    }

    public abstract void execute();

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
