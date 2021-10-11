package seedu.command;

import seedu.contact.Contact;
import seedu.ui.TextUi;

public class AddContactCommand extends Command {
    private final String name;
    private final String github;
    private final String linkedin;
    private final String telegram;
    private final String twitter;
    private final String email;

    public String getLinkedin() {
        return linkedin;
    }

    public String getTelegram() {
        return telegram;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getEmail() {
        return email;
    }

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
