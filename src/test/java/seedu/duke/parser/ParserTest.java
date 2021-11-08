package seedu.duke.parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
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
    @BeforeEach
    void setupTests() {
        Command.workoutMode = 0;
    }

    @AfterEach
    void resetWorkoutMode() {
        Command.workoutMode = 0;
    }

    @Test
    void getExerciseArgs_emptyString_throwsException() {
        String[] emptyString = {" ", "\n", "\t", " "};
        for (String s : emptyString) {
            assertThrows(GetJackDException.class, () -> getExerciseArgs(s, false));
        }
    }

    @Test
    void getExerciseArgs_nonEmptyStringWorkoutIndex_throwsException() {
        String input = "Test input, 5 20";
        assertThrows(GetJackDException.class, () -> getExerciseArgs(input, false));
    }

    @Test
    void getExerciseArgs_validInput_returnsStringWithExerciseArgs() {
        String input = "exercise, 5 20, 1";
        try {
            assertArrayEquals(new String[]{"exercise", "5", "20", "1"}, getExerciseArgs(input, false));
        } catch (GetJackDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getExerciseArgs_missingSetsOrRepsWhenInsideWorkout_throwsException() {
        String input = "exercise, 5";
        Command.workoutMode = 1;
        assertThrows(GetJackDException.class, () -> getExerciseArgs(input, false));
    }

    @Test
    void getExerciseArgs_missingWorkoutIndexWhenInsideWorkout_returnsStringWithExerciseArgs() throws GetJackDException {
        String input = "exercise, 5 20";
        Command.workoutMode = 1;
        assertArrayEquals(new String[]{"exercise", "5", "20", "1"}, getExerciseArgs(input, false));
    }

    @Test
    void parseWorkoutIndex_validInput_returnsWorkoutIndex() throws GetJackDException {
        String input = "  2  ";
        Command.workoutMode = 0;
        assertEquals(2, parseWorkoutIndex(input));
    }

    @Test
    void parseWorkoutIndex_inputWhenInsideAWorkout_returnsCorrectWorkoutIndex() throws GetJackDException {
        String input = " 2 ";
        Command.workoutMode = 1;
        assertEquals(2, parseWorkoutIndex(input));
    }

    @Test
    void parseWorkoutIndex_invalidString_throwsException() {
        String input = "  a  ";
        assertThrows(GetJackDException.class, () -> parseWorkoutIndex(input));
    }

    @Test
    void parseWorkoutIndex_emptyStringWhenInsideAWorkout_returnsWorkoutMode() throws GetJackDException {
        String input = "";
        Command.workoutMode = 2;
        assertEquals(2, parseWorkoutIndex(input));
    }

    @Test
    void parseWorkoutAndExerciseIndex_validInput_returnWorkoutAndExerciseIndices() throws GetJackDException {
        String input = " 2, 3";
        assertArrayEquals(new int[]{2, 3}, parseWorkoutAndExerciseIndex(input,false));
    }

    @Test
    void parseWorkoutAndExerciseIndex_validInputWhenInsideWorkout_returnsWorkoutModeAndExerciseIndex()
            throws GetJackDException {
        String input = " 2";
        Command.workoutMode = 1;
        assertArrayEquals(new int[]{2, 1}, parseWorkoutAndExerciseIndex(input,false));
    }

    @Test
    void parseWorkoutAndExerciseIndex_noInputWhenInsideWorkout_throwsGetJackdException() {
        String input = "";
        Command.workoutMode = 1;
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex(input,false));
    }

    @Test
    void parseWorkoutAndExerciseIndex_invalidInputs_throwsException() {
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex("",false));
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex("a",false));
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex("2 2",false));
        assertThrows(GetJackDException.class, () -> parseWorkoutAndExerciseIndex("2,",false));
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
