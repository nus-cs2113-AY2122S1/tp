package expiryeliminator.parser.argparser;

import expiryeliminator.common.Utils;
import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses recipe name.
 */
public class RecipeParser extends SingleArgParser<String> {
    /**
     * Parses recipe name and converts to title case.
     *
     * @param recipeString String to be parsed as a recipe name.
     * @return Parsed recipe name in title case.
     * @throws InvalidArgFormatException If the string is blank.
     */
    public String parse(String recipeString) throws InvalidArgFormatException {
        checkArgNotBlank(recipeString, "Recipe name cannot be blank.");
        return Utils.toTitleCase(recipeString);
    }
}
