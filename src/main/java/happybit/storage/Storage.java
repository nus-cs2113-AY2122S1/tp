package happybit.storage;

import happybit.goal.GoalList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILEPATH = "data/habits.txt";
    private static final String DELIMITER = "@@@";
    private static final int GOAL_NAME_INDEX = 0;
    private static final int GOAL_START_DATE = 1;
    private static final int GOAL_END_DATE = 2;
    private static final int HABIT_NUMBER = 0;
    private static final int HABIT_TYPE = 1;
    private static final int HABIT_NAME = 2;

    protected String filePath;
    protected String fileDir;

    public Storage() {
        this(DEFAULT_FILEPATH, DEFAULT_DIR);
    }

    public Storage(String filePath, String fileDir) {
        this.filePath = filePath;
        this.fileDir = fileDir;
    }

    // temp method until the importData method is fixed
    public GoalList load() {
        return null;
    }

    public ArrayList<String> importData() {
        ArrayList<String> habits = new ArrayList<>();
        Scanner s;
        String line;

        createFile(this.filePath, this.fileDir);

        File storageFile = new File(this.filePath);

        try {
            s = new Scanner(storageFile);

            while (s.hasNext()) {
                line = s.nextLine();
                String[] lineData = line.split(DELIMITER);

                if (lineData[0].startsWith("-")) {
                    String toAdd = lineData[GOAL_NAME_INDEX].substring(1)
                            + " "
                            + lineData[GOAL_START_DATE]
                            + " "
                            + lineData[GOAL_END_DATE];

                    habits.add(toAdd);
                } else {
                    String toAdd = lineData[HABIT_NUMBER].substring(1)
                            + " "
                            + lineData[HABIT_TYPE]
                            + " "
                            + lineData[HABIT_NAME];

                    habits.add(toAdd);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }

        return habits;
    }

    public void createFile(String filePath, String fileDir) {
        File storageDir = new File(fileDir);
        File storageFile = new File(filePath);

        if (!storageDir.exists()) {
            boolean isDirCreated = storageDir.mkdirs();

            if (isDirCreated) {
                System.out.println("Directory created: " + fileDir);
            } else {
                System.out.println("Directory not created");
            }
        }

        try {
            boolean isFileCreated = storageFile.createNewFile();

            if (isFileCreated) {
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File exists");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating file: " + e);
        }
    }
}
