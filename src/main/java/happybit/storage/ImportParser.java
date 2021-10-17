package happybit.storage;

import happybit.goal.Goal;
import happybit.goal.GoalType;
import happybit.habit.Habit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportParser {
    private static final String SLEEP = "[SL]";
    private static final String FOOD = "[FD]";
    private static final String EXERCISE = "[EX]";
    private static final String STUDY = "[SD]";
    private static final String IS_DONE_VALUE = "1";
    private static final int GOAL_TYPE_INDEX = 2;
    private static final int DONE_INDEX = 2;
    private static final int GOAL_NAME_INDEX = 3;
    private static final int HABIT_NAME_INDEX = 3;
    private static final int GOAL_START_INDEX = 4;
    private static final int GOAL_END_INDEX = 5;

    protected static Goal goalParser(String[] lineData) throws ParseException {
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

    protected static Habit habitParser(String[] lineData) {
        Habit habit = new Habit(lineData[HABIT_NAME_INDEX]);

        if (lineData[DONE_INDEX].equals(IS_DONE_VALUE)) {
            habit.setCompleted();
        }

        return habit;
    }
}
