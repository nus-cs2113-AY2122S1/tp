package seedu.duke.parser;

import seedu.duke.commands.RemoveCommand;

import java.text.ParseException;

public class RemoveCommandParser {

    public RemoveCommand parse(String arguments) throws ParseException {

        String type;
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);

        if (argumentsSubstrings.length < 2) {
            throw new ParseException("No flags or description found.", 1);
        }
        String flag = argumentsSubstrings[0];
        String description = argumentsSubstrings[1].trim();
        System.out.println(flag);
        System.out.println(description);

        switch (flag) {
        case "/u":
            type = "u";
            break;
        case "/m":
            type = "m";
            break;
        default:
            throw new ParseException("Wrong flags passed", 1);
        }

        return new RemoveCommand(type, description);
    }
}
