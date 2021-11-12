package seedu.budgettracker.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.records.Category;
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
    void parseAmount_numberString_sameNumberAsDbl() {
        double testInt = 0;
        try {
            testInt = ParserUtil.parseAmount("10",true);
        } catch (ParserException e) {
            fail();
        }
        assertEquals(testInt,10.00);
    }

    @Test
    void parseAmount_emptyStringNotCompulsory_0() {
        double testInt = 0;
        try {
            testInt = ParserUtil.parseAmount("",false);
        } catch (ParserException e) {
            fail();
        }
        assertEquals(testInt,0.00);
    }

    @Test
    void parseDate_emptyString_LocalDateNow() {
        try {
            assertEquals(ParserUtil.parseDate("",true),LocalDate.of(9898,1,1));
        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    void parseName_string_trimmedString() {
        try {
            assertEquals(ParserUtil.parseName(" Test ",true),"Test");
        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    void parseCategory_lowerCaseString_upperCaseString() {
        try {
            assertEquals(ParserUtil.parseCategory("food",false), Category.FOOD);
        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    void parseCategory_emptyString_categoryGeneral() {
        try {
            assertEquals(ParserUtil.parseCategory("",true),Category.INVALID);
        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    void parseListCategory_emptyString_categoryAll() {
        try {
            assertEquals(ParserUtil.parseListCategory(""),Category.ALL);
        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    void parseType_numString_numInt() {
        try {
            assertEquals(ParserUtil.parseType("1",true),1);
        } catch (ParserException e) {
            fail();
        }
    }
}