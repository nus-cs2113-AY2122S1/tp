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

    public void execute() {
        if (foundImportFile()) {
            importContacts();
            appendImportedContacts();
            TextUi.successfulImportMessage(importedContacts.getListSize());
        } else {
            ExceptionTextUi.missingImportFileMessage();
        }
    }

    private boolean foundImportFile() {
        return importFile.exists();
    }

    private void importContacts() {
        assert importFile.exists();
        try {
            ContactsDecoder contactsDecoder = new ContactsDecoder();
            importedContacts = contactsDecoder.readContacts(importFile, importFilePath);
        } catch (FileErrorException e) {
            ExceptionTextUi.fileErrorMessage(importFilePath);
        }
    }

    private void appendImportedContacts() {
        for (int i = 0; i < importedContacts.getListSize(); i++) {
            contactList.addContact(importedContacts.getContactAtIndex(i));
        }
    }
}
