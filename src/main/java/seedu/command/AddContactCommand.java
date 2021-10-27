//@@author marcusbory
package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;

import java.util.ArrayList;
import static seedu.parser.ContactParser.NUMBER_OF_FIELDS;

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

    //@@author ashrafjfr
    private boolean hasDuplicates(Contact addedContact, ContactList contactList) throws InvalidFlagException {
        ArrayList<Integer> duplicatedIndex = new ArrayList<>();
        String[] addedContactDetails = addedContact.getContactStringArray();
        for (int i = 0; i < contactList.getListSize(); i++) {
            String[] currentContactDetails = contactList.getContactAtIndex(i).getContactStringArray();
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
            return !userAddConfirmation.equalsIgnoreCase("y");
        }
        return false;
    }

    private boolean hasDuplicateField(String input, String saved) {
        return stringCleaner(saved).equals(stringCleaner(input));
    }

    private String stringCleaner(String input) {
        return input.replace(" ", "").toLowerCase();
    }
    //@@author
}
