package seedu.duke.common;

/**
 * Class containing various output messages that are printed to the user.
 */
public class Messages {
    public static final String LOGO = "        _.-\"\\\n"
            + "    _.-\"     \\\n"
            + " ,-\"          \\\n"
            + "( \\            \\\n"
            + " \\ \\            \\\n"
            + "  \\ \\            \\\n"
            + "   \\ \\         _.-;\n"
            + "    \\ \\    _.-\"   :\n"
            + "     \\ \\,-\"    _.-\"\n"
            + "      \\(   _.-\"  libmgr\n"
            + "       `--\""
            + "";
    public static final String DIVIDER = "  ========================================";
    public static final String WELCOME_MESSAGE = "Welcome to libmgr";
    public static final String EXIT_MESSAGE = "See you soon!";
    public static final String UNKNOWN_MESSAGE = "Sorry, I don't understand what you mean";
    public static final String INVALID_VALUES = "  (!) Invalid/missing values";

    // Add Commands
    public static final String WARN_ADDITIONAL_ARGS = "  (*) Additional arguments detected, ignoring them";
    public static final String ADD_DUPLICATE_ID = "  (!) ID cannot be a duplicate!";

    // List
    public static final String LIST_ALL_MESSAGE = "  (+) Listing out all items in library";
    public static final String LIST_AVAILABLE_MESSAGE = "  (+) Listing out available items in library";
    public static final String LIST_LOANED_MESSAGE = "  (+) Listing out loaned items in library";
    public static final String LIST_FORMAT_INCORRECT = " (!) Invalid listing command" + System.lineSeparator()
            + " (!) Format: 'list all' OR 'list available' OR 'list loaned'";
    public static final String LIST_DEADLINE_TODAY = "  (+) Listing out loaned items that have to be returned today";
    public static final String LIST_DEADLINE_OVERDUE = "  (+) Listing out loaned items that are overdue";
    // Search
    public static final String SEARCH_MESSAGE = "  (+) Here are the searching results in library";
    public static final String SEARCH_FORMAT_INCORRECT = " (!) Invalid searching format!" + System.lineSeparator()
            + " (!) Format: 'search i/ID' OR 'search t/TITLE' OR 'search s/STATUS'";
    // Loan and Return
    public static final String LOAN_SUCCESS = "  (+) Item has been loaned out:";
    public static final String LOAN_SUCCESS_RESERVED = "  (+) The reserved item has been loaned out:";
    public static final String RESERVE_SUCCESS = "  (+) You have successfully reserved an item:";
    public static final String UNAVAILABLE_ITEM_MESSAGE = "  (!) Sorry, the item is unavailable.";
    public static final String RETURN_SUCCESS = "  (+) Item has been returned:";
    public static final String WRONG_ITEM_MESSAGE = "  (!) Item is not on loan!";
    public static final String ALREADY_RESERVED_MESSAGE = "  (!) Sorry, the item has already been reserved for someone";
    // Remove
    public static final String RM_SUCCESS = "  (+) Removed the following item:";
    // Add
    public static final String INVALID_TITLE = "  (!) Please enter a valid title";

    // Invalid Formats
    public static final String ADD_INVALID_FORMAT = "  (!) Invalid/missing values!" + System.lineSeparator()
            + "  (!) Format: add [a/b/m/v] [ARGUMENTS]";
    public static final String RM_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: rm ID";
    public static final String RESERVE_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: reserve i/ID u/USER";
    public static final String LOAN_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)";
    // Generic Errors
    public static final String INVALID_ID = "  (!) Invalid Item ID!";
}
