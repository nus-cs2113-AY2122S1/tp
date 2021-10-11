package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseDeleteCommand_invalidNumberFormatInput_expectException() {
        try {
            String inputString = "delete abc";
            String resultMsg = Parser.parse(inputString);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid number format!", e.getMessage());
        }
    }

    @Test
    public void parseDeleteCommand_taskNumberInvalidInput_expectException() {
        try {
            String inputString = "delete -1";
            String resultMsg = Parser.parse(inputString);
            fail();
        } catch (DukeException e) {
            assertEquals("Ingredient number does not exist!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandInput_success() throws DukeException {
        String inputString = "foo 2";
        String resultMsg = Parser.parse(inputString);
        assertEquals("Invalid command!", resultMsg);
    }

}
