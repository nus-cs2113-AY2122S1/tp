package seplanner.parser;

import seplanner.commands.ExitCommand;

// @@author leowyy99

/**
 * Call ExitCommand object.
 */
public class ExitCommandParser {
    ExitCommand parse() {
        return new ExitCommand();
    }
}