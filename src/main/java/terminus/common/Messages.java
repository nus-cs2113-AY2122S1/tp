package terminus.common;

public class Messages {

    /*
     * Help Command Messages
     */
    public static final String HELP_MENU_MESSAGE = "\nHelp Menu\n---------\n";
    public static final String MESSAGE_COMMAND_ADD = "Add an item into your list.";
    public static final String MESSAGE_COMMAND_DELETE = "Delete an item from your list.";
    public static final String MESSAGE_COMMAND_VIEW = "View all items or view an individual items";
    public static final String MESSAGE_COMMAND_BACK = "Returns to the parent workspace.";
    public static final String MESSAGE_COMMAND_EXIT = "Exits the program.";
    public static final String MESSAGE_COMMAND_HELP = "Prints the help page.";
    public static final String MESSAGE_COMMAND_NOTE = "Move to notes workspace.";
    public static final String MESSAGE_COMMAND_SCHEDULE = "Move to schedules workspace.";
    public static final String MESSAGE_COMMAND_QUESTION = "Move to questions workspace.";
    public static final String MESSAGE_COMMAND_TEST_QUESTION = "Test yourself with Active Recall.";
    public static final String MESSAGE_COMMAND_TIMETABLE = "Displays all your schedule.";
    public static final String MESSAGE_COMMAND_MODULE = "Move to the module workspace";
    public static final String MESSAGE_COMMAND_ADD_MODULE = "Adds a module";
    public static final String MESSAGE_COMMAND_MODULE_DELETE = "Deletes a module";
    public static final String MESSAGE_COMMAND_MODULE_VIEW = "View all modules available";
    public static final String MESSAGE_COMMAND_EXPORT = "Export all existing notes into a pdf file.";
    public static final String MESSAGE_COMMAND_UPDATE_MODULE = "Updates the module name";
    public static final String MESSAGE_COMMAND_RELOAD = "Reload the contents of notes for this module.";

    public static final String CONTENT_MESSAGE_HEADER = "List of Content\n---------------\n";
    public static final String CONTENT_MESSAGE_FOOTER =
        "\nRerun the same command with an index behind to view the content.";
    public static final String MESSAGE_RESPONSE_DELETE = "Your %s on '%s' has been deleted!";
    public static final String MESSAGE_RESPONSE_ADD = "Your %s on '%s' has been added!";
    public static final String MESSAGE_RESPONSE_MODULE_ADD = "Module %s has been added";
    public static final String MESSAGE_RESPONSE_MODULE_FORMAT = "%d. %s";
    public static final String MESSAGE_RESPONSE_NO_MODULES = "You do not have any modules.";
    public static final String MESSAGE_RESPONSE_MODULE_DELETE = "Deleted module %s.";
    public static final String MESSAGE_RESPONSE_RELOAD_NOTE = "Your Notes for %s is being reloaded.";

    /*
     * Error Messages
     */
    public static final String ERROR_MESSAGE_TAG = "Error: ";
    public static final String INVALID_ARGUMENT_FORMAT_MESSAGE_EXCEPTION = "%s %s";
    public static final String INVALID_ARGUMENT_FORMAT_MESSAGE = "Format: %s";
    public static final String ERROR_MESSAGE_INVALID_INPUT = ERROR_MESSAGE_TAG + "Invalid input provided.";
    public static final String ERROR_MESSAGE_MISSING_ARGUMENTS = ERROR_MESSAGE_TAG + "Missing arguments.";
    public static final String ERROR_MESSAGE_EMPTY_CONTENTS = ERROR_MESSAGE_TAG + "Content not found.";
    public static final String ERROR_MESSAGE_INVALID_NUMBER = ERROR_MESSAGE_TAG + "Invalid numerical value provided.";
    public static final String ERROR_MESSAGE_INVALID_TIME_FORMAT = ERROR_MESSAGE_TAG + "Invalid time format %s.";
    public static final String ERROR_MESSAGE_INVALID_LINK = ERROR_MESSAGE_TAG + "Invalid link %s.";
    public static final String ERROR_MESSAGE_INVALID_DAY = ERROR_MESSAGE_TAG + "Invalid day %s.";
    public static final String ERROR_MESSAGE_INVALID_DURATION = ERROR_MESSAGE_TAG + "Invalid duration %d.";
    public static final String ERROR_MESSAGE_SCHEDULE_OVERFLOW = ERROR_MESSAGE_TAG
            + "Please set schedules on separate days.";
    public static final String ERROR_MESSAGE_DUPLICATE_NAME = ERROR_MESSAGE_TAG + "Duplicate name found.";
    public static final String ERROR_MESSAGE_NO_QUESTIONS =
        "There are no questions to be tested on. Type 'questions add' to get started";
    public static final String ERROR_MESSAGE_INVALID_DURATION_FORMAT = "Invalid duration format.";


