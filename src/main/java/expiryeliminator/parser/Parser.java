package expiryeliminator.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expiryeliminator.commands.ByeCommand;
import expiryeliminator.commands.Command;
import expiryeliminator.commands.IncorrectCommand;

//@@author bernardboey-reused
// Reused from https://github.com/bernardboey/ip/blob/master/src/main/java/duke/parser/Parser.java
// with minor modifications

public class Parser {
    // Capturing group variables store the name of the named capturing group.
    private static final String CAPTURING_GROUP_COMMAND = "command";
    private static final String CAPTURING_GROUP_ARGS = "args";
    /** Used for initial separation of command word and args. */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "^(?<" + CAPTURING_GROUP_COMMAND + ">[^/]+)"
                    + "(?<" + CAPTURING_GROUP_ARGS + "> .*)?$");

    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(";

    /**
     * Parses user input as a command.
     *
     * @param userInput Input command together with any arguments.
     * @return Command that corresponds to the user input, if valid.
     */
    public static Command parseCommand(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new RuntimeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        final String command = matcher.group(CAPTURING_GROUP_COMMAND);
        String args = matcher.group(CAPTURING_GROUP_ARGS);
        args = args == null ? "" : args.trim();

        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            return new IncorrectCommand(MESSAGE_UNRECOGNISED_COMMAND);
        }
    }
}
//@@author
