package seedu.duke.parser;

import seedu.duke.commands.HelpCommand;

public class HelpCommandParser {
    public HelpCommand parse() {
        return new HelpCommand();
    }
}