    public static final String ERROR_FILES_NOT_DELETED = "Unable to delete some file.";
    public static final String ERROR_MESSAGE_FILE = "Unable to save/load file: %s";
    public static final String ERROR_MESSAGE_FOLDER = "Unable to save/load folder: %s";
    public static final String EMPTY_CONTENT_LIST_MESSAGE = "You do not have any content in this workspace.\n";
    public static final String ERROR_MESSAGE_MODULE_WHITESPACE = "Module name cannot contain any whitespaces!";
    public static final String ERROR_MESSAGE_MODULE_EXIST = "Module already exist!";
    public static final String ERROR_INVALID_FILE_NAME = "Name provided is invalid.";
    public static final String ERROR_CHANGE_FILE_NAME = "Unable to change file name";

    public static final String ERROR_STORAGE_INVALID_ACTION = "Invalid storage operation detected : %s";
    public static final String ERROR_STORAGE_INVALID_TYPE = "Invalid storage type detected : %s";
    public static final String ERROR_STORAGE_CREATE_FOLDER = "Unable to create folder : %s";
    public static final String ERROR_STORAGE_CREATE_FILE = "Unable to create file : %s";
    public static final String ERROR_STORAGE_READ_FILE = "Unable to read file : %s";
    public static final String ERROR_STORAGE_FILE_TOO_LARGE = "File too large detected : %s";
    public static final String ERROR_STORAGE_FILE_EXIST = "Unable to create file/folder, name already exists : %s";
    public static final String ERROR_STORAGE_FILE_NOT_DELETED = "Unable to delete the file/folder : %s.";
    public static final String ERROR_STORAGE_CLEAN_FILE = "Unable to remove some files in folder : %s";
    public static final String ERROR_STORAGE_WRITE_FILE = "Unable to write contents into file : %s";
    public static final String ERROR_STORAGE_WRITE_DATA_NULL = "Data to be written is empty into file : %s";
    public static final String ERROR_MISSING_MODULE_MANAGER = "Error: Module Manager cannot be null.";
    public static final String ERROR_FILE_FOLDER_MISMATCH = "Error file was detected as folder : %s";
    public static final String ERROR_MISSING_FOLDER = "Unable to find folder : %s";
    public static final String ERROR_GET_FILES = "Unable to get files from folder : %s";
    public static final String ERROR_GET_FILE_CONTENT = "Unable to get file content type : %s";
    public static final String ERROR_STORAGE_DISABLE_RESPONSE = "We have detect an issue with storage, we will be "
            + "disabling the storage temporary until the TermiNUS exit where we will attempt to save your data 1 last"
            + " time.";
    public static final String ERROR_FILE_BUFFERED_READER = "Unable to collect file contents : %s";


    /*
     * Banners
     */
    public static final String MAIN_BANNER = "Welcome to TermiNUS!\n";
    public static final String MAIN_REMINDER = "This is your schedule today:\n";
    public static final String NOTE_BANNER = "You have %d note(s) inside this workspace.";
    public static final String SCHEDULE_BANNER = "You have %d link(s) in this workspace.";
    public static final String QUESTION_BANNER = "You have %d question(s) in this workspace.";

    /*
     * Schedules and Timetables
     */
    public static final String MESSAGE_EMPTY_DAILY_SCHEDULE = "You have no schedule for today.";
    public static final String MESSAGE_CONFLICTING_SCHEDULE = "Your new schedule has conflicts with:";
    public static final String EMPTY_SCHEDULE_FOR_THE_DAY = "You have no schedule for %s.\n";
    public static final String EMPTY_SCHEDULE_FOR_THE_WEEK = "You have no schedule for the week.\n";

    /*
     * Active Recall
     */
    public static final String ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE =
        "When you are ready, press [Enter] to continue.";
    public static final String[] ACTIVE_RECALL_SESSION_END_MESSAGE = {"This training session has ended.",
        "Returning you back to main program."};
    public static final String[] ACTIVE_RECALL_ASK_QUESTION_DIFFICULTY_MESSAGE = {"",
        "How did you find the question? (Compare against past attempts if any)",
        "[1] Easy; [2] Normal / Same; [3] Hard; [E] Exit"};

    /*
     * Notes Export
     */
    public static final String SUCCESSFUL_EXPORT = "Exported notes! Check the data folder.";
    public static final String FAIL_TO_EXPORT = "Unable to export file to pdf.";
    public static final String UPDATE_MODULE_RESPONSE_MESSAGE = "Updated %s to %s successfully.";
    public static final String MESSAGE_COMMAND_GO = "Go to a specific module's workspace";
}
