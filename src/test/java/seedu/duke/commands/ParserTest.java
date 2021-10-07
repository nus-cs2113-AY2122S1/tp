package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    public void parse_exit_ExitCommandObject() {
        Boolean type = Parser.parse("exit") instanceof ExitCommand;
        assertTrue(type);
    }

    @Test
    public void parse_null_null() {
        assertNull(Parser.parse("foo 2"));
    }
}