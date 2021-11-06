package gordon.exception;

import java.util.logging.Logger;
import java.util.logging.Level;

public class GordonException extends Exception {
    public static final String loggerName = "ErrorLogger";
    static Logger logger;

    public static final String INDEX_OOB = "Index outside range.";
    public static final String INDEX_INVALID = "Index is not a valid integer.";
    public static final String FLOAT_INVALID = "Index is not a valid float.";

    public static final String LISTHELP_COMMAND_INVALID = "Too many parameters, type 'help' for more info";

    public static final String INGREDIENTS_FORMAT = "Please use the word '/ingredients' before adding ingredients.";
    public static final String STEPS_FORMAT = "Please use the word '/steps' to kickstart the adding of steps.";
    public static final String DUPLICATE_RECIPE_NAME = "No duplicate recipe names allowed.";
    public static final String DUPLICATE_INGREDIENT_NAME = "No duplicate ingredient names allowed.";
    public static final String EMPTY_RECIPE_NAME = "Please input the name of the recipe.";
    public static final String EMPTY_INGREDIENT = "Please ensure that EACH ingredient field is not empty.";
    public static final String EMPTY_STEP = "Please ensure that EACH step field is not empty.";
    public static final String INVALID_DIFFICULTY = "Please input a valid difficulty.";

    public static final String COMMAND_INVALID = "You donkey! What are you talking about?";
    public static final String ADD_COMMAND_INVALID = "Please use the format "
            + "\"addRecipe RECIPE_NAME /ingredients INGREDIENTS /steps STEPS\"";
    public static final String DELETE_COMMAND_INVALID = "Please use the format \"deleteRecipe RECIPE_INDEX\"";
    public static final String SET_COMMAND_INVALID = "Please use the format \"set RECIPE_NAME /ATTRIBUTE VALUE\"";
    public static final String SET_TIME_COMMAND_INVALID = "Please use the format "
            + "\"set RECIPE_NAME /time PREP_TIME, COOK_TIME\"";
    public static final String FIND_COMMAND_INVALID = "Please use the format \"find /ATTRIBUTE VALUE\"";

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
