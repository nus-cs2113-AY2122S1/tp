package seedu.duke.storage;

import seedu.duke.food.ReferenceLists;
import seedu.duke.food.WhatIAteList;
import seedu.duke.journal.CollectionOfEntries;
import seedu.duke.journal.CollectionOfNotebooks;
import seedu.duke.logger.ClickLogger;
import seedu.duke.schedule.lecture.LectureList;
import seedu.duke.schedule.task.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * A class that deals with loading data from the file and saving data to the file.
 */
public class Storage {

    private String tasksFilePath;
    public static ReferenceLists reference = ReferenceLists.getLists();
    public WhatIAteList whatIAteTodayList =  StorageFood.load();
    public static TaskList tasksList;

    static {
        try {
            tasksList = StorageTasks.readTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CollectionOfNotebooks collectionOfNotebooks = StorageNotes.readCollectionOfNotes();
    public CollectionOfEntries collectionOfEntries = StorageEntries.readEntries();
    public StorageModule storageModule = new StorageModule();
    public static LectureList lectureList;

    static {
        try {
            lectureList = StorageLecture.readLectureList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Storage() throws IOException {
    }

    //@@author ngnigel99
    /**
     * Creates directory if directory folderName is not found.
     * access modifier left empty for working in Storage.
     * @param folderName folder name to check
     * @throws IOException case where directory not found
     *
     * @author ngnigel99
     */
    static void checkAndAddDirectory(String folderName) throws IOException {
        assert folderName.contains("/") : "Please follow format [DIR_NAME]/";   //checks syntax of constant
        String home = new File("").getAbsolutePath() + '/';
        File dirCheck = new File(home + folderName);
        ClickLogger.getNewLogger().log(Level.INFO,  "checking if directory exists!");
        if (dirCheck.isDirectory()) {
            ClickLogger.getNewLogger().log(Level.INFO, "nice, directory found!");
            return;
        }
        ClickLogger.getNewLogger().log(Level.CONFIG, "not nice, no directory found,"
                +     "creating  new directory");
        Files.createDirectories(Paths.get(home + folderName));
    }
    //@@author

    //@author swatimahadevan
    /**
     * Loads data in the form of ArrayList data from the save file.
     *
     * @param filePath the filepath of the storage file.
     * @return data as ArrayList.
     * @throws IOException in case of loading from save file error.
     */
    public static ArrayList<String> loadDataFromSaveFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> data;
        data = bufferedReader.lines().collect(Collectors.toCollection(ArrayList::new));
        return data;
    }

    /**
     * Write data from ArrayList data onto save file.
     *
     * @param filePath the filepath of the storage file.
     * @throws IOException in case of writing to save file error.
     */
    public static void writeDataOntoSaveFile(String filePath, ArrayList<String> data) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for (String dataObject : data) {
            bufferedWriter.write(dataObject + '\n');
        }
        bufferedWriter.close();
    }
    //@@author
}
