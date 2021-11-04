package happybit.storage;

import happybit.goal.Goal;
import happybit.goal.GoalType;
import happybit.ui.PrintManager;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
