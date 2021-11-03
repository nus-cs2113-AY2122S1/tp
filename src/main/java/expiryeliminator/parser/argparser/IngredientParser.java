package expiryeliminator.parser.argparser;

import expiryeliminator.common.Utils;
import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses ingredient name.
 */
public class IngredientParser extends MultipleArgParser<String> {
    private static final String MESSAGE_INVALID_INGREDIENT = "Ingredient name must only consist of letters, "
            + "not numbers or other special digits.";

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
        if (!Utils.isAlphabetic(ingredientString)) {
            throw new InvalidArgFormatException(MESSAGE_INVALID_INGREDIENT);
        }
        return Utils.toTitleCase(ingredientString);
    }
}
