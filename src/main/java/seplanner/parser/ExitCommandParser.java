package seplanner.parser;

import seplanner.commands.ExitCommand;

public class ExitCommandParser {
    ExitCommand parse() {
        return new ExitCommand();
    }
}