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

}
