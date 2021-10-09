package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public class IngredientParser extends MultipleArgParser<String> {
    @Override
    public String parse(String ingredientString) throws InvalidArgFormatException {
        checkArgNotBlank(ingredientString, "Ingredient name cannot be blank.");
        return ingredientString;
    }
}
