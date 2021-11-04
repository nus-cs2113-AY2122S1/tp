package happybit.storage;

import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.habit.Habit;
import happybit.interval.Interval;
import happybit.ui.PrintManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Writes data from GoalList to storage file.
 */
public class Export {
    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = "##";
    private static final String GOAL_TYPE = "G";
    private static final String HABIT_TYPE = "H";
    private static final String INTERVAL_TYPE = "I";
    private static final String EMPTY = "";
    private static final String NULL = "null";
    private static final String FILE_WRITE = "Allow writing of data to storage file.";
    private static final String FILE_WRITE_FAIL = "Failed to write to storage file.";
    private static final String READ_ONLY = "Writing done. Storage file is updated and set to read only.";
    private static final String SET_READ_ONLY_FAILED = "Failed to set storage file as read only.";
    private static final String DATE_FORMAT = "ddMMyyyy";

    protected String filePath;
    protected PrintManager printManager;

    public Export(String filePath, PrintManager printManager) {
        this.filePath = filePath;
        this.printManager = printManager;
    }

    /**
     * Exports the entire GoalList to storage.
     *
     * @param goalList the arraylist of goals to be exported
     * @throws HaBitStorageException when an error occurred with storage file
     */
    protected void exportToStorage(ArrayList<Goal> goalList) throws HaBitStorageException {
        try {
            this.clearFile();
            this.writeToFile(goalList);
        } catch (IOException e) {
            throw new HaBitStorageException(e.toString());
        }
    }

    /**
     * Clears the storage file.
     *
     * @throws IOException error when writing to storage file
     */
    protected void clearFile() throws IOException {
        this.setWritable();

        FileWriter fileWriter = new FileWriter(this.filePath);

        fileWriter.write(EMPTY);
        fileWriter.close();
    }

    /**
     * Writes all the goals set to storage file.
     *
     * @param goalList the array list that contains all the user data
     * @throws IOException error when writing to the storage file
     */
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

    /**
     * Allows writing to the storage file which was set as read only.
     */
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
     * Sets the storage file back to read only after writing.
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

    /**
     * Writes all the habits under a certain goal into storage file.
     *
     * @param fileWriter the file to write to
     * @param habitList the list of habit under a goal
     * @param index the index of the goal the habit is under
     * @throws IOException error when writing to the storage file
     */
    protected void writeHabit(FileWriter fileWriter, ArrayList<Habit> habitList, int index) throws IOException {
        for (Habit habit : habitList) {
            int habitIndex = habitList.indexOf(habit);
            ArrayList<Interval> intervals = habit.getIntervals();
            String habitToWrite = this.habitString(habit, index);

            fileWriter.write(habitToWrite);
            this.writeInterval(fileWriter, intervals, index, habitIndex);
        }
    }

    /**
     * Writes all the intervals under a certain habit into storage file.
     *
     * @param fileWriter the file to write to
     * @param intervalList the list of intervals under a habit
     * @param goalIndex the index of goal the habit is under
     * @param habitIndex the index of habit the interval is for
     * @throws IOException error when writing to the storage file
     */
    protected void writeInterval(FileWriter fileWriter, ArrayList<Interval> intervalList, int goalIndex,
                                 int habitIndex) throws IOException {
        for (Interval interval : intervalList) {
            String intervalToWrite = this.intervalString(interval, goalIndex, habitIndex);

            fileWriter.write(intervalToWrite);
        }
    }

    /**
     * Converts a Goal object into a string format to be written to storage file.
     *
     * @param goal the Goal object to be converted to a string for storage
     * @param index the index of the goal in the goallist
     * @return the string representing the goal object
     */
    protected String goalString(Goal goal, int index) {
        return index + DELIMITER
                + GOAL_TYPE + DELIMITER
                + goal.getGoalTypeStr() + DELIMITER
                + goal.getGoalName() + DELIMITER
                + goal.getStartDate() + DELIMITER
                + goal.getStringEndDate() + NEWLINE;
    }

    /**
     * Converts a Habit object into a string format to be written to storage file.
     *
     * @param habit the Habit object to be converted to string for storage
     * @param index the index of the goal the habit is under
     * @return the string representing the habit object
     */
    protected String habitString(Habit habit, int index) {
        return index + DELIMITER
                + HABIT_TYPE + DELIMITER
                + habit.getHabitName() + DELIMITER
                + habit.getStartDate() + DELIMITER
                + habit.getEndDate() + DELIMITER
                + habit.getIntervalLength() + NEWLINE;
    }

    /**
     * Converts an Interval object into a string format to be written to storage file.
     *
     * @param interval the Interval object to be converted to string for storage
     * @param goalIndex the index of the goal the habit is under
     * @param habitIndex the index of the habit the interval is for
     * @return the string representing the interval object
     */
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

    /**
     * Exports a Goal object.
     *
     * @param goal the object to be written to storage
     * @param index the index of the goal
     * @throws HaBitStorageException error when writing to storage file
     */
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

    /**
     * Exports a Habit object.
     *
     * @param habit the object to be written to storage
     * @param index the index of the goal the habit is under
     * @throws HaBitStorageException error when writing to storage file
     */
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
