package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.InvalidFlagException;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;

import java.util.ArrayList;


public class EditContactCommand extends Command {
    public static final int PERSONAL_CONTACT_INDEX = -1;
    private static final int ALL_CONTACTS_INDEX = -2;
    public static final int NUMBER_OF_FIELDS = 6;
    public static final String EDIT_TYPE = "edit";
    String[] contactDetails;
    int contactIndex;

    public EditContactCommand(String[] contactDetails, int contactIndex) {
        this.contactDetails = contactDetails;
        this.contactIndex = contactIndex;
    }

    public void execute() {
        try {
            if (contactIndex == PERSONAL_CONTACT_INDEX) {
                updatePersonalContact();
            } else if (contactIndex == ALL_CONTACTS_INDEX) {
                ExceptionTextUi.missingIndexEditMessage();
            } else {
                Contact editedContact = duplicateContact(contactList.getContactAtIndex(contactIndex));
                editedContact.editContact(contactDetails);
                handleDuplicates(editedContact);
            }
        } catch (IndexOutOfBoundsException e) {
            //triggerred when inputs are "edit <wrong_index> <proper flags>"
            ExceptionTextUi.numOutOfRangeEditMessage(contactList.getListSize());
        }
    }

    private void updatePersonalContact() {
        personalContact.editContact(contactDetails);
        TextUi.editPersonalContactMessage(personalContact);
    }

    private void handleDuplicates(Contact postEditContact) throws IndexOutOfBoundsException {
        if (hasDuplicates(postEditContact, contactList, contactIndex)) {
            TextUi.ignoreContact(EDIT_TYPE);
        } else {
            updateContact(postEditContact);
        }
    }

    private void updateContact(Contact postEditContact) throws IndexOutOfBoundsException {
        contactList.editContactAtIndex(contactDetails, contactIndex);
        //sort the contact list based on name after a contact has been edited
        contactList.sortContacts();
        TextUi.editContactMessage(postEditContact);
    }

    //@@author ashrafjfr
    /**
     * This method takes in the postEditcontact, contactList and contactIndex to check whether there are any contacts
     * already in the contactList that are duplicates to the postEditContact. If there are duplicates, a confirmation
     * message will be output to confirm whether the user would like to still edit the duplicated contact.
     *
     * @param postEditContact Contact that has been edited with fields user intends to edit to
     * @param contactList List of current contacts
     * @param contactIndex int to know which index of contactList is being edited
     */
    private boolean hasDuplicates(Contact postEditContact, ContactList contactList, int contactIndex) {
        ArrayList<Integer> duplicatedIndex = new ArrayList<>();
        boolean[] hasEditedField = hasEditedFields(contactDetails);
        String[] postEditContactDetails = postEditContact.getContactStringArray();
        for (int i = 0; i < contactList.getListSize(); i++) {
            if (i == contactIndex) {
                continue;
            }
            String[] currentContactDetails = contactList.getContactAtIndex(i).getContactStringArray();
            for (int j = 0; j < NUMBER_OF_FIELDS; j++) {
                if (!hasEditedField[j]) {
                    continue;
                }
                if ((postEditContactDetails[j] != null && currentContactDetails[j] != null)
                        && hasDuplicateField(postEditContactDetails[j], currentContactDetails[j])) {
                    duplicatedIndex.add(i);
                    break;
                }
            }
        }
        if (!duplicatedIndex.isEmpty()) {
            TextUi.confirmDuplicateMessage(duplicatedIndex, contactList, EDIT_TYPE);
            String userEditConfirmation = UserInputTextUi.getUserConfirmation();
            return !userEditConfirmation.equalsIgnoreCase("y");
        }
        return false;
    }

    /**
     * This method returns true if the input string is equal to the similar field of a saved contact indicating a
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

    //@@author ng-andre
    private boolean[] hasEditedFields(String[] contactDetails) {
        boolean[] hasEditedField = new boolean[NUMBER_OF_FIELDS];
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            hasEditedField[i] = (contactDetails[i] != null);
        }
        return hasEditedField;
    }

    //@@author ng-andre
    private Contact duplicateContact(Contact contact) {
        String name = contact.getName();
        String github = contact.getGithub();
        String linkedin = contact.getLinkedin();
        String telegram = contact.getTelegram();
        String twitter = contact.getTwitter();
        String email = contact.getEmail();
        return new Contact(name, github, linkedin, telegram, twitter, email);
    }
}
