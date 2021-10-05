package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.exception.FileErrorException;
import static seedu.storage.Storage.SEPARATOR;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContactsDecoder {
    public static ContactList readContacts(File contactFile) throws FileErrorException {
        ContactList updatedContactList = new ContactList();
        try {
            Scanner fileScanner = new Scanner(contactFile);
            while (fileScanner.hasNext()) {
                String contactText = fileScanner.nextLine();
                decodeContacts(updatedContactList, contactText);
            }
        } catch (FileNotFoundException e) {
            throw new FileErrorException();
        }
        return updatedContactList;
    }

    private static void decodeContacts(ContactList contactList, String contactText) {
        String[] destructuredInputs = contactText.split(SEPARATOR);
        String contactName = destructuredInputs[DetailType.NAME.getIndex()];
        String contactGithub = destructuredInputs[DetailType.GITHUB.getIndex()];
        // Add more later
        contactList.addContact(new Contact(contactName, contactGithub));
    }
}
