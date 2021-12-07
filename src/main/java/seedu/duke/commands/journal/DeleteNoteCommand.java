package seedu.duke.commands.journal;

//@@author SvethaMahadevan

import seedu.duke.commands.Command;
import seedu.duke.exceptions.journal.EmptyDeleteNoteException;
import seedu.duke.exceptions.journal.InvalidDeleteNoteArgumentException;
import seedu.duke.exceptions.journal.InvalidNotebookIndexException;
import seedu.duke.journal.Notebook;
import seedu.duke.parser.journal.ParserJournal;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageNotes;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteNoteCommand extends Command {

    public String userInput;

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public DeleteNoteCommand() {
        syntax = "journal delete_notebook [NOTE_INDEX]";
    }

    /**
     * Constructor for the DeleteNoteCommand.
     *
     * @param userInput input from the user.
     */
    public DeleteNoteCommand(String userInput) {
        this.userInput = userInput;
        helpMessage = "Delete a notebook from list";
        syntax = "journal delete_notebook NOTEBOOK_INDEX";
    }


    /**
     * Deletes the notebooks from a collection of notebooks.
     *
     * @param ui allows for printing that note is added
     * @param storage to allow for storage of notes
     * @throws InvalidNotebookIndexException if index of notebook is invalid
     * @throws InvalidDeleteNoteArgumentException if arguments for deleting notebook are invalid.
     * @throws EmptyDeleteNoteException if no notebook index given for deletion.
     * @throws IOException in case of error when writing to save file.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, InvalidNotebookIndexException,
            EmptyDeleteNoteException, InvalidDeleteNoteArgumentException {
        ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
        int indexOfNotebookToDelete = ParserJournal.parseDeleteNoteCommand(userInput);
        if (indexOfNotebookToDelete < 1 || indexOfNotebookToDelete > notes.size()) {
            throw new InvalidNotebookIndexException();
        }
        ui.printDeletedNotebookMessage(indexOfNotebookToDelete);
        storage.collectionOfNotebooks.deleteNote(indexOfNotebookToDelete, storage);
        StorageNotes.writeCollectionOfNotes(storage.collectionOfNotebooks);
    }
}