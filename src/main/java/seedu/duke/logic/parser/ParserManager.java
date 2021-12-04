package seedu.duke.logic.parser;

import seedu.duke.logic.commands.ByeCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.commands.OverviewCommand;
import seedu.duke.logic.parser.exceptions.ExtraParamException;

import java.util.logging.Logger;

/**
 * Parses user input for the command word
 * and calls the appropriate Parser class to determine which command to execute.
 */
public class ParserManager {

    protected static final Logger logger = Logger.getLogger(ParserManager.class.getName());

    /**
     * Returns the correct command to be executed depending on user input.
     * Command words are case-insensitive.
     *
     * @param input Raw user input string
     * @return Command class representing the correct command to be executed
     */
    public Command parseCommand(String input) {

        if (hasTextFileDelimiter(input)) {
            return new InvalidCommand(ParserMessages.MESSAGE_ERROR_ILLEGAL_CHARACTER);
        }

        final String[] commandAndParams = splitInputIntoCommandAndParams(input);
        final String commandWord = commandAndParams[0].toLowerCase(); //case-insensitive (all lower case)
        final String params = " " + commandAndParams[1];
        try {
            switch (commandWord) {
            case Command.COMMAND_WORD_ADD:
                return new AddCommandParser().parse(params);
            case Command.COMMAND_WORD_DELETE:
                return new DeleteCommandParser().parse(params);
            case Command.COMMAND_WORD_VIEW:
                return new ViewCommandParser().parse(params);
            case Command.COMMAND_WORD_EDIT:
                return new EditCommandParser().parse(params);
            case Command.COMMAND_WORD_BMI:
                return new BmiParser().parse(params);
            case Command.COMMAND_WORD_PROFILE:
                return new UpdateProfileParser().parse(params);
            case OverviewCommand.COMMAND_WORD:
                if (!params.trim().isEmpty()) {
                    throw new ExtraParamException();
                }
                return new OverviewCommand();
            case ByeCommand.COMMAND_WORD:
                if (!params.trim().isEmpty()) {
                    throw new ExtraParamException();
                }
                return new ByeCommand();
            case HelpCommand.COMMAND_WORD:
                if (!params.trim().isEmpty()) {
                    throw new ExtraParamException();
                }
                return new HelpCommand();
            default:
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST);
            }
        } catch (ExtraParamException e) {
            return new InvalidCommand(e.getMessage());
        }

    }

    /**
     * Returns a String array where 0th index is command string and 1st index is the remaining parameters.
     * Command string and parameter string is assumed to be separated by the first " " in input.
     * If no parameters are provided in the input, 1st index will be set to EMPTY.
     *
     * @param input Raw user input string
     * @return String array [command, parameters]
     */
    protected static String[] splitInputIntoCommandAndParams(String input) {
        String[] commandAndParams = new String[2];
        final String[] inputSplit = input.trim().split(" ", 2);
        //command string
        commandAndParams[0] = inputSplit[0];
        //param string; if not given, set to EMPTY for error handling
        commandAndParams[1] = (inputSplit.length == 2) ? inputSplit[1].trim() : ParserMessages.EMPTY;
        return commandAndParams;
    }

    protected static boolean hasTextFileDelimiter(String input) {
        return input.contains(ParserMessages.FILE_TEXT_DELIMITER);
    }

}
