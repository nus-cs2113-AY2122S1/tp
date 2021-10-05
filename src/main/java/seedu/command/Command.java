package seedu.command;

import seedu.contact.ContactList;

public abstract class Command {
    protected ContactList contactList;

    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    public abstract void execute();

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
