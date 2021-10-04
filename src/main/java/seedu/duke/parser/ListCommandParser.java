package seedu.duke.parser;

import seedu.duke.commands.ListCommand;

import java.text.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String arguments) throws ParseException {
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
