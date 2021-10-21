package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactComparator;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;

import java.util.ArrayList;
import java.util.Arrays;

public class AddContactCommand extends Command {
    private final String name;
    private final String github;
    private final String linkedin;
    private final String telegram;
    private final String twitter;
    private final String email;
    public static final int NUMBER_OF_FIELDS = 6;
    public static final int NAME_INDEX = 0;
    public static final int GITHUB_INDEX = 1;
    public static final int LINKEDIN_INDEX = 2;
    public static final int TELEGRAM_INDEX = 3;
    public static final int TWITTER_INDEX = 4;
    public static final int EMAIL_INDEX = 5;

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
        try {
            Contact addedContact = new Contact(name, github, linkedin, telegram, twitter, email);
            if (hasDuplicates(addedContact, contactList)) {
                TextUi.ignoreContact("add");
            } else {
                contactList.addContact(addedContact);
                TextUi.addContactMessage(addedContact, contactList.getListSize());
            }
        } catch (InvalidFlagException e) {
            ExceptionTextUi.invalidIndexMessage();
        }
    }

    private Boolean hasDuplicates(Contact addedContact, ContactList contactList) throws InvalidFlagException {
        ArrayList<Integer> duplicatedIndex = new ArrayList<>();
        String[] addedContactDetails = extractContactDetails(addedContact);
        for (int i = 0; i < contactList.getListSize(); i++) {
            String[] currentContactDetails = extractContactDetails(contactList.getContactAtIndex(i));
            for (int j = 0; j < NUMBER_OF_FIELDS; j++) {
                if (addedContactDetails[j] != null && currentContactDetails[j] != null) {
                    if (hasDuplicateField(addedContactDetails[j], currentContactDetails[j])) {
                        duplicatedIndex.add(i);
                        break;
                    }
                }
            }
        }
        if (!duplicatedIndex.isEmpty()) {
            TextUi.confirmDuplicateMessage(duplicatedIndex, contactList, "add");
            String userAddConfirmation = UserInputTextUi.getUserConfirmation();
            return userAddConfirmation.equalsIgnoreCase("n");
        }
        return false;
    }

    public Boolean duplicateField(String input, String saved) {
        return stringCleaner(saved).equals(stringCleaner(input));
    }

    public String stringCleaner(String input) {
        return input.replace(" ", "").toLowerCase();
    }
}
