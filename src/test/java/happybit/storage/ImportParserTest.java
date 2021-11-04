package happybit.storage;

import happybit.goal.Goal;
import happybit.interval.Interval;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImportParserTest {
    private static final String DELIMITER = "##";
    private static final String DATE_FORMAT = "ddMMyyyy";
    private static final String START_DATE_1 = "05112021";
    private static final String END_DATE_1 = "07112021";
    private static final String TEST_GOAL_1 = "0##G##Sleep##example##05112021##07112021";
    private static final String TEST_GOAL_2 = "0##G##Food##example##05112021##07112021";
    private static final String TEST_GOAL_3 = "0##G##Exercise##example##05112021##07112021";
    private static final String TEST_GOAL_4 = "0##G##Study##example##05112021##07112021";
    private static final String TEST_GOAL_5 = "0##G##Default##example##05112021##07112021";
    private static final String TEST_INTERVAL_1 = "0##I##0##07112021##07112021##null";
    private static final String TEST_INTERVAL_2 = "0##I##0##07112021##07112021##07112021";

    @Test
    public void goalParser_sleep() {
        String[] lineData = TEST_GOAL_1.split(DELIMITER);

        try {
            Goal goal = ImportParser.goalParser(lineData);

            assertEquals("Sleep", goal.getGoalTypeStr());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goalParser_food() {
        String[] lineData = TEST_GOAL_2.split(DELIMITER);

        try {
            Goal goal = ImportParser.goalParser(lineData);

            assertEquals("Food", goal.getGoalTypeStr());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goalParser_exercise() {
        String[] lineData = TEST_GOAL_3.split(DELIMITER);

        try {
            Goal goal = ImportParser.goalParser(lineData);

            assertEquals("Exercise", goal.getGoalTypeStr());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goalParser_study() {
        String[] lineData = TEST_GOAL_4.split(DELIMITER);

        try {
            Goal goal = ImportParser.goalParser(lineData);

            assertEquals("Study", goal.getGoalTypeStr());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goalParser_default() {
        String[] lineData = TEST_GOAL_5.split(DELIMITER);

        try {
            Goal goal = ImportParser.goalParser(lineData);

            assertEquals("Default", goal.getGoalTypeStr());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void intervalParser_notCompleted() {
        String[] lineData = TEST_INTERVAL_1.split(DELIMITER);

        try {
            Interval interval = ImportParser.intervalParser(lineData);

            assertFalse(interval.getDone());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void intervalParser_completed() {
        String[] lineData = TEST_INTERVAL_2.split(DELIMITER);

        try {
            Interval interval = ImportParser.intervalParser(lineData);

            assertTrue(interval.getDone());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
