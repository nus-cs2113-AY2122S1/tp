package terminus.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertThrows(AssertionError.class, () -> CommonFormat.isArrayEmpty(resultExpected));
    }

    @Test
    void isArrayEmpty_nullArraylist_exceptionThrown() {
        assertThrows(AssertionError.class, () -> CommonFormat.isArrayEmpty(null));
    }

}
