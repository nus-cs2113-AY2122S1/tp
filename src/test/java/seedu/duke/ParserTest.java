package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;

public class ParserTest {
    @Test
    public void parseDeleteCommand_invalidNumberFormatInput_expectException() {
        String inputString = "delete abc";

        assertThrows(DukeException.class,
                ()-> Parser.parse(inputString));
    }

}
