package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddDishIngrCommandTest {
    @Test
    void execute() {
        //Define inputs
        Ui ui = new Ui();
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("chicken rice");
        inputs.add("rice");
        LinkCommand commandToTest = new LinkCommand();
        //Add the dish and ingr to link
        DishList.add("chicken rice");
        IngredientList.ingredientList.add(new Ingredient("rice", 2.34));
        try {
            commandToTest.execute(inputs);
            assertEquals(1,DishList.dishList.get(0).getParts().size());
            //Clear lists
            DishList.dishList.clear();
            IngredientList.ingredientList.clear();
            //Test case if dish not present
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals(ui.getDishNotExistMsg("chicken rice"), e.getMessage());
        }
    }
}