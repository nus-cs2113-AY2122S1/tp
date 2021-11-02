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
    private static final String EMPTY = "";
    private static final String NULL = "null";
    private static final String FILE_WRITE = "Allow writing to storage file.";
    private static final String FILE_WRITE_FAIL = "Failed to write to storage file.";
    private static final String READ_ONLY = "Writing done. Storage file set to read only.";
    private static final String SET_READ_ONLY_FAILED = "Failed to set storage file as read only.";
    private static final String DATE_FORMAT = "ddMMyyyy";

    protected String filePath;
    protected PrintManager printManager;

    public Export(String filePath, PrintManager printManager) {
        this.filePath = filePath;
        this.printManager = printManager;
    }

    protected void exportToStorage(ArrayList<Goal> goalList) throws HaBitStorageException {
        try {
            this.clearFile();
            this.writeToFile(goalList);
        } catch (IOException e) {
            throw new HaBitStorageException(e.toString());
        }
    }

    protected void clearFile() throws IOException {
        this.setWritable();

        FileWriter fileWriter = new FileWriter(this.filePath);

        fileWriter.write(EMPTY);
        fileWriter.close();
    }

    protected void writeToFile(ArrayList<Goal> goalList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath, true);

        for (Goal goal : goalList) {
            int index = goalList.indexOf(goal);
            ArrayList<Habit> habits = goal.getHabitList();
            String goalToWrite = this.goalString(goal, index);

            fileWriter.write(goalToWrite);
            this.writeHabit(fileWriter, habits, index);
        }
        fileWriter.close();
        this.setReadOnly();
    }

    protected void setWritable() {
        File storageFile = new File(this.filePath);
        boolean isWriteable = storageFile.setWritable(true);

        if (isWriteable) {
            this.printManager.printStorageMessage(FILE_WRITE);
        } else {
            this.printManager.printStorageMessage(FILE_WRITE_FAIL);
        }
    }

    /**
     * Set the storage file as read only.
     */
    protected void setReadOnly() {
        File storageFile = new File(this.filePath);
        boolean isReadOnly = storageFile.setReadOnly();

        if (isReadOnly) {
            this.printManager.printStorageMessage(READ_ONLY);
        } else {
            this.printManager.printStorageMessage(SET_READ_ONLY_FAILED);
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
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String startDate = format.format(interval.getStartDate());
        String endDate = format.format(interval.getEndDate());
        String completedDate;

        if (interval.getCompletedDate() == null) {
            completedDate = NULL;
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
        this.setWritable();

        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            String goalToWrite = this.goalString(goal, index);

            fileWriter.write(goalToWrite);
            fileWriter.close();
        } catch (IOException e) {
            throw new HaBitStorageException(e.getMessage());
        }

        this.setReadOnly();
    }

    protected void exportHabit(Habit habit, int index) throws HaBitStorageException {
        this.setWritable();

        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            String habitToWrite = this.habitString(habit, index);

            fileWriter.write(habitToWrite);
            fileWriter.close();
        } catch (IOException e) {
            throw new HaBitStorageException(e.getMessage());
        }

        this.setReadOnly();
    }
}
