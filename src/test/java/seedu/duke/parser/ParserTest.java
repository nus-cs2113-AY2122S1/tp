package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static seedu.duke.parser.AddExerciseParser.getExerciseArgs;

import static seedu.duke.parser.Parser.parseWorkoutAndExerciseIndex;
import static seedu.duke.parser.Parser.parseWorkoutIndex;
import static seedu.duke.parser.Parser.splitCommandWordsAndArgs;
import static seedu.duke.parser.Parser.parseArgsAsIndex;

class ParserTest {
    //featureUnderTest_testScenario_expectedBehavior()
    @Test
    void getExerciseArgs_emptyString_throwsException() {
        String[] emptyString = {" ", "\n", "\t", " "};
        for (String s : emptyString) {
            assertThrows(GetJackDException.class, () -> getExerciseArgs(s));
        }
    }

    @Test
    void getExerciseArgs_nonEmptyStringWorkoutIndex_throwsException() {
        String input = "Test input, 5 20";
        assertThrows(GetJackDException.class, () -> getExerciseArgs(input));
    }

    @Test
    void getExerciseArgs_validInput_returnsStringWithExerciseArgs() {
        String input = "exercise, 5 20, 1";
        try {
            assertArrayEquals(new String[]{"exercise", "5", "20", "1"}, getExerciseArgs(input));
        } catch (GetJackDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseWorkoutIndex_validInput_returnsWorkoutIndex() {
        String input = "  2  ";
        try {
            assertEquals(2, parseWorkoutIndex(input));
        } catch (GetJackDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseWorkoutIndex_invalidString_throwsException() {
        String input = "  a  ";
        assertThrows(GetJackDException.class, () -> parseWorkoutIndex(input));
    }

    @Test
    void parseWorkoutAndExerciseIndex_validInput_returnWorkoutAndExerciseIndices() {
        String input = " 2, 3";
        try {
            assertArrayEquals(new int[]{2, 3}, parseWorkoutAndExerciseIndex(input));
        } catch (GetJackDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseWorkoutAndExerciseIndex_invalidInputs_throwsException() {
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex(""));
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex("a"));
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex("2 2"));
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex("2,"));
    }

    @Test
    void splitCommandWordsAndArgs_inputStringMissingKeyword_returnInputString() {
        String input = "aalskdjlaksjd";
        String keyword = "$";
        assertArrayEquals(new String[]{input, ""}, splitCommandWordsAndArgs(input, keyword));
    }

    @Test
    void splitCommandWordsAndArgs_inputStringHasKeyword_returnsStringSplit() {
        String input = "alksjdj % lkj";
        String keyword = "%";
        assertArrayEquals(new String[]{"alksjdj ", " lkj"}, splitCommandWordsAndArgs(input, keyword));
    }

    @Test
    void parseArgsAsIndex_invalidNumber_throwsException() {
        String input = "4a";
        assertThrows(GetJackDException.class, () -> parseArgsAsIndex(input));
    }

    @Test
    void parseArgsAsIndex_validNumber_returnsInteger() {
        String input = "99";
        try {
            assertEquals(99, parseArgsAsIndex(input));
        } catch (GetJackDException e) {
            e.printStackTrace();
        }
    }
}
