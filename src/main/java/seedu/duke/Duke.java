package seedu.duke;

import seedu.contact.ContactList;
import seedu.exception.FileErrorException;
import seedu.storage.Storage;
import seedu.ui.TextUi;

public class Duke {
    private TextUi textUi;
    private String contactFilePath;
    private Storage storage;
    private ContactList contactList;

    public Duke(String contactFilePath) {
        this.textUi = new TextUi();
        this.contactFilePath = contactFilePath;
        this.storage = new Storage(contactFilePath);
        try {
            this.contactList = storage.loadExistingContacts();
        } catch (FileErrorException e) {
            TextUi.fileErrorMessage(this.contactFilePath);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke("data/contacts.txt");
    }
}
