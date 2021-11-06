package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.HELP_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;

//@@author Roycius
public class HelpCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        if (!userResponse.isBlank()) {
            throw new ParseException(promptFormat(HELP_FORMAT));
        }
        return new HelpCommand();
    }
}
