//@@author marcusbory
package seedu.contact;

import seedu.exception.InvalidFlagException;
import seedu.ui.TextUi;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<Contact> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        sortContacts();
    }

    public void deleteContact(int deletedIndex) {
        contacts.remove(deletedIndex);
    }

    public void deleteAllContacts() {
        contacts.clear();
    }

    public void editContactAtIndex(String[] contactDetails, int editIndex) {
        Contact contactToEdit = getContactAtIndex(editIndex);
        contactToEdit.editContact(contactDetails);
    }

    //@@author ng-andre
    private boolean hasDetailMatches(String query, int detailType, int index, Contact contact) {
        String[] contactStringArray = contact.getContactStringArray();
        if (contactStringArray[detailType] != null
                && contactStringArray[detailType].toLowerCase().contains(query)) {
            TextUi.viewContactMessage(contact, index);
            return true;
        }
        return false;
    }

    //@@author ng-andre
    public void searchContact(String query, int detailType) throws InvalidFlagException {
        int matches = 0;
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = getContactAtIndex(i);
            if (hasDetailMatches(query, detailType, i, contact)) {
                matches++;
            }
        }
        if (matches == 0) {
            TextUi.searchNoResultsFoundMessage();
        }
    }

    //@@author marcusbory
    public Contact getContactAtIndex(int index) throws IndexOutOfBoundsException {
        sortContacts();
        return contacts.get(index);
    }

    public ArrayList<Contact> getContactList() {
        return contacts;
    }

    public void sortContacts() {
        contacts.sort(new ContactComparator());
    }

    public int getListSize() {
        return contacts.size();
    }
}
