package taa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void getArgumentsFromString_noKeys_expectEmpty() {
        String inputString = "c/CS2113T n/Software Engineering and Object-oriented Programming";
        String[] keys = {};
        assertTrue(Parser.getArgumentsFromString(inputString, keys).isEmpty());
    }

    @Test
    void getArgumentsFromString_oneKey_expectOneItem() {
        String inputString = "c/CS2113T n/Software Engineering and Object-oriented Programming";
        String[] keys = {"n"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 1);
    }

    @Test
    void getArgumentsFromString_twoKeys_expectTwoItems() {
        String inputString = "c/CS2113T n/Software Engineering and Object-oriented Programming";
        String[] keys = {"c","n"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 2);
    }
}
