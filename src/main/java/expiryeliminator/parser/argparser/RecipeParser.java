package expiryeliminator.parser.argparser;

import expiryeliminator.common.Utils;
import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses recipe name.
 */
public class RecipeParser extends MultipleArgParser<String> {
    private static final String MESSAGE_INVALID_RECIPE = "Recipe name must only consist of letters, "
            + "not numbers or other special digits.";

    /**
     * Parses recipe name and converts to title case.
     *
     * @param recipeString String to be parsed as a recipe name.
     * @return Parsed recipe name in title case.
     * @throws InvalidArgFormatException If the string is blank.
     */
    public String parse(String recipeString) throws InvalidArgFormatException {
        checkArgNotBlank(recipeString, "Recipe name cannot be blank.");
        if (!Utils.isAlphabetic(recipeString)) {
            throw new InvalidArgFormatException(MESSAGE_INVALID_RECIPE);
        }
        return Utils.toTitleCase(recipeString);
    }
}
