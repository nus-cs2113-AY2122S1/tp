package seedu.contact;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<Contact> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void editContact(String[] contactDetails, int contactIndex) {
        for (int i = 0; i < contactDetails.length; i++) {
            if (contactDetails[i] != null) {
                switch (i) {
                case NAME_INDEX:
                    contacts.get(contactIndex).setName(contactDetails[i]);
                    break;
                case GITHUB_INDEX:
                    contacts.get(contactIndex).setGithub(contactDetails[i]);
                    break;
                default:
                    System.out.println("Index error has occurred.");
                    break;
                }
            }
        }
    }

    public Contact getContactAtIndex(int index) {
        return contacts.get(index);
    }

    public int getListSize() {
        return contacts.size();
    }
}
