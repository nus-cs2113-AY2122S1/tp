package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.ExitCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.EXIT_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;

//@@author richwill28
public class ExitCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        if (!userResponse.isBlank()) {
            throw new ParseException(promptFormat(EXIT_FORMAT));
        }
        return new ExitCommand();
    }
}
