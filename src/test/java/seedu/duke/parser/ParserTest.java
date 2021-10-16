package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static seedu.duke.parser.AddExerciseParser.getExerciseArgs;
import static seedu.duke.parser.Parser.getWorkoutAndExerciseIndices;
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
    void getExerciseArgs_nonEmptyStringMissingSetsKeyword_throwsException() {
        String input = "\"/w 1 /e exercise /s 10 100";
        assertThrows(GetJackDException.class, () -> getExerciseArgs(input));
    }

    @Test
    void getExerciseArgs_validInput_returnsStringWithExerciseArgs() {
        String input = "/w 1 /e exercise /s 10 /r 30";
        try {
            assertArrayEquals(new String[]{"1", "exercise", "10", "30"}, getExerciseArgs(input));
        } catch (GetJackDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getWorkoutAndExerciseIndices_noWorkoutKeyword_returnEmptyStringArray() {
        String input = "/e 1";
        assertArrayEquals(new String[]{"", ""}, getWorkoutAndExerciseIndices(input));
    }

    @Test
    void getWorkoutAndExerciseIndices_haveWorkoutKeywordNoExerciseKeyword_returnWorkoutIndex() {
        String input = "/w 4";
        assertArrayEquals(new String[]{"4", ""}, getWorkoutAndExerciseIndices(input));
    }

    @Test
    void getWorkoutAndExerciseIndices_haveWorkoutAndExerciseKeyword_returnWorkoutAndExerciseIndices() {
        String input = "/w 4 /e 5";
        assertArrayEquals(new String[]{"4", "5"}, getWorkoutAndExerciseIndices(input));
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
