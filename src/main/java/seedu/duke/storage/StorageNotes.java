package seedu.duke.storage;

//@@author SvethaMahadevan

import seedu.duke.journal.CollectionOfNotebooks;
import seedu.duke.journal.Notebook;
import seedu.duke.logger.ClickLogger;

import java.util.logging.Level;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class StorageNotes {
    public static final String folderName = "storage/journalData/";
    public static final String fileName   = "journalNotes.txt";
    public static final String filePath = folderName + fileName;

    static ArrayList<Notebook> dataToNotes(ArrayList<String> data) {
        ArrayList<Notebook> notes = new ArrayList<>();
        int i = 0;
        int dataSize = data.size();
        while (i < dataSize) {
            String dataLine = data.get(i);
            String[] noteArguments = dataLine.split("\\|");
            if ("note".equals(noteArguments[0])) {
                notes.add(addNote(noteArguments));
            }
            i++;
        }
        return notes;
    }

    private static Notebook addNote(String[] noteArguments) {
        String name = noteArguments[1].trim();
        String tag = noteArguments[2].trim();
        return new Notebook(name, tag);
    }

    public static ArrayList<String> notesToData(ArrayList<Notebook> notes) {
        ArrayList<String> data = new ArrayList<>();
        for (Notebook note : notes) {
            data.add(note.toSaveFileFormat());
        }
        return data;
    }

    public static void writeCollectionOfNotes(CollectionOfNotebooks collectionOfNotes) throws IOException {
        ArrayList<Notebook> notes = collectionOfNotes.getNotesArrayList();
        ArrayList<String> data = notesToData(notes);
        Storage.writeDataOntoSaveFile(filePath, data);
    }

    public static CollectionOfNotebooks readCollectionOfNotes() throws NullPointerException, IOException {
        CollectionOfNotebooks collectionOfNotebooks = new CollectionOfNotebooks();
        ArrayList<Notebook> notes;
        try {
            Storage.checkAndAddDirectory(StorageNotes.folderName);
            ArrayList<String> data = Storage.loadDataFromSaveFile(StorageNotes.filePath);
            notes = StorageNotes.dataToNotes(data);
            for (int i = 0; i < notes.size(); i++) {
                collectionOfNotebooks.addNote(notes.get(i).getNoteName(), notes.get(i).getTag());
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            ClickLogger.getNewLogger().log(Level.WARNING, "file not found on load");
            File f = new File(StorageNotes.filePath);
        }
        return collectionOfNotebooks;
    }
}