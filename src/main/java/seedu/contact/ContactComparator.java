//@@author mayankp291

package seedu.contact;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
    public int compare(Contact contact1, Contact contact2) {
        return contact1.getName().toLowerCase().compareTo(contact2.getName().toLowerCase());
    }
}
