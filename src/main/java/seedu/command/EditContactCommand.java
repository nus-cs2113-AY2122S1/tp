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
            } else {
                Contact editedContact = duplicateContact(contactList.getContactAtIndex(contactIndex));
                editedContact.editContact(contactDetails);
                handleDuplicates(editedContact);
            }
        } catch (IndexOutOfBoundsException | InvalidFlagException e) {
            //triggerred when inputs are "edit <wrong_index> <proper flags>"
            ExceptionTextUi.numOutOfRangeEditMessage(contactList.getListSize());
        }
    }

    private void updatePersonalContact() throws InvalidFlagException {
        personalContact.editContact(contactDetails);
        TextUi.editPersonalContactMessage(personalContact);
    }

    private void handleDuplicates(Contact postEditContact) throws IndexOutOfBoundsException, InvalidFlagException {
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

    //@@author ng-andre
    private boolean[] hasEditedFields(String[] contactDetails) {
        boolean[] hasEditedField = new boolean[NUMBER_OF_FIELDS];
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            hasEditedField[i] = (contactDetails[i] != null);
        }
        return hasEditedField;
    }

    private boolean hasDuplicateField(String input, String saved) {
        return cleanString(saved).equals(cleanString(input));
    }

    private String cleanString(String input) {
        return input.replace(" ", "").toLowerCase();
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
