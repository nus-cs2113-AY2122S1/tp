package seedu.duke.journal;

//@@author SvethaMahadevan

import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageEntries;

import java.io.IOException;
import java.util.ArrayList;

public class CollectionOfNotebooks {
    private static ArrayList<Notebook> notes = new ArrayList<>();

    public CollectionOfNotebooks() {
        this.notes = new ArrayList<>();
    }

    /**
     * Returns list of notes.
     *
     * @return list of notes.
     */
    public ArrayList<Notebook> getNotesArrayList() {
        return notes;
    }

    /**
     * Adds notebooks to a collection of notebooks.
     *
     * @param nameOfNote contains notebook name
     * @param tag contains tag name
     */
    public static void addNote(String nameOfNote, String tag) {
        notes.add(new Notebook(nameOfNote, tag));
    }

    /**
     * Deletes notebooks from a collection of notebooks.
     *
     * @param indexOfNote contains index of notebook to be deleted
     * @param storage Object of storage
     */
    public static void deleteNote(int indexOfNote, Storage storage) throws IOException {
        notes.remove(indexOfNote - 1);
        StorageEntries.writeEntries(storage.collectionOfEntries, storage);
    }
}