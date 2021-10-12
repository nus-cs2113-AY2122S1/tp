package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    public void parse_exit_ExitCommandObject() {
        boolean type = Parser.parse("exit") instanceof ExitCommand;
        assertTrue(type);
    }

    @Test
    public void parse_unknown_UnknownCommandObject() {
        boolean type = Parser.parse("foo 2") instanceof UnknownCommand;
        assertTrue(type);
    }
}