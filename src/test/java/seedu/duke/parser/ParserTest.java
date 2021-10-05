package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_userCommand_checkIfSplitCorrectly() {
        assertEquals("add cs2113 tp project --start 22-09-2021",
                Parser.parseCommand("add cs2113 tp project --start 22-09-2021"));
    }
}