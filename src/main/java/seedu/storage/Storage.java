package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.FileErrorException;
import seedu.parser.AddPersonalContactParser;
import seedu.ui.TextUi;

import java.io.File;
import java.io.IOException;

public class Storage {
    private final String contactFilePath;
    private final File contactFile;
    private final String personalContactFilePath;
    private final File personalContactFile;
    private final ContactsDecoder contactsDecoder;
    private final ContactsEncoder contactsEncoder;
    public static final String SEPARATOR = ",";
    private static boolean isFirstRun = false;

    public Storage(String contactFilePath, String personalContactFilePath, ContactsDecoder contactsDecoder,
                   ContactsEncoder contactsEncoder) {
        this.contactFilePath = contactFilePath;
        this.contactFile = new File(contactFilePath);
        this.personalContactFilePath = personalContactFilePath;
        this.personalContactFile = new File(personalContactFilePath);
        this.contactsDecoder = contactsDecoder;
        this.contactsEncoder  = contactsEncoder;
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

    private boolean hasEmptyExistingPersonalContactFile() {
        if (personalContactFile.exists() && personalContactFile.length() == 0) {
            return true;
        }
        return false;
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
        return contactsDecoder.readContacts(contactFile, contactFilePath);
    }

    public Contact loadExistingPersonalContact() throws FileErrorException {
        if (!hasExistingPersonalContactFile() || hasEmptyExistingPersonalContactFile()) {
            isFirstRun = true;
            // get new contact's name
            AddPersonalContactParser addPersonalContactParser = new AddPersonalContactParser();
            addPersonalContactParser.collectPersonalDetails();
            Contact personalContact = addPersonalContactParser.getPersonalContact();
            contactsEncoder.savePersonalContact(personalContactFilePath, personalContact);
            return personalContact;
        }
        Contact personalContact = contactsDecoder.readPersonalContact(personalContactFile);
        contactsEncoder.savePersonalContact(personalContactFilePath, personalContact);
        TextUi.welcomeBackMessage(personalContact);
        return personalContact;
    }
}
