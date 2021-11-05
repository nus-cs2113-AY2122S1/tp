package seplanner.parser;

import seplanner.commands.HelpCommand;

// @@author leowyy99

/**
 * Call HelpCommand object.
 */
public class HelpCommandParser {
    public HelpCommand parse() {
        return new HelpCommand();
    }
}
