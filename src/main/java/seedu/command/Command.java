package seedu.command;

import seedu.contact.ContactList;
import seedu.contact.PersonalContact;

public abstract class Command {
    protected ContactList contactList;
    protected PersonalContact personalContact;

    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    public void setPersonalContact(PersonalContact personalContact) {
        this.personalContact = personalContact;
    }

    public abstract void execute();

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
