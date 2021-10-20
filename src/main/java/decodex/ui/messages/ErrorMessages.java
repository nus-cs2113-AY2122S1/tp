package decodex.ui.messages;

public class ErrorMessages {

    public static final String MISSING_ARGUMENT = "Missing argument";
    public static final String MISSING_COMMAND_TYPE = "Your command cannot be blank";
    public static final String MISSING_MODULE_NAME = "Missing module name";
    public static final String INPUT_EMPTY = "Your input is empty";
    public static final String UNKNOWN_COMMAND = "Unknown command, please enter a valid command";
    public static final String NO_DATA_FOUND = "No data found";
    public static final String BASE64_DECODING_FAILED_MESSAGE = "Your data does not seem to be in Base64 format";
    public static final String HEX_DECODING_FAILED_MESSAGE = "Invalid hexadecimal string";
    public static final String EMPTY_RECIPE_MESSAGE = "The recipe is currently empty. There is no module to be "
            + "removed.";
    public static final String DUPLICATE_RECIPE_NAME_MESSAGE = "A recipe with the given name already exists.";
    public static final String RECIPE_NOT_FOUND_MESSAGE = "The given recipe could not be found.";
    public static final String EDITED_RECIPE_NOT_FOUND_MESSAGE = "No recipe selected. Please select using the "
            + "\"recipe select\" command.";
}
