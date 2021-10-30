package taa;

//@@author leyondlee
import org.junit.jupiter.api.Test;
import taa.exception.TaaException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void getArgumentsFromString_noKeys_expectEmpty() throws TaaException {
        String inputString = "c/CS2113T_-() n/Software Engineering and Object-oriented Programming_-()";
        String[] keys = {};
        assertTrue(Parser.getArgumentsFromString(inputString, keys).isEmpty());
    }

    @Test
    void getArgumentsFromString_oneKey_expectOneItem() throws TaaException {
        String inputString = "c/CS2113T_-() n/Software Engineering and Object-oriented Programming_-()";
        String[] keys = {"n"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 1);
    }

    @Test
    void getArgumentsFromString_twoKeys_expectTwoItems() throws TaaException {
        String inputString = "c/CS2113T_-() n/Software Engineering and Object-oriented Programming_-()";
        String[] keys = {"c","n"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 2);
    }

    @Test
    void getArgumentsFromString_threeKeys_expectThreeItems() throws TaaException {
        String inputString = "c/CS2113T_-() a/Midterms_-() w/20_-()";
        String[] keys = {"c", "a", "w"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 3);
    }

    @Test
    void getArgumentsFromString_OneKeyAndEmptyValue_expectEmptyValue() throws TaaException {
        String inputString = "c/";
        String[] keys = {"c"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).get("c"), "");
    }

    @Test
    void getArgumentsFromString_TwoKeyAndOneEmptyValue_expectOneItem() throws TaaException {
        String inputString = "c/ n/Software Engineering and Object-oriented Programming";
        String[] keys = {"c","n"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 2);
    }
}
