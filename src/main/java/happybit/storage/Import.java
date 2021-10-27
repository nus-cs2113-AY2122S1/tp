package happybit.storage;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
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
    private static final String ERROR_INVALID_GOAL_INDEX = "There is no goal at that index.";

    protected static GoalList importStorage(String filePath) throws HaBitStorageException {
        GoalList goalList = new GoalList();
        Scanner s;
        String line;

        File storageFile = new File(filePath);

        try {
            s = new Scanner(storageFile);

            while (s.hasNext()) {
                line = s.nextLine();
                String[] lineData = line.split(DELIMITER);

                switch (lineData[TYPE_INDEX]) {
                case GOAL_TYPE:
                    goalList.addGoal(ImportParser.goalParser(lineData));
                    break;
                case HABIT_TYPE:
                    Habit habit = ImportParser.habitParser(lineData);
                    int goalIndex = Integer.parseInt(lineData[GOAL_INDEX]);
                    ArrayList<Interval> tempIntervalList = new ArrayList<>();

                    while (lineData[GOAL_INDEX].equals(INTERVAL_TYPE)) {
                        tempIntervalList.add(ImportParser.intervalParser(lineData));
                    }

                    habit.setIntervals(tempIntervalList);
                    goalList.addHabitToGoal(habit, goalIndex);
                    break;
                default:
                    throw new HaBitStorageException("error while loading");
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            throw new HaBitStorageException(e.getMessage());
        } catch (HaBitCommandException e) {
            throw new HaBitStorageException(ERROR_INVALID_GOAL_INDEX);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return goalList;
    }
}
