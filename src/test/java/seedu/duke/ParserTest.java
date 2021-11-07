package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.UnknownCommand;
import seedu.duke.exceptions.LotsException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    /**
     * Passing an empty command into the parser. Expects UnknownCommand object.
     */
    @Test
    public void getCommand_emptyCommand_nullReturned() throws Exception {
        Command output = Parser.getCommand("");
        assertTrue(output instanceof UnknownCommand);
    }

    /**
     * Passing a command with spaces into the parser. Expects UnknownCommand object.
     */
    @Test
    public void getCommand_spaces_nullReturned() throws Exception {
        Command output = Parser.getCommand("     ");
        assertTrue(output instanceof UnknownCommand);
    }

    /**
     * Test adding more than 99 unique names into the list.
     * Expects an exception in at the last input.
     */
    @Test
    public void addCommand_AddMoreThan99_ErrorThrown() throws Exception {
        String com;
        PeopleManager manager = new PeopleManager();
        PeopleManager.clearListOfPeople();
        for (int i = 1; i <= 99; i++) {
            com = "add /n " + i + " /i 1 /q 2";
            Command output = Parser.getCommand(com);
            output.setData(manager);
            output.execute();
        }
        com = "add /n 100 /i 1 /q 2";
        Command output = Parser.getCommand(com);
        output.setData(manager);
        assertThrows(LotsException.class,() -> output.execute());
    }

    /**
     * Testing to add more than 999 quantity to 1 order.
     * Expects an exception.
     */
    @Test
    public void addCommand_AddMoreThan999Quantity_ThrowError() throws Exception {
        String com = "add /n a /i 1 /q 999";
        PeopleManager manager = new PeopleManager();
        PeopleManager.clearListOfPeople();
        Command output = Parser.getCommand(com);
        output.setData(manager);
        output.execute();
        com = "add /n a /i 1 /q 1";
        Command last = Parser.getCommand(com);
        last.setData(manager);
        assertThrows(LotsException.class, () -> last.execute());
    }
}
