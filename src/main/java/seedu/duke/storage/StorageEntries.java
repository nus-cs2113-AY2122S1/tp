package seedu.duke.storage;

//@@author SvethaMahadevan

import seedu.duke.journal.CollectionOfEntries;
import seedu.duke.journal.Entry;
import seedu.duke.journal.Notebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class StorageEntries {
    public static final String folderName = "storage/journalData/";
    public static final String fileName   = "journalEntries.txt";
    public static final String filePath = folderName + fileName;

    static ArrayList<Entry> dataToEntries(ArrayList<String> data) {
        ArrayList<Entry> entries = new ArrayList<>();
        int i = 0;
        int dataSize = data.size();
        while (i < dataSize) {
            String dataLine = data.get(i);
            String[] entryArguments = dataLine.split("\\|");
            if ("entry".equals(entryArguments[0])) {
                entries.add(addEntry(entryArguments));
            }
            i++;
        }
        return entries;
    }

    private static Entry addEntry(String[] entryArguments) {
        String noteName = entryArguments[1].trim();
        String entryName = entryArguments[2].trim();
        return new Entry(noteName, entryName);
    }

    public static ArrayList<String> entriesToData(ArrayList<Entry> entries, Storage storage) {
        ArrayList<String> data = new ArrayList<>();
        ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
        for (Entry entry : entries) {
            for (Notebook note: notes) {
                if (entry.getEntryNoteName().equals(note.getNoteName())) {
                    data.add(entry.toSaveFileFormat());
                }
            }

        }
        return data;
    }

    public static void writeEntries(CollectionOfEntries collectionOfEntries, Storage storage) throws IOException {
        ArrayList<Entry> entries = collectionOfEntries.getEntriesArrayList();
        ArrayList<String> data = entriesToData(entries, storage);
        Storage.writeDataOntoSaveFile(filePath, data);
    }

    public static CollectionOfEntries readEntries() throws NullPointerException, IOException {
        CollectionOfEntries collectionOfEntries = new CollectionOfEntries();
        ArrayList<Entry> entries;
        try {
            Storage.checkAndAddDirectory(StorageEntries.folderName);
            ArrayList<String> data = Storage.loadDataFromSaveFile(StorageEntries.filePath);
            entries = StorageEntries.dataToEntries(data);
            for (int i = 0; i < entries.size(); i++) {
                collectionOfEntries.addEntry(entries.get(i).getEntryNoteName(), entries.get(i).getNameOfJournalEntry());
            }
            return collectionOfEntries;
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            File f = new File(StorageEntries.filePath);
        }
        return collectionOfEntries;
    }
}