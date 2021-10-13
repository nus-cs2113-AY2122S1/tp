package happybit.storage;

import happybit.exception.HaBitLoadException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.habit.Habit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILEPATH = "data/habits.txt";
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
    public GoalList load() throws HaBitLoadException {
        GoalList goalList = new GoalList();
        Scanner s;
        String line;

        createFile(this.filePath, this.fileDir);

        File storageFile = new File(this.filePath);

        try {
            s = new Scanner(storageFile);

            while (s.hasNext()) {
                line = s.nextLine();
                String[] lineData = line.split(DELIMITER);

                switch (lineData[TYPE_INDEX]) {
                case GOAL_TYPE:
                    try {
                        goalList.addGoal(goalParser(lineData));
                    } catch (ParseException e) {
                        throw new HaBitLoadException(e.toString());
                    }
                    break;
                case HABIT_TYPE:
                    Habit habit = habitParser(lineData);
                    int goalIndex = Integer.getInteger(lineData[NUM_INDEX]);

                    goalList.addHabitFromGoal(habit, goalIndex);
                    break;
                default:
                    throw new HaBitLoadException("error while loading");
                }
            }
        } catch (FileNotFoundException e) {
            throw new HaBitLoadException(e.toString());
        }

        return goalList;
    }

    protected Goal goalParser(String[] lineData) throws ParseException {
        GoalType goalType;
        Date dateStart;
        Date dateEnd;

        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        dateStart = format.parse(lineData[GOAL_START_INDEX]);
        dateEnd = format.parse(lineData[GOAL_END_INDEX]);

        switch (lineData[GOAL_TYPE_INDEX]) {
        case SLEEP:
            goalType = GoalType.SLEEP;
            break;
        case FOOD:
            goalType = GoalType.FOOD;
            break;
        case EXERCISE:
            goalType = GoalType.EXERCISE;
            break;
        case STUDY:
            goalType = GoalType.STUDY;
            break;
        default:
            goalType = GoalType.DEFAULT;
        }

        return new Goal(lineData[GOAL_NAME_INDEX],
                goalType,
                dateStart,
                dateEnd);
    }

    protected Habit habitParser(String[] lineData) {
        boolean isDone;

        if (lineData[DONE_INDEX].equals("0")) {
            isDone = false;
            assert isDone : "done should be false if value of done is 0";
        } else {
            isDone = true;
            assert !isDone : "done should be true if value of done is 1";
        }

        return new Habit(lineData[HABIT_NAME_INDEX],
                isDone);
    }

    protected void createFile(String filePath, String fileDir) {
        File storageDir = new File(fileDir);
        File storageFile = new File(filePath);

        if (!storageDir.exists()) {
            boolean isDirCreated = storageDir.mkdirs();

            if (isDirCreated) {
                System.out.println("Directory created: " + fileDir);
                assert !storageDir.exists() : "directory should have been created";
            } else {
                System.out.println("Directory not created");
            }
        }

        try {
            boolean isFileCreated = storageFile.createNewFile();

            if (isFileCreated) {
                System.out.println("File created: " + filePath);
                assert !storageFile.exists() : "file should have been created";
            } else {
                System.out.println("File exists");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating file: " + e);
        }
    }
}
