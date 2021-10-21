package happybit.parser;

import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ListHabitParserTest {
    private static final String ERROR_INVALID_GOAL_NUMBER = "Please enter a valid goal number";

    @Test
    void parseInvalidInput_whitespace_exceptionThrown() {
        try {
            ListHabitParser.parseListHabitCommand("    ");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_GOAL_NUMBER, e.getMessage());
        }
    }

    @Test
    void parseInvalidInput_alphabeticalCharacter_exceptionThrown() {
        final ArrayList<String> inputs = new ArrayList<>();
        inputs.add("a");
        inputs.add("aa");

        for (String input : inputs) {
            try {
                ListHabitParser.parseListHabitCommand(input);
                fail();
            } catch (HaBitParserException e) {
                assertEquals(ERROR_INVALID_GOAL_NUMBER, e.getMessage());
            }
        }
    }

    @Test
    void parseInvalidInput_specialCharacter_exceptionThrown() {
        final ArrayList<String> inputs = new ArrayList<>();
        inputs.add("-");
        inputs.add("?");

        for (String input : inputs) {
            try {
                ListHabitParser.parseListHabitCommand(input);
                fail();
            } catch (HaBitParserException e) {
                assertEquals(ERROR_INVALID_GOAL_NUMBER, e.getMessage());
            }
        }
    }

    @Test
    void parseInvalidInput_float_exceptionThrown() {
        try {
            ListHabitParser.parseListHabitCommand("0.0");
            fail();
        } catch (HaBitParserException e) {
            assertEquals(ERROR_INVALID_GOAL_NUMBER, e.getMessage());
        }
    }

    @Test
    void parseValidInput_integer_success() throws HaBitParserException {
        assertEquals(-1,
                ((ListHabitsCommand) ListHabitParser.parseListHabitCommand("0")).getGoalIndex());
        assertEquals(0,
                ((ListHabitsCommand) ListHabitParser.parseListHabitCommand("1")).getGoalIndex());
    }
}