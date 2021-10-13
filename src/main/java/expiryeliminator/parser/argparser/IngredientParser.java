package expiryeliminator.parser.argparser;

import expiryeliminator.common.Utils;
import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses ingredient name.
 */
public class IngredientParser extends MultipleArgParser<String> {
    /**
     * Parses ingredient name and converts to title case.
     *
     * @param ingredientString String to be parsed as an ingredient name.
     * @return Parsed ingredient name in title case.
     * @throws InvalidArgFormatException If the string is blank.
     */
    @Override
    public String parse(String ingredientString) throws InvalidArgFormatException {
        checkArgNotBlank(ingredientString, "Ingredient name cannot be blank.");
        return Utils.toTitleCase(ingredientString);
    }
}
