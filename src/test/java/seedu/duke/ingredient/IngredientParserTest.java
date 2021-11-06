//@@author leecheokfeng

package seedu.duke.ingredient;

import org.junit.jupiter.api.Test;
import seedu.duke.dish.DishParser;
import seedu.duke.dish.Menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientParserTest {
    @Test
    void addIngredient_success() {
        IngredientList ingredients = new IngredientList();
        IngredientParser ingredientParser = new IngredientParser();
        String[] addCommand = "add-ingredient/Banana/30/1.50/2021-12-01".trim().split("/");
        ingredientParser.addIngredient(addCommand, ingredients);
        assertEquals(1, ingredients.ingredientList.size());
    }

    @Test
    void removeIngredient_success() {
        IngredientList ingredients = new IngredientList();
        IngredientParser ingredientParser = new IngredientParser();
        String[] addCommand = "add-ingredient/Banana/30/1.50/2021-12-01".trim().split("/");
        String[] removeCommand= "remove-ingredient/1".trim().split("/");
        ingredientParser.addIngredient(addCommand, ingredients);
        ingredientParser.deleteIngredient(removeCommand, ingredients);
        assertEquals(0, ingredients.ingredientList.size());
    }
}
