package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.InvalidFlagException;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;
import seedu.ui.UserInputTextUi;

import java.util.ArrayList;

public class EditContactCommand extends Command {
    public static final int NAME_INDEX = 0;
    public static final int GITHUB_INDEX = 1;
    public static final int LINKEDIN_INDEX = 2;
    public static final int TELEGRAM_INDEX = 3;
    public static final int TWITTER_INDEX = 4;
    public static final int EMAIL_INDEX = 5;
    String[] contactDetails;
    int contactIndex;

    public EditContactCommand(String[] contactDetails, int contactIndex) {
        this.contactDetails = contactDetails;
        this.contactIndex = contactIndex;
    }

    public void execute() {
        try {
            Contact preEditContact = duplicateContact(contactList.getContactAtIndex(contactIndex));
            Contact postEditContact = editTempContact(contactDetails,preEditContact);
            if (duplicateCatcher(postEditContact, contactList, contactIndex)) {
                TextUi.ignoreEditContact();
            } else {
                contactList.editContact(contactDetails, contactIndex);
                TextUi.editContactMessage(postEditContact);
            }
        } catch (IndexOutOfBoundsException | InvalidFlagException e) {
            ExceptionTextUi.invalidIndexMessage();
        }
    }

    public Boolean duplicateCatcher(Contact postEditContact, ContactList contactList, int contactIndex) {
        ArrayList<Integer> duplicatedIndex = new ArrayList<>();
        for (int i = 0; i < contactList.getListSize(); i++) {
            if (i == contactIndex) {
                continue;
            }
            Contact currentContact = contactList.getContactAtIndex(i);
            if (duplicateField(postEditContact.getName(), currentContact.getName())) {
                duplicatedIndex.add(i);
                continue;
            }
            if (postEditContact.getGithub() != null && currentContact.getGithub() != null) {
                if (duplicateField(postEditContact.getGithub(), currentContact.getGithub())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (postEditContact.getEmail() != null && currentContact.getEmail() != null) {
                if (duplicateField(postEditContact.getEmail(), currentContact.getEmail())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (postEditContact.getTelegram() != null && currentContact.getTelegram() != null) {
                if (duplicateField(postEditContact.getTelegram(), currentContact.getTelegram())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (postEditContact.getLinkedin() != null && currentContact.getLinkedin() != null) {
                if (duplicateField(postEditContact.getLinkedin(), currentContact.getLinkedin())) {
                    duplicatedIndex.add(i);
                    continue;
                }
            }
            if (postEditContact.getTwitter() != null && currentContact.getTwitter() != null) {
                if (duplicateField(postEditContact.getTwitter(), currentContact.getTwitter())) {
                    duplicatedIndex.add(i);
                }
            }
        }
        if (!duplicatedIndex.isEmpty()) {
            TextUi.confirmEditDuplicateMessage(duplicatedIndex, contactList);
            String userEditConfirmation = UserInputTextUi.getUserConfirmation();
            return userEditConfirmation.equalsIgnoreCase("n");
        }
        return false;
    }

    public Contact duplicateContact(Contact contact) {
        String name = contact.getName();
        String github = contact.getGithub();
        String linkedin = contact.getLinkedin();
        String telegram = contact.getTelegram();
        String twitter = contact.getTwitter();
        String email = contact.getEmail();
        return new Contact(name, github, linkedin, telegram, twitter, email);
    }

    public Boolean duplicateField(String input, String saved) {
        return stringCleaner(saved).equals(stringCleaner(input));
    }

}
