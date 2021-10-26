package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.module.ModuleInfoCommand;

//@@author Roycius
public class ModuleInfoCommandParser {
    public static Command parse(String userResponse) {
        return new ModuleInfoCommand(userResponse.toUpperCase());
    }
}
