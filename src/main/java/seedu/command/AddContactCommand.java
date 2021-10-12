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

    public AddContactCommand(String name, String github, String linkedin, String telegram, String twitter,
                             String email) {
        this.name = name;
        this.github = github;
        this.linkedin = linkedin;
        this.telegram = telegram;
        this.twitter = twitter;
        this.email = email;
    }

    public void execute() {
        Contact addedContact = new Contact(name, github, linkedin, telegram, twitter, email);
        contactList.addContact(addedContact);
        TextUi.addContactMessage(addedContact, contactList.getListSize());
    }
}
