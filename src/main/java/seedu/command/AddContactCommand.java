package seedu.command;

import seedu.contact.Contact;
import seedu.ui.TextUi;

public class AddContactCommand extends Command {
    private final String name;
    private final String github;

    public String getName() {
        return name;
    }

    public String getGithub() {
        return github;
    }

    public AddContactCommand(String name, String github) {
        this.name = name;
        this.github = github;
    }

    public void execute() {
        Contact addedContact = new Contact(name, github);
        contactList.addContact(addedContact);
        TextUi.addContactMessage(name, contactList.getListSize());
    }
}
