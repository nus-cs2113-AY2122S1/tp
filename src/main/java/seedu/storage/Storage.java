//@@author marcusbory

package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.FileErrorException;
import seedu.parser.AddPersonalContactParser;
import seedu.ui.TextUi;

import java.io.File;
import java.io.IOException;

public class Storage {
    public static final String SEPARATOR = ",";
    private final String contactFilePath;
    private final File contactFile;
    private final String personalContactFilePath;
    private final File personalContactFile;
    private final ContactsDecoder contactsDecoder;
    private final ContactsEncoder contactsEncoder;

    public Storage(String contactFilePath, String personalContactFilePath, ContactsDecoder contactsDecoder,
                   ContactsEncoder contactsEncoder) {
        this.contactFilePath = contactFilePath;
        this.contactFile = new File(contactFilePath);
        this.personalContactFilePath = personalContactFilePath;
        this.personalContactFile = new File(personalContactFilePath);
        this.contactsDecoder = contactsDecoder;
        this.contactsEncoder = contactsEncoder;
    }

    //@@author lezongmun
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

    //@@author lezongmun
    private boolean hasEmptyExistingPersonalContactFile() {
        return personalContactFile.exists() && personalContactFile.length() == 0;
    }

    //@@author marcusbory
    /**
     * Checks if there is already an existing local storage file, and attempts to create a new local storage file for
     * data persistence if one does not exist.
     * @return boolean Returns true if local storage file exists
     * @throws FileErrorException If there are errors writing or checking for local storage files
     */
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

    /**
     * Attempts to decode and retrieve existing contacts from a user's local storage file, or returns a new contact
     * list if no local storage files are found.
     * @return ContactList Decoded contact list from local storage file
     * @throws FileErrorException If there are errors reading from local storage file
     */
    public ContactList loadExistingContacts() throws FileErrorException {
        if (!hasExistingContactFile()) {
            return new ContactList();
        }
        return contactsDecoder.readContacts(contactFile, contactFilePath);
    }

    //@@author lezongmun
    public Contact loadExistingPersonalContact() throws FileErrorException {
        if (!hasExistingPersonalContactFile() || hasEmptyExistingPersonalContactFile()) {
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
