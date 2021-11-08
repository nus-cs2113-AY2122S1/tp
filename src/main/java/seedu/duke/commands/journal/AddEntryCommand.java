package seedu.duke.commands.journal;

//@@author SvethaMahadevan

import seedu.duke.commands.Command;
import seedu.duke.exceptions.journal.EmptyEntryArgumentsException;
import seedu.duke.exceptions.journal.EmptyEntryNameException;
import seedu.duke.exceptions.journal.EmptyNoteNameException;
import seedu.duke.exceptions.journal.InvalidAddEntryArgumentException;
import seedu.duke.exceptions.journal.NotebookNotFoundForEntry;
import seedu.duke.journal.Notebook;
import seedu.duke.parser.journal.ParserJournal;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageEntries;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class AddEntryCommand extends Command {
    public String userInput;

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public AddEntryCommand() {
        syntax = "journal entry n/ [NOTEBOOK_NAME] e/ [ENTRY_NAME]";
    }

    /**
     * Constructor for the AddEntryCommand.
     *
     * @param userInput input from the user
     */
    public AddEntryCommand(String userInput) {
        this.userInput = userInput;
        this.helpMessage = "Add an entry to the desired notebook";
        this.syntax = "journal entry n/ NOTEBOOK_NAME e/ ENTRY_NAME";
    }

    /**
     * Allows for the adding of entry.
     *
     * @param ui allows for printing of a message to indicate that the entry has been added
     * @param storage to allow for storing of entries
     * @throws EmptyNoteNameException No note name entered after 'n/'
     * @throws EmptyEntryArgumentsException if no arguments found for the entry
     * @throws EmptyEntryNameException if no entry name is provided
     * @throws NotebookNotFoundForEntry if no notebook is found in list for entry
     * @throws InvalidAddEntryArgumentException if arguments for adding entry are invalid.
     * @throws IOException in case of error when writing to save file.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws EmptyNoteNameException, EmptyEntryArgumentsException,
            EmptyEntryNameException, IOException,
            NotebookNotFoundForEntry, InvalidAddEntryArgumentException {
        String[] argumentsNoteEntry = ParserJournal.parseAddEntryCommand(userInput, storage);
        String notebookName = argumentsNoteEntry[0];
        String entryName = argumentsNoteEntry[1];
        ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
        for (Notebook note : notes) {
            if (note.getNoteName().equals(notebookName)) {
                storage.collectionOfEntries.addEntry(notebookName, entryName);
            }
        }
        ui.printAddedEntryMessage(entryName);
        StorageEntries.writeEntries(storage.collectionOfEntries, storage);
    }
}
