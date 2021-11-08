package seedu.duke.journal;

//@@author SvethaMahadevan

import seedu.duke.exceptions.journal.InvalidTagNameException;
import seedu.duke.storage.StorageNotes;

import seedu.duke.storage.Storage;

import java.io.IOException;

public class Notebook {
    private String notebookName;
    private String tag;

    /**
     * Constructor of Note class.
     *
     * @param noteName name of the note
     * @param tag of the notebook
     */
    public Notebook(String noteName, String tag) {
        this.notebookName = noteName;
        this.tag = tag;
    }

    /**
     * Tags the notebook.
     *
     * @param tag name of the tag for the notebook
     *
     */
    public void tag(String tag, Storage storage) throws InvalidTagNameException, IOException {
        if (tag.equals("")) {
            throw new InvalidTagNameException();
        }
        this.tag = tag;
        StorageNotes.writeCollectionOfNotes(storage.collectionOfNotebooks);
    }

    /**
     * Returns the tag.
     *
     * @return tag name of the notebook
     *
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns the name of the note.
     *
     * @return noteName which stores the name of the note.
     */
    public String getNoteName() {
        return notebookName;
    }

    /**
     * Returns name of notebook in format to save in data file.
     *
     * @return  stringForStorage name of notebook in format to save in data file.
     */
    public String toSaveFileFormat() {
        String stringForStorage = "note" + "|" + notebookName + "|" + tag;
        return stringForStorage;
    }

}