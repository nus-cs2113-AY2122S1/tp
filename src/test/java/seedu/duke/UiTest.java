package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.exceptions.LotsException;

public class UiTest {

    /**
     * To testing List command with an extra input behind.
     * Expects an exception.
     */
    @Test
    void execute_inputContainsExtraInputs_throwsLotsException() {
        try {
            Command command;
            command = Parser.getCommand("list a");
            command.execute();
        } catch (LotsException e) {
            System.out.println("Please make sure there is no inputs after the list command!");
        }
    }

}
