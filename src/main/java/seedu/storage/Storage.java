package seedu.storage;

import seedu.contact.ContactList;
import seedu.exception.FileErrorException;
import seedu.ui.TextUi;

import java.io.File;
import java.io.IOException;

public class Storage {
    private final String contactFilePath;
    private final File contactFile;
    public static final String SEPARATOR = ",";

    public Storage(String contactFilePath) {
        this.contactFilePath = contactFilePath;
        this.contactFile = new File(contactFilePath);
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
}
