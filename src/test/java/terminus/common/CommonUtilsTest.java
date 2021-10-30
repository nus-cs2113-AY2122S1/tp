package terminus.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Link;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;

public class CommonUtilsTest {

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
        String input = "\"test1\" \"test2\"";
        resultExpected.add("test1");
        resultExpected.add("test2");
        ArrayList<String> result = CommonUtils.findArguments(input);
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_missingDoubleQuotes() {
        String input = "\"test1\" \"test2";
        ArrayList<String> result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "\"test1 test2";
        result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "test1 test2";
        result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_extraDoubleQuotes() {
        String input = "\"test1\" \"\"test2\"";
        ArrayList<String> result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());

        reset();
        input = "\"test1\" \"test2\"\"";
        result = CommonUtils.findArguments(input);
        assertEquals(0, result.size());

        reset();
        input = "\"test1\" \" \" \" \"";
        result = CommonUtils.findArguments(input);
        resultExpected.add("test1");
        resultExpected.add(" ");
        resultExpected.add(" ");
        assertEquals(3, result.size());
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
    void isValidUrl_invalidInput_exceptionThrown() throws InvalidArgumentException {
        assertFalse(CommonUtils.isValidUrl(""));
        assertFalse(CommonUtils.isValidUrl(".."));
    }

    @Test
    void isStringNullOrEmpty_success() {
        assertTrue(CommonUtils.isStringNullOrEmpty(null));
        assertTrue(CommonUtils.isStringNullOrEmpty(""));
        assertFalse(CommonUtils.isStringNullOrEmpty("test"));
    }

    @Test
    void isValidFileName_success() {
        assertTrue(CommonUtils.isValidFileName("test"));
        assertTrue(CommonUtils.isValidFileName("CS2113T"));
    }

    @Test
    void isValidFileName_invalidFileName() {
        assertFalse(CommonUtils.isValidFileName(""));
        assertFalse(CommonUtils.isValidFileName(null));
        assertFalse(CommonUtils.isValidFileName("\\uD83D\\uDE00"));
        String s = "a".repeat(31);
        assertFalse(CommonUtils.isValidFileName(s));
    }

    @Test
    void getFileNameOnly_success() {
        assertEquals("test", CommonUtils.getFileNameOnly("test.txt"));
    }

    @Test
    void getFileNameOnly_invalidFileName() {
        assertEquals(null, CommonUtils.getFileNameOnly("test.txt.txt"));
        assertEquals(null, CommonUtils.getFileNameOnly(""));
    }

    @Test
    void getFileNameOnly_nullInput_exceptionThrown() {
        assertThrows(AssertionError.class, () -> CommonUtils.getFileNameOnly(null));
    }

    @Test
    void isValidDuration_success() {
        assertTrue(CommonUtils.isValidDuration(0));
        assertTrue(CommonUtils.isValidDuration(1));
        assertTrue(CommonUtils.isValidDuration(2));
        assertFalse(CommonUtils.isValidDuration(-3));
        assertFalse(CommonUtils.isValidDuration(34));
        assertFalse(CommonUtils.isValidDuration(-1));
        assertFalse(CommonUtils.isValidDuration(25));
    }

    @Test
    void hasDurationOverflow_success() {
        assertTrue(CommonUtils.hasDurationOverflow(LocalTime.of(22,22), 2));
        assertTrue(CommonUtils.hasDurationOverflow(LocalTime.of(22,00), 3));
        assertTrue(CommonUtils.hasDurationOverflow(LocalTime.of(23,00), 2));
        assertTrue(CommonUtils.hasDurationOverflow(LocalTime.of(00,22), 24));
        assertTrue(CommonUtils.hasDurationOverflow(LocalTime.of(02,22), 23));
        assertFalse(CommonUtils.hasDurationOverflow(LocalTime.of(22,22), 1));
        assertFalse(CommonUtils.hasDurationOverflow(LocalTime.of(23,59), 0));
        assertFalse(CommonUtils.hasDurationOverflow(LocalTime.of(00,00), 0));
        assertFalse(CommonUtils.hasDurationOverflow(LocalTime.of(23,00), 0));
    }

    @Test
    void isValidIndex() {
        String[] list = new String[50];
        assertTrue(CommonUtils.isValidIndex(5, list));
        assertTrue(CommonUtils.isValidIndex(50, list));
        assertTrue(CommonUtils.isValidIndex(1, list));
        assertFalse(CommonUtils.isValidIndex(0, list));
        assertFalse(CommonUtils.isValidIndex(51, list));
        assertFalse(CommonUtils.isValidIndex(100, list));
    }

    @Test
    void getCurrentDay_success() {
        assertNotNull(CommonUtils.getCurrentDay());
    }
}
