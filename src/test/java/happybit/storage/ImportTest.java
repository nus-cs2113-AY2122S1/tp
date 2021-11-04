package happybit.storage;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.habit.Habit;
import happybit.interval.Interval;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ImportTest {
    private static final String DELIMITER = "##";
    private static final String TEST_1 = "0##G##Default##example##03##12122021";
    private static final String TEST_2 = "a##G##Default##example##03112021##12122021";
    private static final String TEST_3 = "10##H##eg1##03112021##12122021##1";
    private static final String TEST_4 = "0##Z##eg1##03112021##12122021##1";
    private static final String TEST_5 = "0##G##Default##example##05112021##07112021";
    private static final String TEST_6 = "0##H##eg1##05112021##07112021##1";
    private static final String TEST_7 = "0##I##0##05112021##06112021##null";
    private static final String TEST_8 = "0##I##0##06112021##07112021##null";
    private static final String TEST_9 = "0##I##0##07112021##07112021##null";

    @Test
    public void updateGoalList_incorrectDateFormat_parseException() {
        String[] lineData = TEST_1.split(DELIMITER);
        GoalList goalList = new GoalList();

        ParseException thrown = assertThrows(ParseException.class, () -> {
            Import.updateGoalList(lineData, goalList);
        });
    }

    @Test
    public void updateGoalList_notInt_parseException() {
        String[] lineData = TEST_2.split(DELIMITER);
        GoalList goalList = new GoalList();

        NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
            Import.updateGoalList(lineData, goalList);
        });
    }

    @Test
    public void updateGoalList_indexOutOfBound_habitCommandException() {
        String[] lineData = TEST_3.split(DELIMITER);
        GoalList goalList = new GoalList();

        HaBitCommandException thrown = assertThrows(HaBitCommandException.class, () -> {
            Import.updateGoalList(lineData, goalList);
        });
    }

    @Test
    public void updateGoalList_invalidClassType_habitStorageException() {
        String[] lineData = TEST_4.split(DELIMITER);
        GoalList goalList = new GoalList();

        HaBitStorageException thrown = assertThrows(HaBitStorageException.class, () -> {
            Import.updateGoalList(lineData, goalList);
        });
    }

    @Test
    public void updateGoalList_addGoalHabitInterval_success() {
        String[] lineData1 = TEST_5.split(DELIMITER);
        String[] lineData2 = TEST_6.split(DELIMITER);
        String[] lineData3 = TEST_7.split(DELIMITER);
        String[] lineData4 = TEST_8.split(DELIMITER);
        String[] lineData5 = TEST_9.split(DELIMITER);
        GoalList goalList = new GoalList();

        try {
            Import.updateGoalList(lineData1, goalList);
            Import.updateGoalList(lineData2, goalList);
            Import.updateGoalList(lineData3, goalList);
            Import.updateGoalList(lineData4, goalList);
            Import.updateGoalList(lineData5, goalList);
        } catch (HaBitStorageException | HaBitCommandException | ParseException e) {
            e.printStackTrace();
        }
//        0##G##Default##example##05112021##07112021
//        0##H##eg1##05112021##07112021##1
//        0##I##0##05112021##06112021##null
//        0##I##0##06112021##07112021##null
//        0##I##0##07112021##07112021##null
        assertAll(() -> {
            assertEquals(1, goalList.getListLength());

            ArrayList<Goal> goals = goalList.getGoalList();
            Goal goal1 = goals.get(0);
            ArrayList<Habit> habits = goal1.getHabitList();

            assertEquals("[DF] example", goal1.getDescription());
            assertEquals(1, habits.size());

            Habit habit1 = habits.get(0);

            assertEquals("eg1", habit1.getHabitName());
            assertEquals(1, habit1.getIntervalLength());
            assertEquals(3, habit1.getTotalIntervals());
        });
    }
}
