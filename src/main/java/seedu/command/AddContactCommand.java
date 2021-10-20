package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;

import java.util.ArrayList;

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

    public AddContactCommand(String[] details) {
        this.name = details[DetailType.NAME.getIndex()];
        this.github = details[DetailType.GITHUB.getIndex()];
        this.linkedin = details[DetailType.LINKEDIN.getIndex()];
        this.telegram = details[DetailType.TELEGRAM.getIndex()];
        this.twitter = details[DetailType.TWITTER.getIndex()];
        this.email = details[DetailType.EMAIL.getIndex()];
    }

    public void execute() {
        Contact addedContact = new Contact(name, github, linkedin, telegram, twitter, email);
        if (duplicateCatcher(addedContact, contactList)) {
            TextUi.ignoreAddContact();
        } else {
            contactList.addContact(addedContact);
            TextUi.addContactMessage(addedContact, contactList.getListSize());
        }
    }

    public Boolean duplicateCatcher(Contact addedContact, ContactList contactList) {
        ArrayList<Integer> duplicatedIndex = new ArrayList<>();
        for (int i = 0; i < contactList.getListSize(); i++) {
            Contact currentContact = contactList.getContactAtIndex(i);
            if (duplicateField(addedContact.getName(), currentContact.getName())) {
                duplicatedIndex.add(i);
                continue;
            }
            if (addedContact.getGithub() != null && currentContact.getGithub() != null) {
                if (duplicateField(addedContact.getGithub(), currentContact.getGithub())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (addedContact.getEmail() != null && currentContact.getEmail() != null) {
                if (duplicateField(addedContact.getEmail(), currentContact.getEmail())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (addedContact.getTelegram() != null && currentContact.getTelegram() != null) {
                if (duplicateField(addedContact.getTelegram(), currentContact.getTelegram())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (addedContact.getLinkedin() != null && currentContact.getLinkedin() != null) {
                if (duplicateField(addedContact.getLinkedin(), currentContact.getLinkedin())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (addedContact.getTwitter() != null && currentContact.getTwitter() != null) {
                if (duplicateField(addedContact.getTwitter(), currentContact.getTwitter())) {
                    duplicatedIndex.add(i);
                }
            }
        }
        if (!duplicatedIndex.isEmpty()) {
            TextUi.confirmAddDuplicateMessage(duplicatedIndex, contactList);
            String userAddConfirmation = UserInputTextUi.getUserConfirmation();
            return userAddConfirmation.equalsIgnoreCase("n");
        }
        return false;
    }
    }
}
