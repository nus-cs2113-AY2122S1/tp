package expiryeliminator.commands;

//@@author JoshHDs

import expiryeliminator.data.IngredientRepository;
import org.junit.jupiter.api.Test;
import expiryeliminator.util.TestUtil;
import expiryeliminator.data.RecipeList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingListCommandTest {

    @Test
    public void shoppingListCommand_singleRecipeInput_expectStringOfIngredients() {
        //generate ingredient repo
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForRecipe();
        assert ingredientRepository != null;
        //generate recipe repo that contains a single recipe
        RecipeList recipes = TestUtil.generateRecipeListWithSingleRecipe();
        //generate array list of recipe input
        ArrayList<String> recipeInput = new ArrayList<>();
        recipeInput.add("Chicken Soup");
        //output message string
        String outputList = "Here is your shopping list!\n" + "\n" + "\n" + "Chicken (qty: 1 grams)\n"
                + "Salt (qty: 20 grams)";

        Command command = new ShoppingListCommand(recipeInput);

        assertEquals(command.execute(ingredientRepository, recipes), outputList);
    }

    @Test
    public void shoppingListCommand_multipleRecipeInput_expectStringOfIngredients() {
        //generate ingredient repo
        IngredientRepository ingredientRepository = TestUtil.generateIngredientRepositoryForMultipleRecipe();
        assert ingredientRepository != null;
        //generate recipe repo that contains a multiple recipe
        RecipeList recipes = TestUtil.generateRecipeListWithMultipleRecipes();
        //generate array list of recipe input
        ArrayList<String> recipeInput = new ArrayList<>();
        recipeInput.add("Chicken Soup");
        recipeInput.add("Pork Soup");
        //output message string
        String outputList = "Here is your shopping list!\n" + "\n" + "\n" + "Chicken (qty: 1 grams)\n"
                + "Pork (qty: 1)\n" + "Salt (qty: 40 grams)";

        Command command = new ShoppingListCommand(recipeInput);

        assertEquals(command.execute(ingredientRepository, recipes), outputList);
    }

}
