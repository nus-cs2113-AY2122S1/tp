package seedu.situs;

import org.junit.jupiter.api.Test;
import seedu.situs.exceptions.SitusException;
import seedu.situs.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseDeleteCommand_invalidParameterFormatInput_expectException() {
        try {
            String inputString = "delete abc";
            String resultMsg = Parser.parse(inputString);
            fail();
        } catch (SitusException e) {
            assertEquals("The parameter format is wrong!", e.getMessage());
        }
    }

    @Test
    public void parseDeleteCommand_taskNumberInvalidInput_expectException() {
        try {
            String inputString = "delete 1.100";
            String resultMsg = Parser.parse(inputString);
            fail();
        } catch (SitusException e) {
            assertEquals("Ingredient number does not exist!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandInput_success() throws SitusException {
        String inputString = "foo 2";
        String resultMsg = Parser.parse(inputString);
        assertEquals("Invalid command!", resultMsg);
    }

    @Test
    public void parseAddCommand_insufficientCommandParameters_expectException() {
        try {
            String inputString = "add carrot";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("The number of parameters is wrong!", e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_invalidAmountParameter_expectException() {
        try {
            String inputString = "add n/carrot a/five e/1aug";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("Invalid number format!", e.getMessage());
        }
    }

    @Test
    public void parseUpdateCommand_insufficientCommandParameters_expectException() {
        try {
            String inputString = "update onion";
            String resultMsg = Parser.parse(inputString);
            fail();
        } catch (SitusException e) {
            assertEquals("The number of parameters is wrong!", e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_invalidExpiryDate_expectException() {
        try {
            String inputString = "add n/carrot a/50 e/1 dec 2021";
            String resultMsg = Parser.parse(inputString);
            fail();
        } catch (SitusException e) {
            assertEquals("Invalid expiry date format!"
                    + '\n' + "Please key in the expiry date in the format dd/mm/yyyy!", e.getMessage());
        }
    }

    @Test
    public void parseUpdateCommand_ingredientNotFound_success() throws SitusException {
        try {
            String inputString1 = "add n/ apple a/ 300 e/ 12/10/2999";
            String inputString2 = "update 999 a/ 12.3";
            Parser.parse(inputString1);
            String resultMsg = Parser.parse(inputString2);
            fail();
        } catch (SitusException e) {
            assertEquals("Ingredient number does not exist!", e.getMessage());
        }
    }

    @Test
    public void parseFindCommand_incorrectParameters_expectException() {
        try {
            String inputString = "find";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("The number of parameters is wrong!", e.getMessage());
        }
    }

    //@@author mudkip8
    @Test
    public void parseAlertsCommand_insufficientParameters_expectException() {
        try {
            String inputString = "alerts";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("The number of parameters is wrong!", e.getMessage());
        }
    }

    @Test
    public void parseAlertsCommand_invalidAlertType_expectException() {
        try {
            String inputString = "alerts invalid";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("Not an alert type!", e.getMessage());
        }
    }

    @Test
    public void parseSetCommand_insufficientParameters_expectException() {
        try {
            String inputString = "set expiry";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("The number of parameters is wrong!", e.getMessage());
        }
    }

    @Test
    public void parseSetCommand_negativeExpiryThreshold_expectException() {
        try {
            String inputString = "set expiry -10";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("Thresholds cannot be less than or equal to 0", e.getMessage());
        }
    }

    @Test
    public void parseSetCommand_extraLargeExpiryThreshold_expectException() {
        try {
            String inputString = "set expiry 9999999999999999";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("The max expiry threshold is 1096 days", e.getMessage());
        }
    }

    @Test
    public void parseSetCommand_negativeStockThreshold_expectException() {
        try {
            String inputString = "set stock -10";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("Thresholds cannot be less than or equal to 0", e.getMessage());
        }
    }

    @Test
    public void parseSetCommand_extraLargeStockThreshold_expectException() {
        try {
            String inputString = "set stock 9999999999999999";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("The max stock threshold is 1000kg", e.getMessage());
        }
    }

    @Test
    public void parseExpireCommand_insufficientParameters_expectException() {
        try {
            String inputString = "expire";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("The number of parameters is wrong!", e.getMessage());
        }
    }

    @Test
    public void parseExpireCommand_incorrectExpiryFormat_expectException() {
        try {
            String inputString = "expire 2021-11-11";
            String resultMsg = Parser.parse(inputString);
        } catch (SitusException e) {
            assertEquals("Invalid expiry date format!"
                    + '\n' + "Please key in the expiry date in the format dd/mm/yyyy!", e.getMessage());
        }
    }
}
