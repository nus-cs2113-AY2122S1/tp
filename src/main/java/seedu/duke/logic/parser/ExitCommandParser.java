package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.ExitCommand;

public class ExitCommandParser {
    public static Command parse() {
        return new ExitCommand();
    }
}
