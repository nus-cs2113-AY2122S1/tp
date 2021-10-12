package terminus.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        ArrayList<String> result = CommonFormat.findArguments(input);
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_missingDoubleQuotes() {
        String input = "add \"test1\" \"test2";
        ArrayList<String> result = CommonFormat.findArguments(input);
        resultExpected.add("test1");
        assertEquals(1, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add \"test1 test2";
        result = CommonFormat.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add test1 test2";
        result = CommonFormat.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_extraDoubleQuotes() {
        String input = "add \"test1\"\"\"test2\"";
        ArrayList<String> result = CommonFormat.findArguments(input);
        resultExpected.add("test1");
        resultExpected.add("");
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add \"test1\" \"test2\"\"";
        resultExpected.add("test1");
        resultExpected.add("test2");
        result = CommonFormat.findArguments(input);
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);

        reset();
        input = "add \"test1\" \"\"\"test2";
        result = CommonFormat.findArguments(input);
        resultExpected.add("test1");
        resultExpected.add("");
        assertEquals(2, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_missingArgument() {
        String input = "";
        ArrayList<String> result = CommonFormat.findArguments(input);
        assertEquals(0, result.size());
        assertEquals(resultExpected, result);
    }

    @Test
    void findArguments_nullArgument_exceptionThrown() {
        String input = null;
        assertThrows(AssertionError.class, () -> CommonFormat.findArguments(input));
    }

    @Test
    void isArrayEmpty_success() {
        resultExpected.add("test1");
        resultExpected.add("test2");
        assertFalse(CommonFormat.isArrayEmpty(resultExpected));
    }

    @Test
    void isArrayEmpty_emptyElements() {
        resultExpected.add("test1");
        resultExpected.add("");
        resultExpected.add("test2");
        assertTrue(CommonFormat.isArrayEmpty(resultExpected));

        reset();
        System.out.println(resultExpected);
        assertTrue(CommonFormat.isArrayEmpty(resultExpected));
    }

    @Test
    void isArrayEmpty_nullElements_exceptionThrown() {
        resultExpected.add("test1");
        resultExpected.add(null);
        resultExpected.add("test2");
        assertTrue(CommonFormat.isArrayEmpty(resultExpected));
    }

    @Test
    void isArrayEmpty_nullArraylist_exceptionThrown() {
        assertThrows(AssertionError.class, () -> CommonFormat.isArrayEmpty(null));
    }

    @Test
    void getClassName_success() {
        String result = CommonFormat.getClassName(Note.class);
        assertEquals("Note", result);
        result = CommonFormat.getClassName(Link.class);
        assertEquals("Link", result);
    }

    @Test
    void getClassName_invalidInput() {
        String result = CommonFormat.getClassName("test1");
        assertEquals("test1", result);
        result = CommonFormat.getClassName("test1.2");
        assertEquals("2", result);
    }

    @Test
    void getClassName_nullInput_exceptionThrown() {
        assertThrows(AssertionError.class, () -> CommonFormat.getClassName(null));
    }

    @Test
    void convertToLocalTime_success() throws InvalidArgumentException {
        assertTrue(CommonFormat.convertToLocalTime("11:56") instanceof LocalTime);
        assertTrue(CommonFormat.convertToLocalTime("22:56") instanceof LocalTime);
    }

    @Test
    void convertToLocalTime_invalidInput_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> CommonFormat.convertToLocalTime("test"));
        assertThrows(InvalidArgumentException.class, () -> CommonFormat.convertToLocalTime("25:10"));
        assertThrows(InvalidArgumentException.class, () -> CommonFormat.convertToLocalTime("11-10"));
        assertThrows(AssertionError.class, () -> CommonFormat.convertToLocalTime(null));
    }

}
