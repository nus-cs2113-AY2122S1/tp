package happybit.storage;

import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.habit.Habit;
import happybit.interval.Interval;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Export {
    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = "##";
    private static final String GOAL_TYPE = "G";
    private static final String HABIT_TYPE = "H";
    private static final String INTERVAL_TYPE = "I";

    protected String filePath;

    public Export(String filePath) {
        this.filePath = filePath;
    }

    protected void exportStorage(ArrayList<Goal> goalList) throws HaBitStorageException {
        try {
            clearFile();
            writeToFile(goalList);
        } catch (IOException e) {
            throw new HaBitStorageException(e.toString());
        }
    }

    protected void clearFile() throws IOException {
        File storageFile = new File(this.filePath);
        boolean isWriteable = storageFile.setWritable(true);

        if (isWriteable) {
            System.out.println("Writing to storage file.");
        } else {
            System.out.println("Failed to write to storage file.");
        }

        FileWriter fileWriter = new FileWriter(this.filePath);

        fileWriter.write("");
        fileWriter.close();
    }

    protected void writeToFile(ArrayList<Goal> goalList) throws IOException {
        File storageFile = new File(this.filePath);
        FileWriter fileWriter = new FileWriter(this.filePath, true);

        for (Goal goal : goalList) {
            int index = goalList.indexOf(goal);
            ArrayList<Habit> habits = goal.getHabitList();
            String goalToWrite = this.goalString(goal, index);

            fileWriter.write(goalToWrite);
            this.writeHabit(fileWriter, habits, index);
        }
        fileWriter.close();

        boolean isReadOnly = storageFile.setReadOnly();

        if (isReadOnly) {
            System.out.println("Storage file is not writable.");
        } else {
            System.out.println("Failed to disable writing to storage file.");
        }
    }

    protected void writeHabit(FileWriter fileWriter, ArrayList<Habit> habitList, int index) throws IOException {
        for (Habit habit : habitList) {
            int habitIndex = habitList.indexOf(habit);
            ArrayList<Interval> intervals = habit.getIntervals();
            String habitToWrite = this.habitString(habit, index);

            fileWriter.write(habitToWrite);
            this.writeInterval(fileWriter, intervals, index, habitIndex);
        }
    }

    protected void writeInterval(FileWriter fileWriter, ArrayList<Interval> intervalList, int goalIndex,
                                 int habitIndex) throws IOException {
        for (Interval interval : intervalList) {
            String intervalToWrite = this.intervalString(interval, goalIndex, habitIndex);

            fileWriter.write(intervalToWrite);
        }
    }

    protected String goalString(Goal goal, int index) {
        return index + DELIMITER
                + GOAL_TYPE + DELIMITER
                + goal.getGoalTypeStr() + DELIMITER
                + goal.getGoalName() + DELIMITER
                + goal.getStartDate() + DELIMITER
                + goal.getStringEndDate() + NEWLINE;
    }

    protected String habitString(Habit habit, int index) {
        return index + DELIMITER
                + HABIT_TYPE + DELIMITER
                + habit.getHabitName() + DELIMITER
                + habit.getStartDate() + DELIMITER
                + habit.getEndDate() + DELIMITER
                + habit.getIntervalLength() + NEWLINE;
    }

    protected String intervalString(Interval interval, int goalIndex, int habitIndex) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        String startDate = format.format(interval.getStartDate());
        String endDate = format.format(interval.getEndDate());
        String completedDate;

        if (interval.getCompletedDate() == null) {
            completedDate = "null";
        } else {
            completedDate = format.format(interval.getCompletedDate());
        }

        return goalIndex + DELIMITER
                + INTERVAL_TYPE + DELIMITER
                + habitIndex + DELIMITER
                + startDate + DELIMITER
                + endDate + DELIMITER
                + completedDate + NEWLINE;
    }

    protected void exportGoal(Goal goal, int index) throws HaBitStorageException {
        try {
            File storageFile = new File(this.filePath);
            boolean isWriteable = storageFile.setWritable(true);

            if (isWriteable) {
                System.out.println("Writing to storage file.");
            } else {
                System.out.println("Failed to write to storage file.");
            }

            FileWriter fileWriter = new FileWriter(this.filePath, true);
            String goalToWrite = this.goalString(goal, index);

            fileWriter.write(goalToWrite);
            fileWriter.close();

            boolean isReadOnly = storageFile.setReadOnly();

            if (isReadOnly) {
                System.out.println("Storage file is not writable.");
            } else {
                System.out.println("Failed to disable writing to storage file.");
            }
        } catch (IOException e) {
            throw new HaBitStorageException(e.getMessage());
        }
    }

    protected void exportHabit(Habit habit, int index) throws HaBitStorageException {
        try {
            File storageFile = new File(this.filePath);
            boolean isWriteable = storageFile.setWritable(true);

            if (isWriteable) {
                System.out.println("Writing to storage file.");
            } else {
                System.out.println("Failed to write to storage file.");
            }

            FileWriter fileWriter = new FileWriter(this.filePath, true);
            String habitToWrite = this.habitString(habit, index);

            fileWriter.write(habitToWrite);
            fileWriter.close();

            boolean isReadOnly = storageFile.setReadOnly();

            if (isReadOnly) {
                System.out.println("Storage file is not writable.");
            } else {
                System.out.println("Failed to disable writing to storage file.");
            }
        } catch (IOException e) {
            throw new HaBitStorageException(e.getMessage());
        }
    }
}
