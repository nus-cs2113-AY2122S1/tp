package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public class RecipeParser extends SingleArgParser<String> {
    public String parse(String recipeString) throws InvalidArgFormatException {
        checkArgNotBlank(recipeString, "Recipe name cannot be blank.");
        return recipeString;
        return Utils.toTitleCase(recipeString);
    }
}
