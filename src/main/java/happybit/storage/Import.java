package happybit.storage;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.habit.Habit;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Import {
    private static final String DELIMITER = "##";
    private static final String GOAL_TYPE = "G";
    private static final String HABIT_TYPE = "H";
    private static final int NUM_INDEX = 0;
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
                    try {
                        goalList.addGoal(ImportParser.goalParser(lineData));
                    } catch (ParseException e) {
                        throw new HaBitStorageException(e.toString());
                    }
                    break;
                case HABIT_TYPE:
                    Habit habit = ImportParser.habitParser(lineData);
                    int goalIndex = Integer.parseInt(lineData[NUM_INDEX]);

                    goalList.addHabitToGoal(habit, goalIndex);
                    break;
                default:
                    throw new HaBitStorageException("error while loading");
                }
            }
        } catch (FileNotFoundException e) {
            throw new HaBitStorageException(e.toString());
        } catch (HaBitCommandException e) {
            throw new HaBitStorageException(ERROR_INVALID_GOAL_INDEX);
        }

        return goalList;
    }
}
