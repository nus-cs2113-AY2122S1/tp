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
            + "  (+) Add a new audio item: add a t/TITLE i/ID a/ARTIST d/DURATION" + System.lineSeparator()
            + "  (+) Add a new book item: add b t/TITLE i/ID a/AUTHOR" + System.lineSeparator()
            + "  (+) Add a new magazine item: add m t/TITLE i/ID p/PUBLISHER e/EDITION" + System.lineSeparator()
            + "  (+) Add a new video item: add v t/TITLE i/ID p/PUBLISHER e/DURATION" + System.lineSeparator()
            + "  (+) Add a new miscellaneous item: add i t/TITLE i/ID" + System.lineSeparator()
            + "  (+) List out all items: list" + System.lineSeparator()
            + "  (+) List out loaned items due today: deadline today" + System.lineSeparator()
            + "  (+) List out loaned items that are overdue: deadline overdue" + System.lineSeparator()
            + "  (+) Search items by ID, title, status or category: search [t|i|s|c]/ATTRIBUTE" + System.lineSeparator()
            + "  (+) Reserve an item: res i/ID u/USERNAME" + System.lineSeparator()
            + "  (+) Unreserve an item: unres ID" + System.lineSeparator()
            + "  (+) Loan out an item: loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)" + System.lineSeparator()
            + "  (+) Return a loaned item: return ID" + System.lineSeparator()
            + "  (+) Edit details of an existing audio item: edit ID [t|i|a|d]/ATTRIBUTE" + System.lineSeparator()
            + "  (+) Edit details of an existing book item: edit ID [t|i|a]/ATTRIBUTE" + System.lineSeparator()
            + "  (+) Edit details of an existing magazine item: edit ID [t|i|p|e]/ATTRIBUTE" + System.lineSeparator()
            + "  (+) Edit details of an existing video item: edit ID [t|i|p|d]/ATTRIBUTE" + System.lineSeparator()
            + "  (+) Edit details of an existing miscellaneous item: edit ID [t|i]/ATTRIBUTE" + System.lineSeparator()
            + "  (+) Remove an existing item: rm ID" + System.lineSeparator()
            + "  (+) Display all library statistics: stats all" + System.lineSeparator()
            + "  (+) Display library statistics by item category: stats category" + System.lineSeparator()
            + "  (+) Display library statistics by item status: stats status" + System.lineSeparator()
            + "  (+) Get a list of valid user commands: help" + System.lineSeparator()
            + "  (+) Exit the program: exit";

    // Add Commands
    public static final String ADD_DUPLICATE_ID = "  (!) ID cannot be a duplicate of an existing ID!";

    // Unknown
    public static final String UNKNOWN_MESSAGE = "  (!) Invalid Command" + System.lineSeparator()
            + "  (!) Type 'help' to view available commands";

    // Info
    public static final String INFO_INVALID_FORMAT = "  (!) Invalid Info command" + System.lineSeparator()
            + "  (!) Format:" + System.lineSeparator()
            + "  1. info all" + System.lineSeparator()
            + "  2. info category" + System.lineSeparator()
            + "  3. info status";

    // List
    public static final String LIST_ALL_MESSAGE = "  (+) Listing out all items in library";
    public static final String LIST_FORMAT_INCORRECT = " (!) Invalid listing command" + System.lineSeparator()
            + " (!) Format: 'list'";
    // List deadline
    public static final String LIST_DEADLINE_TODAY = "  (+) Listing out loaned items that have to be returned today";
    public static final String LIST_DEADLINE_OVERDUE = "  (+) Listing out loaned items that are overdue";
    public static final String LIST_DEADLINE_DATE = "  (+) Listing out loaned items that are due this date: ";
    public static final String EMPTY_DEADLINE_COMMAND = " (!) Oops! Please specify the due date"
            + " (today/overdue/specific date)!";
    public static final String INVALID_DEADLINE_COMMAND = " (!) Oops! Please input the command in one of these format"
            + System.lineSeparator()
            + " 1. deadline today" + System.lineSeparator()
            + " 2. deadline overdue" + System.lineSeparator()
            + " 3. deadline d/dd-mm-yyyy";
    public static final String EMPTY_DATE = " (!) Oops! Please specify the due date!";
    // Search
    public static final String SEARCH_MESSAGE = "  (+) Here are the searching results in library";
    public static final String SEARCH_FORMAT_INCORRECT = " (!) Invalid searching format!" + System.lineSeparator()
            + " (!) Format: 'search i/ID t/TITLE s/STATUS(LOANED/AVAILABLE/RESERVED) "
            + "c/CATEGORY(Magazine/Book/Audio/Video)' or its subset";
    public static final String NO_SEARCH_RESULT = " (!) No search result! "
            + "Please check again whether your search format is correct." + System.lineSeparator()
            + " (!) Format: 'search i/ID t/TITLE s/STATUS c/CATEGORY' or its subset";
    // Loan and Return
    public static final String UNAVAILABLE_ITEM_MESSAGE = "  (!) Sorry, the item is unavailable.";
    public static final String RETURN_SUCCESS = "  (+) Item has been returned:";
    public static final String WRONG_ITEM_MESSAGE = "  (!) Item is not on loan!";
    // Remove
    public static final String RM_SUCCESS = "  (+) Removed the following item:";
    // Edit
    public static final String EDIT_AUDIO_MESSAGE = "  (+) Edited audio item details:";
    public static final String EDIT_BOOK_MESSAGE = "  (+) Edited book item details:";
    public static final String EDIT_VIDEO_MESSAGE = "  (+) Edited video item details:";
    public static final String EDIT_MAGAZINE_MESSAGE = "  (+) Edited magazine item details:";
    public static final String EDIT_MISCELLANEOUS_MESSAGE = "  (+) Edited item details:";
    public static final String EDIT_UNCHANGED_TITLE = "  (!) New title is the same as the existing title";
    public static final String EDIT_UNCHANGED_ID = "  (!) New ID is the same as the existing ID";
    public static final String EDIT_UNCHANGED_AUTHOR = "  (!) New author is the same as the existing author";
    public static final String EDIT_UNCHANGED_PUBLISHER = "  (!) New publisher is the same as the existing publisher";
    public static final String EDIT_UNCHANGED_DURATION = "  (!) New duration is the same as the existing duration";
    public static final String EDIT_UNCHANGED_ARTIST = "  (!) New artist is the same as the existing artist";
    public static final String EDIT_UNCHANGED_EDITION = "  (!) New edition is the same as the existing edition";

    // Keys
    public static final String KEY_TITLE = "t";
    public static final String KEY_ID = "i";
    public static final String KEY_ARTIST = "a";
    public static final String KEY_DURATION = "d";
    public static final String KEY_PUBLISHER = "p";
    public static final String KEY_AUTHOR = "a";
    public static final String KEY_EDITION = "e";
    public static final String KEY_STATUS = "s";
    public static final String KEY_CATEGORY = "c";
    public static final String KEY_USER = "u";
    public static final String KEY_DUE = "d";


    // Invalid Formats
    public static final String RM_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: rm ID";
    public static final String RESERVE_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: reserve i/ID u/USER";
    public static final String LOAN_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)";
    public static final String EDIT_INVALID_ITEM = "  (!) Invalid ID entered. No such item in library";
    public static final String EDIT_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: edit ID KEY/ATTRIBUTE";
    public static final String EDIT_MISCELLANEOUS_INVALID_FORMAT = "  (!) Invalid/missing values"
            + System.lineSeparator() + "  (!) Format: edit ID [t|i]/ATTRIBUTE";
    public static final String EDIT_AUDIO_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: edit ID [t|i|a|d]/ATTRIBUTE";
    public static final String EDIT_BOOK_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: edit ID [t|i|a]/ATTRIBUTE";
    public static final String EDIT_MAGAZINE_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: edit ID [t|i|p|e]/ATTRIBUTE";
    public static final String EDIT_VIDEO_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: edit ID [t|i|p|d]/ATTRIBUTE";

    // Generic Errors
    public static final String WARN_INVALID_ARGS = "  (*) Invalid arguments detected, ignoring them";
    public static final String INVALID_ID = "  (!) Invalid Item ID!";
    public static final String INVALID_DATE = "  (!) Invalid date format! It should be in dd-mm-yyyy";
}
