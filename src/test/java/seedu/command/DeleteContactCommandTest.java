package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteContactCommandTest {

    @Test
    void execute() {
        Parser parser = new Parser();
        final int testIndex = 1;
        final String input = "rm " + testIndex;
        final Command result = parser.parseCommand(input);
//        final DeleteCommand result = parseAndAssertCommandType(input, DeleteCommand.class);
        int targetIndex = result.contactList.getIndexOfContact(result);

        assertEquals(result.getTargetIndex(), testIndex);
    }
}