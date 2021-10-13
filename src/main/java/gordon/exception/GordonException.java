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
    public static final String DUPLICATE_RECIPE_NAME = "No duplicate recipe names allowed.";
    public static final String EMPTY_CALORIES = "Please input calories for the recipe.";

    public static final String COMMAND_INVALID = "Command given cannot be recognized.";

    public static final String NO_RESULT_FOUND = "Search returns no result.";

    public GordonException(String errorMessage) {
        super(errorMessage);
        logger = Logger.getLogger(loggerName);
        logger.log(Level.WARNING, errorMessage);
    }
}
