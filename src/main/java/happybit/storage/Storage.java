package happybit.storage;

import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.habit.Habit;
import happybit.ui.PrintManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Supports the import and export of user data.
 */
public class Storage {
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILEPATH = "data/habits.txt";
    private static final String DIR_NOT_EXIST = "Directory '" + DEFAULT_DIR + "' does not exist.";
    private static final String DIR_CREATED = "Directory created.";
    private static final String DIR_NOT_CREATED = "Failed to create directory.";
    private static final String FILE_CREATED = "Storage file created at " + DEFAULT_FILEPATH + ".";
    private static final String FILE_FOUND = "Storage file found at " + DEFAULT_FILEPATH + ".";
    private static final String READ_ONLY = "Storage file is set to read only.";
    private static final String SET_READ_ONLY_FAILED = "Failed to set storage file as read only.";
    private static final String EMPTY = "";
    private static final String FILE_CORRUPTED = "File is corrupted. All the data will be cleared.";

    protected String filePath;
    protected String fileDir;
    protected Export export;
    protected PrintManager printManager;

    public Storage() {
        this(DEFAULT_FILEPATH, DEFAULT_DIR);
    }

    public Storage(String filePath, String fileDir) {
        this.filePath = filePath;
        this.fileDir = fileDir;
        this.printManager = new PrintManager();
        this.export = new Export(this.filePath, this.printManager);
    }

    /**
     * This method is automatically called everytime the program start up.
     * It will import the data from storage file into GoalList object.
     *
     * @return a GoalList object
     */
    public GoalList load() {
        this.createStorageFile(this.filePath, this.fileDir);
        GoalList goalList = new GoalList();

        try {
            goalList = Import.importStorage(this.filePath);
        } catch (HaBitStorageException e) {
            this.clearFile();
            this.setReadOnly(new File(this.filePath));
            printManager.printError(e.getMessage());
        }
        return goalList;
    }

    /**
     * Checks if the storage file exists. If it does not, it will create one for the user.
     * The storage file created will be read only.
     *
     * @param filePath the file path where the storage file is to be found
     * @param fileDir the folder where the storage file is supposed to be stored at
     */
    protected void createStorageFile(String filePath, String fileDir) {
        File storageDir = new File(fileDir);
        File storageFile = new File(filePath);

        if (!storageDir.exists()) {
            this.printManager.printStorageMessage(DIR_NOT_EXIST);
            this.makeDirectory(storageDir);
        }

        this.createFile(storageFile);
        this.setReadOnly(storageFile);
    }

    /**
     * Creates the directory of storage file.
     *
     * @param storageDir the directory where storage file will be placed
     */
    protected void makeDirectory(File storageDir) {
        boolean isDirCreated = storageDir.mkdirs();

        if (isDirCreated) {
            this.printManager.printStorageMessage(DIR_CREATED);
        } else {
            this.printManager.printStorageMessage(DIR_NOT_CREATED);
        }
    }

    /**
     * Clears the storage file.
     */
    protected void clearFile() {
        this.setWritable();

        try {
            FileWriter fileWriter = new FileWriter(this.filePath);

            fileWriter.write(EMPTY);
            fileWriter.close();
        } catch (IOException e) {
            printManager.printError(e.getMessage());
        }
    }

    /**
     * Allows writing to the storage file which was set as read only.
     */
    protected void setWritable() {
        File storageFile = new File(this.filePath);
        boolean isWriteable = storageFile.setWritable(true);

        if (isWriteable) {
            this.printManager.printStorageMessage(FILE_CORRUPTED);
        }
    }

    /**
     * Creates the storage file if it does not exist.
     *
     * @param storageFile the storage text file to create
     */
    protected void createFile(File storageFile) {
        try {
            boolean isFileCreated = storageFile.createNewFile();

            if (isFileCreated) {
                this.printManager.printStorageMessage(FILE_CREATED);
            } else {
                this.printManager.printStorageMessage(FILE_FOUND);
            }
        } catch (IOException e) {
            this.printManager.printError(e.getMessage());
        }
    }

    /**
     * Sets the storage file as read only.
     *
     * @param storageFile the storage file to set as read only
     */
    protected void setReadOnly(File storageFile) {
        boolean isReadOnly = storageFile.setReadOnly();

        if (isReadOnly) {
            this.printManager.printStorageMessage(READ_ONLY);
        } else {
            this.printManager.printStorageMessage(SET_READ_ONLY_FAILED);
        }
    }

    /**
     * Exports goal list to storage file.
     *
     * @param goalList an array list of goals
     * @throws HaBitStorageException IOException
     */
    public void export(ArrayList<Goal> goalList) throws HaBitStorageException {
        this.export.exportToStorage(goalList);
    }

    /**
     * Exports a goal to storage file.
     *
     * @param goal a Goal object
     * @param index the index of goal in goal list
     * @throws HaBitStorageException IOException
     */
    public void export(Goal goal, int index) throws HaBitStorageException {
        this.export.exportGoal(goal, index);
    }

    /**
     * Exports a habit to storage file.
     *
     * @param habit a Habit object
     * @param goalIndex the index of the goal the habit is under
     * @param habitIndex the index of the habit in the goal
     * @throws HaBitStorageException IOException
     */
    public void export(Habit habit, int goalIndex, int habitIndex) throws HaBitStorageException {
        this.export.exportHabit(habit, goalIndex, habitIndex);
    }
}
