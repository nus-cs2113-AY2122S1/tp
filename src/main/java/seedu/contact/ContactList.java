package seedu.contact;

import seedu.exception.InvalidFlagException;

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
    }

    public void editContact(String[] contactDetails, int contactIndex) throws InvalidFlagException {
        for (int i = 0; i < contactDetails.length; i++) {
            if (contactDetails[i] != null) {
                switch (i) {
                case NAME_INDEX:
                    contacts.get(contactIndex).setName(contactDetails[i]);
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

    public int getIndexOfContact(Contact contact) {
        return contacts.indexOf(contact);
    }

    public Contact getContactAtIndex(int index) {
        return contacts.get(index);
    }

    public int getListSize() {
        return contacts.size();
    }
}
