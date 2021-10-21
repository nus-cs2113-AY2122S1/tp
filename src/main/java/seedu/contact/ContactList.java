package seedu.contact;

import seedu.exception.InvalidFlagException;
import seedu.ui.TextUi;

import java.util.ArrayList;

import static seedu.parser.ContactParser.NUMBER_OF_FIELDS;

public class ContactList {
    public static final int NAME_INDEX = 0;
    public static final int GITHUB_INDEX = 1;
    public static final int LINKEDIN_INDEX = 2;
    public static final int TELEGRAM_INDEX = 3;
    public static final int TWITTER_INDEX = 4;
    public static final int EMAIL_INDEX = 5;

    private ArrayList<Contact> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contacts.sort(new ContactComparator());
    }

    //@@author ng-andre
    public void editContact(String[] contactDetails, int contactIndex) throws InvalidFlagException {
        for (int i = 0; i < contactDetails.length; i++) {
            if (contactDetails[i] != null) {
                switch (i) {
                case NAME_INDEX:
                    contacts.get(contactIndex).setName(contactDetails[i]);
                    contacts.sort(new ContactComparator());
                    break;
                case GITHUB_INDEX:
                    contacts.get(contactIndex).setGithub(contactDetails[i]);
                    break;
                case LINKEDIN_INDEX:
                    contacts.get(contactIndex).setLinkedin(contactDetails[i]);
                    break;
                case TELEGRAM_INDEX:
                    contacts.get(contactIndex).setTelegram(contactDetails[i]);
                    break;
                case TWITTER_INDEX:
                    contacts.get(contactIndex).setTwitter(contactDetails[i]);
                    break;
                case EMAIL_INDEX:
                    contacts.get(contactIndex).setEmail(contactDetails[i]);
                    break;
                default:
                    //control should never reach here
                    assert false;
                    throw new InvalidFlagException();
                }
            }
        }
    }

    public void deleteContact(int deletedIndex) {
        contacts.remove(deletedIndex);
    }

    //@@author ng-andre
    private boolean hasDetailMatches(String query, int detailType, int index, Contact contact) {
        String[] contactStringArray = contact.getContactStringArray();
        if (contactStringArray[detailType] != null &&
                contactStringArray[detailType].toLowerCase().contains(query)) {
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
            if(hasDetailMatches(query, detailType, i, contact)) {
                matches++;
            }
        }
        if (matches == 0) {
            TextUi.searchNoResultsFoundMessage();
        }
    }

    public int getIndexOfContact(Contact contact) {
        return contacts.indexOf(contact);
    }

    public Contact getContactAtIndex(int index) {
        contacts.sort(new ContactComparator());
        return contacts.get(index);
    }

    public int getListSize() {
        return contacts.size();
    }
}
