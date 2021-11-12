package happybit.storage;

import happybit.goal.Goal;
import happybit.goal.GoalType;
import happybit.habit.Habit;
import happybit.interval.Interval;
import happybit.ui.PrintManager;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExportTest {
    private static final String DATE_FORMAT = "ddMMyyyy";
    private static final String START_DATE_1 = "05112021";
    private static final String END_DATE_1 = "07112021";
    private static final String GOAL_STRING_1 = "0##G##Default##example##05112021##07112021" + System.lineSeparator();
    private static final String HABIT_STRING_1 = "0##H##eg1##05112021##07112021##1" + System.lineSeparator();
    private static final String INTERVAL_STRING_1 = "0##I##0##07112021##07112021##null" + System.lineSeparator();
    private static final String INTERVAL_STRING_2 = "0##I##0##07112021##07112021##07112021" + System.lineSeparator();

    @Test
    public void goalStringTest() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date startDate;
        Date endDate;

        try {
            startDate = format.parse(START_DATE_1);
            endDate = format.parse(END_DATE_1);
            Goal goal = new Goal("example", GoalType.DEFAULT, startDate, endDate);

            assertEquals(GOAL_STRING_1, new Export("path", new PrintManager()).goalString(goal, 0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void habitStringTest() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date startDate;
        Date endDate;

        try {
            startDate = format.parse(START_DATE_1);
            endDate = format.parse(END_DATE_1);
            Habit habit = new Habit("eg1",startDate, endDate, 1);

            assertEquals(HABIT_STRING_1, new Export("path", new PrintManager()).habitString(habit, 0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void intervalStringTest() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date endDate;

        try {
            endDate = format.parse(END_DATE_1);
            Interval interval = new Interval(endDate, endDate);

            assertEquals(INTERVAL_STRING_1, new Export("path",
                    new PrintManager()).intervalString(interval, 0, 0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void intervalStringTest_completedInterval() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date endDate;

        try {
            endDate = format.parse(END_DATE_1);
            Interval interval = new Interval(endDate, endDate);
            interval.setCompleted(endDate);

            assertEquals(INTERVAL_STRING_2, new Export("path",
                    new PrintManager()).intervalString(interval, 0, 0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
