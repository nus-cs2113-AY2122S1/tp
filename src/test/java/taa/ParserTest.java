package taa;

//@@author leyondlee
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

    @Test
    void getArgumentsFromString_threeKeys_expectThreeItems() {
        String inputString = "c/CS2113T a/Midterms w/20";
        String[] keys = {"c", "a", "w"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 3);
    }

    @Test
    void getArgumentsFromString_OneKeyAndEmptyValue_expectZeroItems() {
        String inputString = "c/";
        String[] keys = {"c"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 0);
    }

    @Test
    void getArgumentsFromString_TwoKeyAndOneEmptyValue_expectOneItem() {
        String inputString = "c/ n/Software Engineering and Object-oriented Programming";
        String[] keys = {"c","n"};
        assertEquals(Parser.getArgumentsFromString(inputString, keys).size(), 1);
    }
}
