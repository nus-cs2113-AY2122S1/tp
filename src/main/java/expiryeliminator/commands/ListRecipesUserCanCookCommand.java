package expiryeliminator.commands;

import expiryeliminator.common.LogsCenter;
import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.IngredientQuantity;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.Recipe;

import java.time.LocalDate;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lists recipes that can be cooked with the current amount of ingredients.
 */
public class ListRecipesUserCanCookCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list recipes i can cook";

    public static final String MESSAGE_NOT_ENOUGH_INGREDIENTS = "You don't have enough ingredients to cook "
            + "any recipes :(";
    public static final String MESSAGE_RECIPES_WITH_ENOUGH_INGREDIENTS_TO_COOK = "Here are the recipes you can cook "
            + "with the ingredients you have:\n"
            + "\n%1$s";
    public static final String MESSAGE_INGREDIENTS_EXPIRED = "Note that some of these ingredients have expired:\n"
            + "\n%1$s\n" + "Please remove them if you don't want to use them for your recipe.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists recipes that you can cook with the ingredients\n"
            + "you currently have.\n"
            + "Example: " + COMMAND_WORD;

    private static final Logger logger = LogsCenter.getLogger(ListRecipesUserCanCookCommand.class);

    private String checkForExpiredIngredients(TreeMap<String, IngredientQuantity> ingredientsFromRecipe,
                                                IngredientRepository ingredients) {
        String expiredIngredients = "";
        for (IngredientQuantity i : ingredientsFromRecipe.values()) {
            IngredientStorage ingredient = ingredients.findWithNullReturn(i.getName());
            assert ingredient != null : "Ingredient should be in the repository after the recipe is added";
            LocalDate expiryDate = ingredient.getEarliestExpiryDate();
            logger.log(Level.INFO,"Checking if some of " + ingredient.getIngredient() + " are expired.");
            if (expiryDate.isBefore(LocalDate.now())) {
                expiredIngredients += ingredient.getIngredient() + "\n";
            }
        }
        return expiredIngredients;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        String recipeList = "";
        String expiredIngredients = "";
        for (Recipe r : recipes.getRecipes().values()) {
            logger.log(Level.INFO,"Checking if " + r.getName() + " can be cooked");
            if (r.allIngredientsAreSufficient(ingredients)) {
                recipeList += r + "\n";
                logger.log(Level.INFO,"Checking if there are any expired ingredients for recipe " + r.getName());
                expiredIngredients += checkForExpiredIngredients(r.getIngredientQuantities(),ingredients);
            }
        }

        if (recipes.size() == 0) {
            return ListRecipeCommand.MESSAGE_EMPTY_RECIPE_LIST;
        } else if (recipeList.isEmpty()) {
            return MESSAGE_NOT_ENOUGH_INGREDIENTS;
        } else if (!expiredIngredients.isBlank()) {
            return String.format(MESSAGE_RECIPES_WITH_ENOUGH_INGREDIENTS_TO_COOK,recipeList)
                    + String.format(MESSAGE_INGREDIENTS_EXPIRED,expiredIngredients);
        }
        return String.format(MESSAGE_RECIPES_WITH_ENOUGH_INGREDIENTS_TO_COOK,recipeList);
    }
}
