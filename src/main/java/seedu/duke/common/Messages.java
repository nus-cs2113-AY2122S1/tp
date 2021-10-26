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
    // List
    public static final String LIST_ALL_MESSAGE = "  (+) Listing out all items in library";
    public static final String LIST_AVAILABLE_MESSAGE = "  (+) Listing out available items in library";
    public static final String LIST_LOANED_MESSAGE = "  (+) Listing out loaned items in library";
    // Loan and Return
    public static final String LOAN_SUCCESS = "  (+) Item has been loaned out:";
    public static final String LOAN_SUCCESS_RESERVED = "  (+) The reserved item has been loaned out:";
    public static final String RESERVE_SUCCESS = "  (+) You have successfully reserved an item:";
    public static final String UNAVAILABLE_ITEM_MESSAGE = "  (!) Sorry, the item is unavailable.";
    public static final String RETURN_SUCCESS = "  (+) Item has been returned:";
    public static final String WRONG_ITEM_MESSAGE = "  (!) Item is not on loan!";
    // Remove
    public static final String RM_INVALID_FORMAT = "  (!) Invalid/missing values" + System.lineSeparator()
            + "  (!) Format: rm <id>";
    public static final String RM_SUCCESS = "  (+) Removed the following item:";
    // Add
    public static final String ADD_MESSAGE = "  (+) Added new item to catalogue:";
    public static final String FORMAT_INCORRECT = "  (!) Invalid/missing values!" + System.lineSeparator()
            + "  (!) Format: add t/TITLE i/ID";
    public static final String INVALID_TITLE = "  (!) Please enter a valid title";
    public static final String ADD_DUPLICATE_ID = "  (!) ID cannot be a duplicate!";
    // Edit
    public static final String EDIT_MESSAGE = " (+) Edited item details:";
    // Generic Errors
    public static final String INVALID_ID = "  (!) Invalid Item ID!";
}
