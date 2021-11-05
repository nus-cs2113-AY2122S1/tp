package happybit.parser;

import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void getName_noFlag_expectException() {
        String[] parameters = {"", "i/1", "e/11112021"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getName(parameters));
    }

    @Test
    void getName_name0Characters_expectException() {
        String[] parameters = {"n/"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getName(parameters));
    }

    @Test
    void getName_name50Characters_expectSameString() throws HaBitParserException {
        String length50String = "01234567890123456789012345678901234567890123456789";
        String[] parameters = {"n/" + length50String};
        assertEquals(length50String, Parser.getName(parameters));
    }

    @Test
    void getName_name51Characters_expectException() {
        String[] parameters = {"n/012345678901234567890123456789012345678901234567890"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getName(parameters));
    }

    @Test
    void getName_twoNameFlags_expectFirstName() throws HaBitParserException {
        String[] parameters = {"n/Name 1", "n/Name 2"};
        assertEquals("Name 1", Parser.getName(parameters));
    }

    @Test
    void getNumber_negativeNumber_expectException() {
        String[] parameters = {"g/-1", "h/a", "i/"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getNumber(parameters, "g/"));
    }

    @Test
    void getNumber_nonInteger_expectException() {
        String[] parameters = {"g/-1", "h/a", "i/"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getNumber(parameters, "h/"));
    }

    @Test
    void getNumber_noNumber_expectException() {
        String[] parameters = {"g/-1", "h/a", "i/"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getNumber(parameters, "i/"));
    }

    @Test
    void getNumber_twoDigitNumber_expectSameNumber() throws HaBitParserException {
        String[] parameters = {"g/99", "h/a", "i/"};
        assertEquals(99, Parser.getNumber(parameters, "g/"));
    }

    @Test
    void getType_noFlag_expectDefault() throws HaBitParserException {
        String[] parameters = {"", "n/Test", "e/11112021"};
        assertEquals(GoalType.DEFAULT, Parser.getType(parameters));
    }

    @Test
    void getType_flagWithoutLabel_expectException() {
        String[] parameters = {"t/", "n/Test", "e/11112021"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getType(parameters));
    }

    @Test
    void getType_flagWithAUndefinedLabel_expectException() {
        String[] parameters = {"t/??", "n/Test", "e/11112021"};
        assertThrows(HaBitParserException.class,
            () -> Parser.getType(parameters));
    }

    @Test
    void getType_flagWithCorrectLabel_expectGoalTypeOfLabel() throws HaBitParserException {
        String[] parameters = {"t/sl", "n/Test", "e/11112021"};
        assertEquals(GoalType.SLEEP, Parser.getType(parameters));
    }
}