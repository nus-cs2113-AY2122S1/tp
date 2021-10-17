package happybit.storage;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.habit.Habit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILEPATH = "data/habits.txt";
    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = "##";
    private static final String GOAL_TYPE = "G";
    private static final String HABIT_TYPE = "H";
    private static final String SLEEP = "[SL]";
    private static final String FOOD = "[FD]";
    private static final String EXERCISE = "[EX]";
    private static final String STUDY = "[SD]";
    private static final int NUM_INDEX = 0;
    private static final int TYPE_INDEX = 1;
    private static final int GOAL_TYPE_INDEX = 2;
    private static final int DONE_INDEX = 2;
    private static final int GOAL_NAME_INDEX = 3;
    private static final int HABIT_NAME_INDEX = 3;
    private static final int GOAL_START_INDEX = 4;
    private static final int GOAL_END_INDEX = 5;
    private static final String ERROR_INVALID_GOAL_INDEX = "There is no goal at that index.";

    protected String filePath;
    protected String fileDir;

    public Storage() {
        this(DEFAULT_FILEPATH, DEFAULT_DIR);
    }

    public Storage(String filePath, String fileDir) {
        this.filePath = filePath;
        this.fileDir = fileDir;
    }

    public GoalList load() throws HaBitStorageException {
        createFile(this.filePath, this.fileDir);

        return Import.importStorage(this.filePath);
    }

    protected void createFile(String filePath, String fileDir) {
        File storageDir = new File(fileDir);
        File storageFile = new File(filePath);

        if (!storageDir.exists()) {
            boolean isDirCreated = storageDir.mkdirs();

            if (isDirCreated) {
                System.out.println("Directory created: " + fileDir);
                assert storageDir.exists() : "directory should have been created";
            } else {
                System.out.println("Directory not created");
            }
        }

        try {
            boolean isFileCreated = storageFile.createNewFile();

            if (isFileCreated) {
                System.out.println("File created: " + filePath);
                assert storageFile.exists() : "file should have been created";
            } else {
                System.out.println("File exists");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating file: " + e);
        }
    }

    public void export(ArrayList<Goal> goalList) throws HaBitStorageException {
        try {
            clearFile();
            writeToFile(goalList);
        } catch (IOException e) {
            throw new HaBitStorageException(e.toString());
        }
    }

    protected void clearFile() throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);

        fileWriter.write("");
        fileWriter.close();
    }

    protected void writeToFile(ArrayList<Goal> goalList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath, true);

        for (Goal goal : goalList) {
            int index = goalList.indexOf(goal);
            ArrayList<Habit> habits = goal.getHabitList();
            String goalToWrite = index
                    + DELIMITER
                    + GOAL_TYPE
                    + DELIMITER
                    + goal.getGoalTypeCharacter()
                    + DELIMITER
                    + goal.getGoalName()
                    + DELIMITER
                    + goal.getStartDate()
                    + DELIMITER
                    + goal.getEndDate()
                    + NEWLINE;
            fileWriter.write(goalToWrite);

            for (Habit habit : habits) {
                int doneValue = habit.getDone() ? 1 : 0;
                String habitToWrite = index
                        + DELIMITER
                        + HABIT_TYPE
                        + DELIMITER
                        + doneValue
                        + DELIMITER
                        + habit.getHabitName()
                        + NEWLINE;
                fileWriter.write(habitToWrite);
            }
        }

        fileWriter.close();
    }
}
