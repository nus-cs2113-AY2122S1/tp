package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.commands.SearchmapCommand;
import seedu.duke.commands.AddmodCommand;
import seedu.duke.commands.AdduniCommand;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SepPlannerParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Command parseCommand(String userInput) throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException("matcher class exception", 1);
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);
        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);
        case SearchmapCommand.COMMAND_WORD:
            return new SearchmapCommandParser().parse(arguments);
        case AddmodCommand.COMMAND_WORD:
            return new AddmodCommandParser().parse(arguments);
        case AdduniCommand.COMMAND_WORD:
            return new AdduniCommandParser().parse(arguments);
        default:
            throw new ParseException("Command not found", 1);
        }
    }
}
