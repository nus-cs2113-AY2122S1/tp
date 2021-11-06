package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactList;

//@@author marcusbory
public abstract class Command {
    protected ContactList contactList;
    protected Contact personalContact;

    /**
     * Sets the current contact list as a context before proceeding with any command execution.
     * @param contactList Current ContactList to be set
     */
    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    public void setPersonalContact(Contact personalContact) {
        this.personalContact = personalContact;
    }

    public abstract void execute();

    /**
     * Returns true if the Command given is an ExitCommand
     * @return boolean True if ExitCommand is given by user
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
