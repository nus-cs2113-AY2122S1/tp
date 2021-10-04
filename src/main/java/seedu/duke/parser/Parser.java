package seedu.duke.parser;

import seedu.duke.commands.Command;

import java.text.ParseException;

public interface Parser<T extends Command> {
    T parse(String userInput) throws ParseException;
}
