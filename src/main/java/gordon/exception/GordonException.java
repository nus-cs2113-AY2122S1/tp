package gordon.exception;

import java.util.logging.Logger;
import java.util.logging.Level;

public class GordonException extends Exception {
    public static final String loggerName = "ErrorLogger";
    static Logger logger;

    public static final String INDEX_OOB = "Index outside range.";
    public static final String INDEX_INVALID = "Index is not a valid integer.";

    public static final String INGREDIENTS_FORMAT = "Please use the word 'ingredients' before adding ingredients.";
    public static final String STEPS_FORMAT = "Please use the word 'steps' to kickstart the adding of steps.";
    public static final String CALORIES_FORMAT = "Please input integer for calories.";
    public static final String DUPLICATE_RECIPE_NAME = "No duplicate recipe names allowed.";
    public static final String EMPTY_RECIPE_NAME = "Please input name of recipe.";
    public static final String EMPTY_CALORIES = "Please input calories for the recipe.";
    public static final String INVALID_DIFFICULTY = "Please input a valid difficulty.";

    public static final String COMMAND_INVALID = "Command given cannot be recognized.";

    public static final String NO_RESULT_FOUND = "Search returns no result.";
    public static final String NO_RECIPE_FOUND = "No such recipe exists.";
    public static final String NO_TAG_FOUND = "No such tag exists.";

    public static final String TAG_NONE_DETECTED = "No tags detected, try again.";
    public static final String TAG_FORMAT_TOOSHORT = "Please use 'tag / recipeName / tagName1 + tagName2 + ...'";
    public static final String TAG_FORMAT_NOTAGS = "Please state which tags you wish to use.";
    public static final String DUPLICATE_TAG_NAME = "No duplicate tag names allowed within the same recipe.";
    public static final String UNTAG_FORMAT_TOOSHORT = "Please use 'untag / recipeName / tagName1 + tagName2 + ...'";
    public static final String UNTAG_FORMAT_NOTAGS = "Please state which tags you wish to untag.";
    public static final String DELETETAG_FORMAT_TOOSHORT = "Please use 'deleteTag / tagName1 + tagName2 + ...'";
    public static final String DELETETAG_FORMAT_NOTAGS = "Please say which tags you wish to delete.";

    public GordonException(String errorMessage) {
        super(errorMessage);
        logger = Logger.getLogger(loggerName);
        logger.log(Level.WARNING, errorMessage);
    }
}
