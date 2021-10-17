package happybit.storage;

import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.habit.Habit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Export {
    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = "##";
    private static final String GOAL_TYPE = "G";
    private static final String HABIT_TYPE = "H";

    protected static void exportStorage(ArrayList<Goal> goalList, String filePath) throws HaBitStorageException {
        try {
            clearFile(filePath);
            writeToFile(goalList, filePath);
        } catch (IOException e) {
            throw new HaBitStorageException(e.toString());
        }
    }

    protected static void clearFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

        fileWriter.write("");
        fileWriter.close();
    }

    protected static void writeToFile(ArrayList<Goal> goalList, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);

        for (Goal goal : goalList) {
            int index = goalList.indexOf(goal);
            ArrayList<Habit> habits = goal.getHabitList();
            String goalToWrite = index + DELIMITER
                    + GOAL_TYPE + DELIMITER
                    + goal.getGoalTypeCharacter() + DELIMITER
                    + goal.getGoalName() + DELIMITER
                    + goal.getStartDate() + DELIMITER
                    + goal.getEndDate() + NEWLINE;

            fileWriter.write(goalToWrite);

            for (Habit habit : habits) {
                int doneValue = habit.getDone() ? 1 : 0;
                String habitToWrite = index + DELIMITER
                        + HABIT_TYPE + DELIMITER
                        + doneValue + DELIMITER
                        + habit.getHabitName() + NEWLINE;

                fileWriter.write(habitToWrite);
            }
        }
        fileWriter.close();
    }
}
