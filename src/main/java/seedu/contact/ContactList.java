package seedu.contact;

import seedu.exception.InvalidFlagException;
import seedu.ui.TextUi;

import java.util.ArrayList;

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
    public void searchContact(String query, int detailType) throws InvalidFlagException {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = getContactAtIndex(i);
            switch (detailType) {
            case NAME_INDEX:
                if (contact.getName().toLowerCase().contains(query)) {
                    TextUi.viewContactMessage(contact, i);
                }
                break;
            case GITHUB_INDEX:
                if (contact.getGithub().toLowerCase().contains(query)) {
                    TextUi.viewContactMessage(contact, i);
                }
                break;
            case LINKEDIN_INDEX:
                if (contact.getLinkedin().toLowerCase().contains(query)) {
                    TextUi.viewContactMessage(contact, i);
                }
                break;
            case TELEGRAM_INDEX:
                if (contact.getTelegram().toLowerCase().contains(query)) {
                    TextUi.viewContactMessage(contact, i);
                }
                break;
            case TWITTER_INDEX:
                if (contact.getTwitter().toLowerCase().contains(query)) {
                    TextUi.viewContactMessage(contact, i);
                }
                break;
            case EMAIL_INDEX:
                if (contact.getEmail().toLowerCase().contains(query)) {
                    TextUi.viewContactMessage(contact, i);
                }
                break;
            default:
                //control should never reach here
                assert false;
                throw new InvalidFlagException();
            }
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
