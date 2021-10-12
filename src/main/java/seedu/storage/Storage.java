package seedu.storage;

import org.w3c.dom.Text;
import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.FileErrorException;
import seedu.ui.TextUi;

import java.io.File;
import java.io.IOException;

public class Storage {
    private final String contactFilePath;
    private final File contactFile;
    private final String personalContactFilePath;
    private final File personalContactFile;
    public static final String SEPARATOR = ",";
    private static boolean isFirstRun = false;

    public Storage(String contactFilePath, String personalContactFilePath) {
        this.contactFilePath = contactFilePath;
        this.contactFile = new File(contactFilePath);
        this.personalContactFilePath = personalContactFilePath;
        this.personalContactFile = new File(personalContactFilePath);
    }

    public static boolean getIsFirstRun() {
        return isFirstRun;
    }

    private boolean hasExistingPersonalContactFile() throws FileErrorException {
        try {
            if (!personalContactFile.exists()) {
                personalContactFile.getParentFile().mkdirs();
            }
            if (personalContactFile.createNewFile()) {
                TextUi.createNewPersonalContactFileMessage(personalContactFilePath);
                return false;
            }
        } catch (IOException e) {
            throw new FileErrorException();
        }
        return true;
    }

    private boolean hasExistingContactFile() throws FileErrorException {
        try {
            if (!contactFile.exists()) {
                contactFile.getParentFile().mkdirs();
            }
            if (contactFile.createNewFile()) {
                TextUi.createNewContactFileMessage(contactFilePath);
                return false;
            }
        } catch (IOException e) {
            throw new FileErrorException();
        }
        return true;
    }

    public ContactList loadExistingContacts() throws FileErrorException {
        if (!hasExistingContactFile()) {
            return new ContactList();
        }
        return ContactsDecoder.readContacts(contactFile);
    }



    public Contact loadExistingPersonalContact() throws FileErrorException {
        if (!hasExistingPersonalContactFile()) {
            isFirstRun = true;
            // get new contact
            String personalName = TextUi.getNameMessage();
            Contact personalContact = new Contact(personalName, null,null,null,null,null);
            ContactList contactList = new ContactList();
            contactList.addContact(personalContact);
            ContactsEncoder.saveContacts(personalContactFilePath, contactList);
            TextUi.greetingMessage(personalContact);
            return personalContact;
        }
        ContactList contactList = ContactsDecoder.readContacts(personalContactFile);
        assert contactList.getListSize() == 1;
        Contact personalContact = contactList.getContactAtIndex(0);
        TextUi.welcomeBackMessage(personalContact);
        return personalContact;
    }
}
