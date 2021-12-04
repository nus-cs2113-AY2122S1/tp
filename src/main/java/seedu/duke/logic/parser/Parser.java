package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;

/**
 * Represents a Parser that parses input arguments into their respective Command classes.
 */
public interface Parser {
    Command parse(String params);
}
