//@@author lezongmun

package seedu.command;

import seedu.contact.Contact;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;


public class DeleteContactCommand extends Command {
    private static final String ACKNOWLEDGE_DELETE = "y";
    private static final int PERSONAL_CONTACT_INDEX = -1;
    private static final int DELETE_ALL_CONTACTS_INDEX = -2;

    private final int contactIndex;
    private final boolean[] hasDeletedDetails;

    public DeleteContactCommand(int contactIndex, boolean[] hasDeletedDetails) {
        this.contactIndex = contactIndex;
        this.hasDeletedDetails = hasDeletedDetails;
    }

    public int getContactIndex() {
        return contactIndex;
    }

    /**
     * This method is used to delete one specified contact given by the user. It finds
     * the contact to be deleted and prompts the user for confirmation to delete, before
     * executing any deletion of a contact.
     *
     * @throws IndexOutOfBoundsException If index given in the DeleteContactCommand by user
     *                                   is outside the size of the Contact List.
     */
    private void deleteSelectedContact() throws IndexOutOfBoundsException {
        // throws IndexOutOfBoundsException if index is outside of the range
        Contact deletedContact = contactList.getContactAtIndex(contactIndex);
        // index must be within range since no exceptions thrown
        assert contactIndex >= 0 && contactIndex < contactList.getListSize();
        // ask for confirmation to delete from user
        TextUi.confirmDeleteContactMessage(deletedContact, contactIndex);
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        if (userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE)) {
            this.contactList.deleteContact(contactIndex);
            String deletedName = deletedContact.getName();
            int contactListSize = contactList.getListSize();
            TextUi.deleteContactMessage(deletedName, contactListSize);
        } else {
            assert !userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE);
            TextUi.cancelDeleteContactMessage();
        }
    }

    /**
     * This method is used to delete all of the contacts in the Contact List. It
     * prompts the user for confirmation to delete before executing any deletion
     * of all the contacts.
     */
    private void deleteAllContacts() {
        TextUi.confirmDeleteAllMessage();
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        if (userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE)) {
            int contactListSize = contactList.getListSize();
            this.contactList.deleteAllContacts();
            TextUi.deleteAllContactsMessage(contactListSize);
        } else {
            assert !userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE);
            TextUi.cancelDeleteContactMessage();
        }
    }

    /**
     * This method is used to delete specified fields of a selected contact. It
     * finds the selected contact with fields to be deleted, and prompts the user
     * for confirmation to delete before executing any deletion of fields.
     */
    private void deleteFields() {
        Contact deletedContact = contactList.getContactAtIndex(contactIndex);
        assert contactIndex >= 0 && contactIndex < contactList.getListSize();
        if (TextUi.deletedFieldsGenerator(hasDeletedDetails, deletedContact).isEmpty()) {
            //if no fields exist, return false
            TextUi.noDeleteFields();
            return;
        }
        TextUi.confirmDeleteFieldMessage(hasDeletedDetails, deletedContact);
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        if (userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE)) {
            deletedContact.deleteContactFields(hasDeletedDetails);
            TextUi.deleteFieldsMessage(deletedContact);
        } else {
            assert !userConfirmation.equalsIgnoreCase(ACKNOWLEDGE_DELETE);
            TextUi.cancelDeleteContactMessage();
        }
    }

    /**
     * This method executes the deletion process of the DeleteContactCommand. It
     * checks whether the deletion is for all contacts, a single contact or a
     * contact's fields, before running their corresponding delete methods
     */
    public void execute() {
        try {
            if (contactIndex == DELETE_ALL_CONTACTS_INDEX) {
                deleteAllContacts();
            } else if (contactIndex == PERSONAL_CONTACT_INDEX) {
                ExceptionTextUi.missingIndexMessage();
            } else if (hasDeletedDetails[6]) { //delete entire contact
                deleteSelectedContact();
            } else {
                deleteFields();
            }
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
        }
    }
}
