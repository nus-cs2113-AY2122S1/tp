package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.HelpCommand;

//@@author Roycius
public class HelpCommandParser {
    public static Command parse() {
        return new HelpCommand();
    }
}
