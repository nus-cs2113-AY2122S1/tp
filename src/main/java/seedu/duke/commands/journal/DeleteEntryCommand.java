package seedu.duke.commands.journal;

//@@author SvethaMahadevan

import seedu.duke.commands.Command;
import seedu.duke.exceptions.journal.EmptyEntryArgumentsException;
import seedu.duke.exceptions.journal.EmptyEntryNameException;
import seedu.duke.exceptions.journal.EmptyNoteNameException;
import seedu.duke.exceptions.journal.EntryDoesNotExistException;
import seedu.duke.exceptions.journal.InvalidDeleteEntryArgumentException;
import seedu.duke.exceptions.journal.InvalidNotebookIndexException;
import seedu.duke.exceptions.journal.NotebookNotFoundForEntry;
import seedu.duke.journal.Entry;
import seedu.duke.parser.journal.ParserJournal;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageEntries;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteEntryCommand extends Command {
    public String userInput;

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public DeleteEntryCommand() {
        syntax = "journal delete_entry n/ [NOTEBOOK_NAME] e/ [ENTRY_NAME]";
    }

    /**
     * Constructor for the DeleteEntryCommand.
     *
     * @param userInput input from the user
     */
    public DeleteEntryCommand(String userInput) {
        this.userInput = userInput;
        this.helpMessage = "Deletes entry from the notebook";
        this.syntax = "journal delete_entry n/ NOTEBOOK_NAME e/ ENTRY_NAME";
    }

    /**
     * Allows for the deleting of entry.
     *
     * @param ui allows for printing of a message to indicate that the entry has been added
     * @param storage to allow for storing of entries
     * @throws InvalidNotebookIndexException if notebook index is invalid
     * @throws NotebookNotFoundForEntry notebook not found for deleting the entry
     * @throws EmptyEntryNameException if entry name not given by user
     * @throws EntryDoesNotExistException if the entry doesn't exist
     * @throws EmptyEntryArgumentsException if no arguments given for entry deletion
     * @throws InvalidDeleteEntryArgumentException if arguments given for entry deletion are invalid
     * @throws IOException in case of error when writing to save file.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException,
            InvalidNotebookIndexException,
            NotebookNotFoundForEntry, EmptyEntryNameException, EmptyNoteNameException,
            EntryDoesNotExistException, EmptyEntryArgumentsException, InvalidDeleteEntryArgumentException {
        String[] notebookNameAndEntryName = ParserJournal.parseDeleteEntryCommand(userInput, storage);
        String notebookName = notebookNameAndEntryName[0];
        String entryName = notebookNameAndEntryName[1];
        ArrayList<Entry> entries = storage.collectionOfEntries.getEntriesArrayList();
        boolean isEntryPresent = false;
        int indexOfEntry = 0;
        for (Entry entry: entries) {
            assert notebookNameAndEntryName != null;
            if (entry.getEntryNoteName().equals(notebookName) && (entry.getNameOfJournalEntry().equals(entryName))) {
                isEntryPresent = true;
                indexOfEntry = entries.indexOf(entry);
                break;
            }
        }
        if (!isEntryPresent) {
            throw new EntryDoesNotExistException();
        }
        entries.remove(indexOfEntry);
        ui.printDeletedEntryMessage();
        StorageEntries.writeEntries(storage.collectionOfEntries, storage);
    }
}

