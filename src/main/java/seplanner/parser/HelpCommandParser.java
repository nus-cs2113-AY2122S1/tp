package seplanner.parser;

import seplanner.commands.HelpCommand;

public class HelpCommandParser {
    public HelpCommand parse() {
        return new HelpCommand();
    }
}
