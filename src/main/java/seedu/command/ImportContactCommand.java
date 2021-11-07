//@@author marcusbory

package seedu.command;

import seedu.contact.ContactList;
import seedu.exception.FileErrorException;
import seedu.storage.ContactsDecoder;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;

import java.io.File;

public class ImportContactCommand extends Command {
    private ContactList importedContacts;
    private String importFilePath;
    private File importFile;

    public ImportContactCommand() {
        importFilePath = "data/import.txt";
        importFile = new File(importFilePath);
    }

    /**
     * Executes the ImportContactCommand by attempting to read and append the import file's contents into the user's
     * contact list.
     */
    public void execute() {
        if (foundImportFile()) {
            importContacts();
            appendImportedContacts();
            TextUi.successfulImportMessage(importedContacts.getListSize());
        } else {
            ExceptionTextUi.missingImportFileMessage();
        }
    }

    /**
     * Returns true if there exists an import file at the target location.
     * @return boolean True if the import file exists
     */
    private boolean foundImportFile() {
        return importFile.exists();
    }

    /**
     * Attempts to decode the import file and read its contents into a temporary contact list.
     */
    private void importContacts() {
        assert importFile.exists();
        try {
            ContactsDecoder contactsDecoder = new ContactsDecoder();
            importedContacts = contactsDecoder.readContacts(importFile, importFilePath);
        } catch (FileErrorException e) {
            ExceptionTextUi.fileErrorMessage(importFilePath);
        }
    }

    /**
     * Appends the temporary contact list to the user's contact list.
     * Note: User's contact list is always sorted at any point during import.
     */
    private void appendImportedContacts() {
        for (int i = 0; i < importedContacts.getListSize(); i++) {
            contactList.addContact(importedContacts.getContactAtIndex(i));
        }
    }
}
