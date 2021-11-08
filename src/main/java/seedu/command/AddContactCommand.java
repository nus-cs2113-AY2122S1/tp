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

    public AddContactCommand(String[] details) {
        this.name = details[DetailType.NAME.getIndex()];
        this.github = details[DetailType.GITHUB.getIndex()];
        this.linkedin = details[DetailType.LINKEDIN.getIndex()];
        this.telegram = details[DetailType.TELEGRAM.getIndex()];
        this.twitter = details[DetailType.TWITTER.getIndex()];
        this.email = details[DetailType.EMAIL.getIndex()];
    }

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

    /**
     * Executes the AddContactCommand with the supplied fields to add a new contact to the contact list.
     * This method also checks for existing duplicates in the contact list.
     */
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
    /**
     * This method takes in the addedContact and contactList and checks whether there are any contacts already in the
     * contactList that are duplicates to the addedContact. If there are duplicates, a confirmation message will be
     * output to confirm whether the user would like to still add the duplicated contact.
     *
     * @param addedContact Contact that user has just input to add
     * @param contactList List of current contacts
     * @throws InvalidFlagException If the flag given is not recognised
     */
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

    /**
     * Returns true if the input string is equal to the similar field of a saved contact indicating a
     * duplicate.
     *
     * @param input String of field that user has input to add
     * @param saved String of field that is already in the list of contacts
     * @return boolean
     */
    private boolean hasDuplicateField(String input, String saved) {
        return cleanString(saved).equals(cleanString(input));
    }

    /**
     * This method cleans a string by removing all spaces and converting each letter to lower case for ease of
     * comparison.
     *
     * @param input String of a contact detail
     * @return input String that is cleaned
     */
    private String cleanString(String input) {
        return input.replace(" ", "").toLowerCase();
    }
    //@@author
}
