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
    public static final String WELCOME_MESSAGE = "Welcome to libmgr" + System.lineSeparator()
            + "Type 'help' to view available commands";
    public static final String EXIT_MESSAGE = "See you soon!";
    public static final String INVALID_VALUES = "  (!) Invalid/missing values";
    // Help
    public static final String HELP_MESSAGE
            = "  ** Words in `UPPER_CASE` are parameters to be supplied by you" + System.lineSeparator()
            + "  ** Parameters can be in any order" + System.lineSeparator()
            + "  ** Parameters must be separated with a space" + System.lineSeparator()
            + "  Add a new audio item: add a t/TITLE i/ID a/ARTIST d/DURATION" + System.lineSeparator()
            + "  Add a new book item: add b t/TITLE i/ID a/AUTHOR" + System.lineSeparator()
            + "  Add a new magazine item: add m t/TITLE i/ID p/PUBLISHER e/EDITION" + System.lineSeparator()
            + "  Add a new video item: add v t/TITLE i/ID p/PUBLISHER e/DURATION" + System.lineSeparator()
            + "  List out all items: list" + System.lineSeparator()
            + "  List out loaned items due today: deadline today" + System.lineSeparator()
            + "  List out overdue items: deadline overdue" + System.lineSeparator()
            + "  Search items by ID: search i/ID" + System.lineSeparator()
            + "  Search items by title/part of title: search t/TITLE" + System.lineSeparator()
            + "  Search items by status: search s/STATUS" + System.lineSeparator()
            + "  Reserve an item: res i/ID u/USERNAME" + System.lineSeparator()
            + "  Unreserve an item: unres ID" + System.lineSeparator()
            + "  Loan out an item: loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)" + System.lineSeparator()
            + "  Return a loaned item: return ID" + System.lineSeparator()
            + "  Edit details of an existing item: edit ID MARKER/new attribute" + System.lineSeparator()
            + "  Remove an existing item: rm ID" + System.lineSeparator()
            + "  Exits the program: exit";

    // Add Commands

    public static final String ADD_DUPLICATE_ID = "  (!) ID cannot be a duplicate!";

    // Unknown
    public static final String UNKNOWN_MESSAGE = "Sorry, I don't understand what you mean" + System.lineSeparator()
            + "Type 'help' to view available commands";

    // List
    public static final String LIST_ALL_MESSAGE = "  (+) Listing out all items in library";
    public static final String LIST_AVAILABLE_MESSAGE = "  (+) Listing out available items in library";
    public static final String LIST_LOANED_MESSAGE = "  (+) Listing out loaned items in library";
    public static final String LIST_FORMAT_INCORRECT = " (!) Invalid listing command" + System.lineSeparator()
            + " (!) Format: 'list all' OR 'list available' OR 'list loaned'";
    // List deadline
    public static final String LIST_DEADLINE_TODAY = "  (+) Listing out loaned items that have to be returned today";
    public static final String LIST_DEADLINE_OVERDUE = "  (+) Listing out loaned items that are overdue";
    // Search
    public static final String SEARCH_MESSAGE = "  (+) Here are the searching results in library";
    public static final String SEARCH_FORMAT_INCORRECT = " (!) Invalid searching format!" + System.lineSeparator()
            + " (!) Format: 'search i/ID' OR 'search t/TITLE' OR 'search s/STATUS'";
    // Loan and Return
    public static final String UNAVAILABLE_ITEM_MESSAGE = "  (!) Sorry, the item is unavailable.";
    public static final String RETURN_SUCCESS = "  (+) Item has been returned:";
    public static final String WRONG_ITEM_MESSAGE = "  (!) Item is not on loan!";
    // Remove
    public static final String RM_SUCCESS = "  (+) Removed the following item:";
    // Edit
    public static final String EDIT_MESSAGE = "  (+) Edited item details:";
    public static final String EDIT_INVALID_ITEM = "  (!) Invalid ID entered. No such item in library";
    // Invalid Formats
    public static final String RM_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: rm ID";

    public static final String RESERVE_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: reserve i/ID u/USER";
    public static final String LOAN_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)";
    public static final String EDIT_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: edit ID marker/attribute";
    public static final String EDIT_INVALID_AUDIO = "  (!) Attribute Marker not valid for Audio"
            + System.lineSeparator() + "  (!) Should only be t/, i/, a/ or d/";
    public static final String EDIT_INVALID_BOOK = "  (!) Attribute Marker not valid for Book" + System.lineSeparator()
            + "  (!) Should only be t/, i/ or a/";
    public static final String EDIT_INVALID_MAGAZINE = "  (!) Attribute Marker not valid for Magazine"
            + System.lineSeparator() + "  (!) Should only be t/, i/, p/ or e/";
    public static final String EDIT_INVALID_VIDEO = "  (!) Attribute Marker not valid for Video"
            + System.lineSeparator() + "  (!) Should only be t/, i/, p/ or d/";


    // Generic Errors
    public static final String WARN_ADDITIONAL_ARGS = "  (*) Additional arguments detected, ignoring them";
    public static final String INVALID_ID = "  (!) Invalid Item ID!";
    public static final String INVALID_DATE = "  (!) Invalid date format! It should be in dd-mm-yyyy";
}
