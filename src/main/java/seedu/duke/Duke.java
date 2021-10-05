package seedu.duke;

import seedu.command.Command;
import seedu.contact.ContactList;
import seedu.exception.FileErrorException;
import seedu.parser.Parser;
import seedu.storage.ContactsEncoder;
import seedu.storage.Storage;
import seedu.ui.TextUi;

public class Duke {
    private TextUi textUi;
    private String contactFilePath;
    private Storage storage;
    private Parser parser;
    private ContactList contactList;

    public Duke(String contactFilePath) {
        this.textUi = new TextUi();
        this.contactFilePath = contactFilePath;
        this.storage = new Storage(contactFilePath);
        this.parser = new Parser();
        try {
            this.contactList = storage.loadExistingContacts();
        } catch (FileErrorException e) {
            TextUi.fileErrorMessage(this.contactFilePath);
        }
    }

    private void runConTech() {
        Command command;
        do {
            String userInput = textUi.getUserInput();
            command = parser.parseCommand(userInput);
            runCommandProcedure(command);
        } while (!command.isExit());
    }

    private void runCommandProcedure(Command command) {
        try {
            command.setContactList(contactList);
            command.execute();
            ContactsEncoder.saveContacts(contactFilePath, contactList);
        } catch (FileErrorException e) {
            TextUi.fileErrorMessage(contactFilePath);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke("data/contacts.txt").runConTech();
    }
}
