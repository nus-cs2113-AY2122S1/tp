package happybit.storage;

import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.habit.Habit;
import happybit.interval.Interval;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILEPATH = "data/habits.txt";

    protected String filePath;
    protected String fileDir;
    protected Export export;

    public Storage() {
        this(DEFAULT_FILEPATH, DEFAULT_DIR);
    }

    public Storage(String filePath, String fileDir) {
        this.filePath = filePath;
        this.fileDir = fileDir;
        export = new Export(this.filePath);
    }

    public GoalList load() throws HaBitStorageException {
        this.createFile(this.filePath, this.fileDir);

        return Import.importStorage(this.filePath);
    }

    protected void createFile(String filePath, String fileDir) {
        File storageDir = new File(fileDir);
        File storageFile = new File(filePath);

        if (!storageDir.exists()) {
            boolean isDirCreated = storageDir.mkdirs();
            System.out.printf("Directory %s does not exist." + System.lineSeparator(), fileDir);

            if (isDirCreated) {
                System.out.println("Directory created.");
                assert storageDir.exists() : "directory should have been created";
            } else {
                System.out.println("Directory not created.");
            }
        }

        try {
            boolean isFileCreated = storageFile.createNewFile();

            if (isFileCreated) {
                System.out.println("File created at: " + filePath);
                assert storageFile.exists() : "file should have been created";
            } else {
                System.out.printf("Storage file found at %s." + System.lineSeparator(), filePath);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating file: " + e);
        }
    }

    /**
     * To export goal list to storage file.
     *
     * @param goalList an array list of goals
     * @throws HaBitStorageException IOException
     */
    public void export(ArrayList<Goal> goalList) throws HaBitStorageException {
        export.exportStorage(goalList);
    }

    /**
     * To export a goal to storage file.
     *
     * @param goal a Goal object
     * @param index the index of goal in goal list
     * @throws HaBitStorageException IOException
     */
    public void export(Goal goal, int index) throws HaBitStorageException {
        export.exportGoal(goal, index);
    }

    /**
     * To export a habit to storage file.
     *
     * @param habit a Habit object
     * @param index the index of the goal the habit is under
     * @throws HaBitStorageException IOException
     */
    public void export(Habit habit, int index) throws HaBitStorageException {
        export.exportHabit(habit, index);
    }

    public void export(Interval interval, int goalIndex, int habitIndex) throws HaBitStorageException {
        export.exportInterval(interval, goalIndex, habitIndex);
    }
}
