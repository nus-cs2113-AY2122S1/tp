package seedu.budgettracker.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserUtilTest {

    @Test
    void parseMonth_allString_0() {
        assertEquals(ParserUtil.parseMonth("all"),0);
    }

    @Test
    void parseMonth_2_2() {
        assertEquals(ParserUtil.parseMonth("2"),2);
    }

    @Test
    void parseMonth_emptyStringNotCompulsory_currentMonth() {
        int testMonth = 0;
        try {
            testMonth = ParserUtil.parseMonth("", false);
        } catch (ParserException e) {
            fail();
        }
        assertEquals(testMonth, LocalDate.now().getMonthValue());
    }
    
    @Test
    void parseIndex_2String_1() {
        int testIndex = 0;
        try {
            testIndex = ParserUtil.parseIndex("2");
        } catch (ParserException e) {
            fail();
        }
        assertEquals(testIndex,1);
    }

    @Test
    void parseMultipleIndexes_positiveNumbers_validArray() {
        int[] testIndexes = new int[2];
        try {
            testIndexes = ParserUtil.parseMultipleIndexes("1-5");
        } catch (ParserException e) {
            fail();
        }
        assertEquals(testIndexes[0],1);
        assertEquals(testIndexes[1],5);
    }

    @Test
    void parseDescription_nonEmptyString_trimmedString() {
        String testDesc = "";
        try {
            testDesc = ParserUtil.parseDescription(" Balls ", true);
        } catch (ParserException e) {
            fail();
        }
        assertEquals(testDesc,"Balls");
    }

    @Test
    void parseAmount() {
    }

    @Test
    void parseDate() {
    }

    @Test
    void parseName() {
    }

    @Test
    void parseCategory() {
    }

    @Test
    void parseListCategory() {
    }

    @Test
    void parseType() {
    }
}