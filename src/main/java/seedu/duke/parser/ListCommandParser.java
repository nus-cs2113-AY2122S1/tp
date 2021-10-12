package seedu.duke.parser;

import seedu.duke.commands.ListCommand;

import java.io.IOException;
import java.text.ParseException;

public class ListCommandParser {

    public ListCommand parse(String arguments) throws ParseException, IOException {
        String type;

        if (arguments.trim().length() == 0) {
            throw new ParseException("no flags passed", 1);
        }

        switch (arguments.trim()) {
        case "/m":
            type = "m";
            break;
        case "/u":
            type = "u";
            break;
        case "/s":
            type = "s";
            break;
        case "/t":
            type = "t";
            break;
        default:
            throw new ParseException("Incorrect flags passed.", 1);
        }

        return new ListCommand(type);
    }
}
