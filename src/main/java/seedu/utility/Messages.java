package seedu.utility;

/**
 * Contains all the warning messages to be sent to the user when an invalid input is given.
 */
public class Messages {
    public static final String INVALID_COMMAND_MESSAGE = 
            "Invalid command. Use \"help\" to show the list of possible commands.";
    public static final String NON_NUMERIC_AMOUNT_MESSAGE = "Only numeric inputs are allowed for amount.";
    public static final String N0N_NUMERIC_INDEX_MESSAGE = "Only numeric inputs are allowed for index.";
    public static final String NON_POSITIVE_AMOUNT_MESSAGE = "Only positive values are allowed for amount.";
    public static final String NON_POSITIVE_INDEX_MESSAGE = "Only positive values are allowed for index.";
    public static final String UNABLE_TO_DELETE_MESSAGE = "Entry not deleted because entry not found!";
    public static final String BLANK_DESCRIPTION_MESSAGE = "Your description is empty!";
    public static final String HAS_CORRUPTED_DATA_ENTRIES = "StonksXD_Data.csv has corrupted entries, " +
            "some or all data maybe lost.";
    public static final String UNABLE_TO_FIND_DATA_FILE = "Unable to find StonksXD_Data.csv, a new one has been made.";
    public static final String ERROR_SAVING_ENTRIES = "Unable to save entries into StonksXD_Data.csv, " +
            "some or all data maybe lost.";
}
