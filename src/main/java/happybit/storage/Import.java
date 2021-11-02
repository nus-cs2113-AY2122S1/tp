package happybit.storage;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.habit.Habit;
import happybit.interval.Interval;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Import {
    private static final String DELIMITER = "##";
    private static final String GOAL_TYPE = "G";
    private static final String HABIT_TYPE = "H";
    private static final String INTERVAL_TYPE = "I";
    private static final int GOAL_INDEX = 0;
    private static final int TYPE_INDEX = 1;
    private static final int HABIT_INDEX = 2;
    private static final String ERROR_INVALID_GOAL_INDEX = "There is no goal at that index.";

    protected static GoalList importStorage(String filePath) throws HaBitStorageException {
        GoalList goalList = new GoalList();
        File storageFile = new File(filePath);
        Scanner s;
        String line;

        try {
            s = new Scanner(storageFile);

            while (s.hasNext()) {
                line = s.nextLine();
                String[] lineData = line.split(DELIMITER);

                updateGoalList(lineData, goalList);
            }
        } catch (FileNotFoundException | NumberFormatException | ParseException e) {
            throw new HaBitStorageException(e.getMessage());
        } catch (HaBitCommandException e) {
            throw new HaBitStorageException(ERROR_INVALID_GOAL_INDEX);
        }

        return goalList;
    }

    /**
     * This method will determine the data to add to GoalList and update accordingly.
     *
     * @param lineData the data read from the storage file
     * @param goalList the GoalList object to contain user data
     * @throws ParseException when string cannot be parsed to integers
     * @throws HaBitCommandException when there is error adding to GoalList
     * @throws HaBitStorageException when there is an unexpected data being read
     */
    protected static void updateGoalList(String[] lineData, GoalList goalList) throws ParseException,
            HaBitCommandException, HaBitStorageException {
        int goalIndex = Integer.parseInt(lineData[GOAL_INDEX]);

        switch (lineData[TYPE_INDEX]) {
        case GOAL_TYPE:
            goalList.addGoal(ImportParser.goalParser(lineData));
            break;
        case HABIT_TYPE:
            // this Habit object returned contains an empty ArrayList of Intervals
            Habit habit = ImportParser.habitParser(lineData);

            goalList.addHabitToGoal(habit, goalIndex);
            break;
        case INTERVAL_TYPE:
            int habitIndex = Integer.parseInt(lineData[HABIT_INDEX]);
            Interval interval = ImportParser.intervalParser(lineData);

            goalList.addIntervalToHabit(goalIndex, habitIndex, interval);
            break;
        default:
            throw new HaBitStorageException(ERROR_READING_DATA);
        }
    }
}
