package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.FindCommand;
import seedu.duke.exceptions.LotsException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindCommandTest {

    /**
     * Testing an empty command. Expects an exceptions.
     */
    @Test
    public void inputRegex_emptyCommand_lotsExceptionThrown() {
        String input = "";
        assertThrows(LotsException.class, () -> new FindCommand(input));
    }

    /**
     * Testing the Find command without any parameters.
     * Expects an exception.
     */
    @Test
    public void inputRegex_incompleteCommand_lotsExceptionThrown() {
        String input = "find";
        assertThrows(LotsException.class, () -> new FindCommand(input));
    }

    /**
     * Testing the find command without specifying the search input.
     * Expects an exception.
     */
    @Test
    public void inputRegex_emptySearch_lotsExceptionThrown() {
        String input = "find /n";
        assertThrows(LotsException.class, () -> new FindCommand(input));
    }

    /**
     * Testing the find command with the format wrong.
     * Expects an exception.
     */
    @Test
    public void inputRegex_wrongSlashPosition_lotsExceptionThrown() {
        String input = "find n/";
        assertThrows(LotsException.class, () -> new FindCommand(input));
    }
}
