package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.HelpCommand;

/**
 * Contains all message constants that Parser classes uses.
 */
public class ParserMessages {
    protected static final String EMPTY = "";
    protected static final String LS = System.lineSeparator();
    protected static final String QUOTATION = "\"";
    protected static final String MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST = "Fitbot is unable to understand this command! "
            + LS + "Lost? Try typing " + HelpCommand.MESSAGE_COMMAND_FORMAT + " to see the list of commands!";
    protected static final String MESSAGE_ERROR_NO_DESCRIPTION = "Please input a description for this item!";
    protected static final String MESSAGE_ERROR_NAME_EMPTY_STRING = "Please do not use an empty string as your name!";
    protected static final String MESSAGE_ERROR_NO_CALORIES_INFO = "Please input the number of calories using "
            + "the prefix "
            + QUOTATION + Command.COMMAND_PREFIX_CALORIES + Command.COMMAND_PREFIX_DELIMITER + QUOTATION + "!";
    protected static final String MESSAGE_ERROR_NO_ITEM_NUM = "Please input the item number!";
    protected static final String MESSAGE_ERROR_INVALID_ITEM_NUM = "Please input the item number as a whole number "
            + "greater than 0!";
    protected static final String MESSAGE_ERROR_TOO_MANY_DELIMITERS = "Please do not use the character "
            + QUOTATION + Command.COMMAND_PREFIX_DELIMITER + QUOTATION
            + " other than to specify parameters relevant to this command!";
    protected static final String FILE_TEXT_DELIMITER = "|";
    protected static final String MESSAGE_ERROR_ILLEGAL_CHARACTER = "Please do not use the character "
            + QUOTATION + FILE_TEXT_DELIMITER + QUOTATION
            + " in your input!";
    protected static final String DATE_FORMAT = "dd-MM-yyyy";
    protected static final String TIME_FORMAT = "HHmm";
    protected static final String MESSAGE_ERROR_INVALID_DATE_FORMAT = "Invalid date! Please input a valid date as "
            + "DD-MM-YYYY";
    protected static final String MESSAGE_ERROR_INVALID_TIME_FORMAT = "Invalid time! Please input a valid time as "
            + "HHMM";
    protected static final String MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK = "Invalid day format! Please input day(s) "
            + "between 1 and 7 with a comma in between the days." + LS
            + LS + "1 : Monday"
            + LS + "2 : Tuesday"
            + LS + "3 : Wednesday"
            + LS + "4 : Thursday"
            + LS + "5 : Friday"
            + LS + "6 : Saturday"
            + LS + "7 : Sunday"
            + LS + LS + "E.G: @/1,3,5,6";
    protected static final String MESSAGE_ERROR_NO_DATE = "Please input the date for this item using the prefix "
            + QUOTATION + Command.COMMAND_PREFIX_DATE + Command.COMMAND_PREFIX_DELIMITER + QUOTATION + "!";
    protected static final String MESSAGE_ERROR_NO_START_DATE = "Please input the start date for this item "
            + "using the prefix " + QUOTATION + Command.COMMAND_PREFIX_START_DATE + Command.COMMAND_PREFIX_DELIMITER
            + QUOTATION + "!";
    protected static final String MESSAGE_ERROR_NO_END_DATE = "Please input the end date for this item "
            + "using the prefix " + QUOTATION + Command.COMMAND_PREFIX_END_DATE + Command.COMMAND_PREFIX_DELIMITER
            + QUOTATION + "!";
    protected static final String MESSAGE_ERROR_NO_TIME = "Please input the time for this item using the prefix "
            + QUOTATION + Command.COMMAND_PREFIX_TIME + Command.COMMAND_PREFIX_DELIMITER + QUOTATION + "!";
    protected static final String MESSAGE_ERROR_NO_DAY_OF_THE_WEEK = "Please input the day(s) of reoccurrence "
            + "for this item using the prefix "
            + QUOTATION + Command.COMMAND_PREFIX_DAY_OF_THE_WEEK + Command.COMMAND_PREFIX_DELIMITER + QUOTATION + "!";
    protected static final String MESSAGE_ERROR_REPEATED_DAY_OF_THE_WEEK = "Please check your input of the day(s) "
            + "of reoccurrence and make sure that there is no repeated day!";
    protected static final int MONDAY = 1;
    protected static final int SUNDAY = 7;
    protected static final String MESSAGE_ERROR_ITEM_DATE_TOO_OLD = "Please input a date that is within %s to %s!";
    protected static final String MESSAGE_ERROR_EXTRA_PARAMETERS =
            "Error! There were unnecessary parameters detected. "
                    + "Please follow the command format and try again!";
    public static final String MESSAGE_ERROR_DUPLICATE_NUMBERS =
            "Duplicate numbers found! Please input a list of unique numbers.";

}
