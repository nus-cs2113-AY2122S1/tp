package terminus.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Link;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;

public class CommonFormatTest {

    private ArrayList<String> resultExpected;

    void reset() {
        resultExpected = new ArrayList<>();
    }

    @BeforeEach
    void setup() {
        resultExpected = new ArrayList<>();
    }

    @Test
    void findArguments_success() {
        String input = "add \"test1\" \"test2\"";
        resultExpected.add("test1");
        resultExpected.add("test2");
        ArrayList<String> result = CommonUtils.findArguments(input);
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_missingDoubleQuotes() {
        String input = "add \"test1\" \"test2";
        ArrayList<String> result = CommonUtils.findArguments(input);
        resultExpected.add("test1");
        assertEquals(1, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add \"test1 test2";
        result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add test1 test2";
        result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_extraDoubleQuotes() {
        String input = "add \"test1\"\"\"test2\"";
        ArrayList<String> result = CommonUtils.findArguments(input);
        resultExpected.add("test1");
        resultExpected.add("");
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add \"test1\" \"test2\"\"";
        resultExpected.add("test1");
        resultExpected.add("test2");
        result = CommonUtils.findArguments(input);
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add \"test1\" \"\"\"test2";
        result = CommonUtils.findArguments(input);
        resultExpected.add("test1");
        resultExpected.add("");
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_missingArgument() {
        String input = "";
        ArrayList<String> result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_nullArgument_exceptionThrown() {
        String input = null;
        assertThrows(AssertionError.class, () -> CommonUtils.findArguments(input));
    }

    @Test
    void isArrayEmpty_success() {
        resultExpected.add("test1");
        resultExpected.add("test2");
        assertFalse(CommonUtils.hasEmptyString(resultExpected));
    }

    @Test
    void isArrayEmpty_emptyElements() {
        resultExpected.add("test1");
        resultExpected.add("");
        resultExpected.add("test2");
        assertTrue(CommonUtils.hasEmptyString(resultExpected));

        reset();
        System.out.println(resultExpected);
        assertTrue(CommonUtils.hasEmptyString(resultExpected));
    }

    @Test
    void isArrayEmpty_nullElements_exceptionThrown() {
        resultExpected.add("test1");
        resultExpected.add(null);
        resultExpected.add("test2");
        assertTrue(CommonUtils.hasEmptyString(resultExpected));
    }

    @Test
    void isArrayEmpty_nullArraylist_exceptionThrown() {
        assertThrows(AssertionError.class, () -> CommonUtils.hasEmptyString(null));
    }

    @Test
    void getClassName_success() {
        String result = CommonUtils.getClassName(Note.class);
        assertEquals("Note", result);
        result = CommonUtils.getClassName(Link.class);
        assertEquals("Link", result);
    }

    @Test
    void getClassName_invalidInput() {
        String result = CommonUtils.getClassName("test1");
        assertEquals("test1", result);
        result = CommonUtils.getClassName("test1.2");
        assertEquals("2", result);
    }

    @Test
    void getClassName_nullInput_exceptionThrown() {
        assertThrows(AssertionError.class, () -> CommonUtils.getClassName(null));
    }

    @Test
    void convertToLocalTime_success() throws InvalidArgumentException {
        assertTrue(CommonUtils.convertToLocalTime("11:56") instanceof LocalTime);
        assertTrue(CommonUtils.convertToLocalTime("22:56") instanceof LocalTime);
    }

    @Test
    void convertToLocalTime_invalidInput_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> CommonUtils.convertToLocalTime("test"));
        assertThrows(InvalidArgumentException.class, () -> CommonUtils.convertToLocalTime("25:10"));
        assertThrows(InvalidArgumentException.class, () -> CommonUtils.convertToLocalTime("11-10"));
        assertThrows(AssertionError.class, () -> CommonUtils.convertToLocalTime(null));
    }

    @Test
    void isValidDay_success() {
        assertTrue(CommonUtils.isValidDay("monday"));
        assertTrue(CommonUtils.isValidDay("MoNdAy"));
        assertTrue(CommonUtils.isValidDay("tuesday"));
        assertTrue(CommonUtils.isValidDay("wednesday"));
        assertTrue(CommonUtils.isValidDay("thursday"));
        assertTrue(CommonUtils.isValidDay("friday"));
        assertTrue(CommonUtils.isValidDay("saturday"));
        assertTrue(CommonUtils.isValidDay("sunday"));
    }

    @Test
    void isValidDay_invalidInput() {
        assertFalse(CommonUtils.isValidDay("mon"));
        assertFalse(CommonUtils.isValidDay("test1"));
        assertFalse(CommonUtils.isValidDay("wednesdey"));
        assertFalse(CommonUtils.isValidDay(""));
        assertFalse(CommonUtils.isValidDay(null));
    }

    @Test
    void isValidUrl_success() throws InvalidArgumentException {
        assertTrue(CommonUtils.isValidUrl("https://www.test.com"));
        assertTrue(CommonUtils.isValidUrl("http://www.test.org"));
        assertTrue(CommonUtils.isValidUrl("https://nus-sg.zoom.us/j/88433650229?pwd=NFg3WSl0UEQ5ZG05ZW1MZz09"));
    }

    @Test
    void isValidUrl_invalidInput_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> CommonUtils.isValidUrl(""));
        assertThrows(InvalidArgumentException.class, () -> CommonUtils.isValidUrl(".."));
    }

}
