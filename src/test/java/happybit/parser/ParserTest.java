package happybit.parser;

import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    private static final String ERROR_NAME_FORMAT = "Use the 'n/' flag to define the name. Exp: n/Foo";
    private static final String ERROR_GOAL_TYPE_FORMAT = "Use the 't/' flag to define the goal type. Exp: t/df";
    private static final String ERROR_INTEGER_FLAG_FORMAT = "The command is missing the '%1$s' flag";
    private static final String ERROR_CONVERT_NUM = "The flag '%1$s' has to be followed by a number";
    private static final String ERROR_NEGATIVE_NUM = "The flag '%1$s' has to be followed by a positive integer";
    private static final String ERROR_UNDEFINED_GOAL_TYPE_LABEL = "Use the following goal types: 'sl', 'fd', 'ex', 'sd', 'df'";
    private static final String ERROR_NO_DESCRIPTION = "Use a description of at least 1 character";
    private static final String ERROR_LONG_DESCRIPTION = "Use a description no more than 50 characters "
            + "(current: %1$s characters)";
    private static final int MAX_NAME_LENGTH = 50;

    @Test
    void splitInput_validInput_expectValidSplit() {
        String input = "  n/ input/1    h/input/2 g/input/ is/3 i/$%   %$/4   ";
        ArrayList<String> splitInput = Parser.splitInput(input);
        assertEquals("n/input/1", splitInput.get(0));
        assertEquals("h/input/2", splitInput.get(1));
        assertEquals("g/input/ is/3", splitInput.get(2));
        assertEquals("i/$%   %$/4", splitInput.get(3));

        assertEquals("n/input", Parser.splitInput("n/input").get(0));
    }

    @Test
    void getName_noFlag_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("");
        parameters.add("i/1");
        parameters.add("e/11112021");
        try {
            Parser.getName(parameters);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NAME_FORMAT, e.getMessage());
        }
    }

    @Test
    void getName_name0Characters_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("n/");
        try {
            Parser.getName(parameters);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_NO_DESCRIPTION, e.getMessage());
        }
    }

    @Test
    void getName_name50Characters_expectSameString() throws HaBitParserException {
        String length50String = "0".repeat(MAX_NAME_LENGTH);
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("n/ " + length50String);
        assertEquals(length50String, Parser.getName(parameters));
    }

    @Test
    void getName_name51Characters_expectException() {
        String length51String = "0".repeat(MAX_NAME_LENGTH + 1);
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("n/ " + length51String);
        try {
            Parser.getName(parameters);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_LONG_DESCRIPTION, MAX_NAME_LENGTH + 1), e.getMessage());
        }
    }

    @Test
    void getName_twoNameFlags_expectFirstName() throws HaBitParserException {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("n/Name 1");
        parameters.add("n/Name 2");
        assertEquals("Name 1", Parser.getName(parameters));
    }

    @Test
    void getNumber_negativeNumber_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("g/-1");
        parameters.add("h/a");
        parameters.add("i/");
        try {
            Parser.getNumber(parameters, "g/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_NEGATIVE_NUM, "g/"), e.getMessage());
        }
    }

    @Test
    void getNumber_nonInteger_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("g/-1");
        parameters.add("h/a");
        parameters.add("i/");
        try {
            Parser.getNumber(parameters, "h/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_CONVERT_NUM, "h/"), e.getMessage());
        }
    }

    @Test
    void getNumber_noNumber_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("g/-1");
        parameters.add("h/a");
        parameters.add("i/");
        try {
            Parser.getNumber(parameters, "i/");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_CONVERT_NUM, "i/"), e.getMessage());
        }
    }

    @Test
    void getNumber_twoDigitNumber_expectSameNumber() throws HaBitParserException {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("g/99");
        parameters.add("h/a");
        parameters.add("i/");
        assertEquals(99, Parser.getNumber(parameters, "g/"));
    }

    @Test
    void getNumber_noFlag_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("g/99");
        parameters.add("h/a");

        try {
            Parser.getNumber(parameters, "i/");
        } catch (HaBitParserException e) {
            assertEquals(String.format(ERROR_INTEGER_FLAG_FORMAT, "i/"), e.getMessage());
        }
    }

    @Test
    void getType_noFlag_expectDefault() throws HaBitParserException {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("");
        parameters.add("n/Test");
        parameters.add("e/11112021");
        assertEquals(GoalType.DEFAULT, Parser.getType(parameters));
    }

    @Test
    void getType_flagWithoutLabel_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("t/");
        parameters.add("n/Test");
        parameters.add("e/11112021");
        try {
            Parser.getType(parameters);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_GOAL_TYPE_FORMAT, e.getMessage());
        }
    }

    @Test
    void getType_flagWithAUndefinedLabel_expectException() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("t/??");
        parameters.add("n/Test");
        parameters.add("e/11112021");
        try {
            Parser.getType(parameters);
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_UNDEFINED_GOAL_TYPE_LABEL, e.getMessage());
        }
    }

    @Test
    void getType_flagWithCorrectLabel_expectGoalTypeOfLabel() throws HaBitParserException {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("t/sl");
        assertEquals(GoalType.SLEEP, Parser.getType(parameters));

        parameters.replaceAll(s -> "t/fd");
        assertEquals(GoalType.FOOD, Parser.getType(parameters));

        parameters.replaceAll(s -> "t/ex");
        assertEquals(GoalType.EXERCISE, Parser.getType(parameters));

        parameters.replaceAll(s -> "t/sd");
        assertEquals(GoalType.STUDY, Parser.getType(parameters));

        parameters.replaceAll(s -> "t/df");
        assertEquals(GoalType.DEFAULT, Parser.getType(parameters));
    }
}