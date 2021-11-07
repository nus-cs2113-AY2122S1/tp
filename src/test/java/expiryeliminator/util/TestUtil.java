package expiryeliminator.util;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import expiryeliminator.data.IngredientQuantity;
import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

public class TestUtil {

    public static final String EXAMPLE_RECIPE_NAME = "Chicken Soup";

    public static final String EXAMPLE_INGREDIENT_NAME = "Chicken";

    public static final String RANDOM_INPUT_RECIPE_NAME = "Foo";

    public static final String INGREDIENTS_TO_UPDATE_UNITS = "Chicken\nSalt\n";

    public static void addIngredientsToChickenRecipe(Recipe recipe) {
        final IngredientRepository ingredientRepository = generateIngredientRepositoryForRecipe();
        assert ingredientRepository != null;
        try {
            recipe.addIngredient("Chicken", 1, ingredientRepository);
            recipe.addIngredient("Salt", 20, ingredientRepository);
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Recipe should be valid by definition");
        }
    }

    public static void addIngredientsToPorkRecipe(Recipe recipe) {
        final IngredientRepository ingredientRepository = generateIngredientRepositoryForRecipe();
        assert ingredientRepository != null;
        try {
            recipe.addIngredient("Pork", 1, ingredientRepository);
            recipe.addIngredient("Salt", 20, ingredientRepository);
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Recipe should be valid by definition");
        }
    }

    public static void addIngredientsWithoutUnitsToRecipe(Recipe recipe) {
        final IngredientRepository ingredientRepository = generateIngredientRepositoryForExampleRecipe(1, 20);
        assert ingredientRepository != null;
        try {
            recipe.addIngredient("Chicken", 1, ingredientRepository);
            recipe.addIngredient("Salt", 20, ingredientRepository);
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Recipe should be valid by definition");
        }
    }

    public static Recipe generateChickenRecipe() {
        final Recipe recipe = new Recipe("Chicken Soup");
        addIngredientsToChickenRecipe(recipe);
        return recipe;
    }

    public static Recipe generatePorkRecipe() {
        final Recipe recipe = new Recipe("Pork Soup");
        addIngredientsToPorkRecipe(recipe);
        return recipe;
    }


    public static Recipe generateRecipeWithoutUnits() {
        final Recipe recipe = new Recipe("Chicken Soup");
        addIngredientsWithoutUnitsToRecipe(recipe);
        return recipe;
    }

    /**
     * Generates the ingredient names required for a recipe to test for AddRecipeCommand.
     *
     * @param name1 The name of the first ingredient in the recipe
     * @param name2 The name of the second ingredient in the recipe
     * @return The list of ingredient names for the recipe.
     */
    public static ArrayList<String> generateIngredientNamesForRecipe(String name1, String name2) {
        final ArrayList<String> ingredientNames = new ArrayList<>();
        ingredientNames.add(name1);
        ingredientNames.add(name2);
        return ingredientNames;
    }

    /**
     * Generates the quantities required for a recipe to test for AddRecipeCommand.
     *
     * @param quantity1 Quantity for "Chicken" ingredient
     * @param quantity2 Quantity for "Salt" ingredient
     * @return The list of quantities for the ingredients.
     */
    public static ArrayList<Integer> generateQuantitiesForRecipe(int quantity1, int quantity2) {
        final ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(quantity1);
        quantities.add(quantity2);
        return quantities;
    }

    public static RecipeList generateRecipeListWithSingleRecipe() {
        try {
            Recipe recipe = generateChickenRecipe();
            RecipeList recipes = new RecipeList();
            recipes.add(recipe);
            return recipes;
        } catch (DuplicateDataException e) {
            fail("Recipe should be valid by definition");
            return null;
        }
    }

    public static RecipeList generateRecipeListWithMultipleRecipes() {
        try {
            Recipe recipe1 = generateChickenRecipe();
            Recipe recipe2 = generatePorkRecipe();

            RecipeList recipes = new RecipeList();
            recipes.add(recipe1);
            recipes.add(recipe2);
            return recipes;
        } catch (DuplicateDataException e) {
            fail("Recipe should be valid by definition");
            return null;
        }
    }

    public static RecipeList generateEmptyRecipeList() {
        return new RecipeList();
    }

    public static IngredientRepository generateIngredientRepositoryForRecipe() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        try {
            ingredientRepository.add("Chicken", "grams");
            ingredientRepository.add("Salt", "grams");
            return ingredientRepository;
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    public static IngredientRepository generateIngredientRepositoryForMultipleRecipe() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        try {
            ingredientRepository.add("Chicken", "grams");
            ingredientRepository.add("Pork", "grams");
            ingredientRepository.add("Salt", "grams");
            return ingredientRepository;
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    /**
     * Generates unexpired ingredients for example recipe to test for ListRecipeUserCanCookCommand output.
     *
     * @param quantity1 The quantity of the "Chicken" ingredient in the example recipe
     * @param quantity2 The quantity of the "Salt" ingredient in the example recipe
     * @return The ingredient repository for the example recipe.
     */
    public static IngredientRepository generateIngredientRepositoryForExampleRecipe(int quantity1, int quantity2) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeDays = currentDate.plus(3, ChronoUnit.DAYS);
        final LocalDate currentDatePlusThreeWeeks = currentDate.plus(3, ChronoUnit.WEEKS);

        final IngredientRepository ingredientRepository = new IngredientRepository();

        try {
            //expiring
            if (quantity1 <= 0) {
                ingredientRepository.add("Chicken");
            } else {
                ingredientRepository.add("Chicken", null, quantity1, currentDatePlusThreeDays);
            }
            //fresh
            if (quantity2 <= 0) {
                ingredientRepository.add("Salt");
            } else {
                ingredientRepository.add("Salt", null, quantity2, currentDatePlusThreeWeeks);
            }
            return ingredientRepository;
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    /**
     * Generates expired ingredients for example recipe to test for ListRecipeUserCanCookCommand output.
     *
     * @param quantity1 The quantity of the "Chicken" ingredient in the example recipe
     * @param quantity2 The quantity of the "Salt" ingredient in the example recipe
     * @return The ingredient repository for the example recipe.
     */
    public static IngredientRepository generateExpiredIngredientsForExampleRecipe(int quantity1, int quantity2) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDateMinusThreeDays = currentDate.minus(3, ChronoUnit.DAYS);
        final LocalDate currentDateMinusThreeWeeks = currentDate.minus(3, ChronoUnit.WEEKS);

        final IngredientRepository ingredientRepository = new IngredientRepository();

        try {
            ingredientRepository.add("Chicken", null, quantity1, currentDateMinusThreeDays);
            ingredientRepository.add("Salt", null, quantity2, currentDateMinusThreeWeeks);
            return ingredientRepository;
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    /**
     * Generates the ingredient to be used for comparison of CookedRecipeCommand output.
     *
     * @return The ingredient "Chicken" for the example recipe
     */
    public static IngredientStorage generateFirstIngredient() {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeDays = currentDate.plus(3, ChronoUnit.DAYS);

        try {
            final IngredientRepository ingredients = new IngredientRepository();
            ingredients.add("Chicken", null, 1, currentDatePlusThreeDays);
            return ingredients.find("Chicken");
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        } catch (NotFoundException e) {
            fail("Ingredient should be in the repository by definition");
            return null;
        }
    }

    /**
     * Generates the ingredient to be used for comparison of CookedRecipeCommand output.
     *
     * @return The ingredient "Salt" for the example recipe
     */
    public static IngredientStorage generateSecondIngredient() {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeWeeks = currentDate.plus(3, ChronoUnit.WEEKS);

        try {
            final IngredientRepository ingredients = new IngredientRepository();
            ingredients.add("Salt", null, 20, currentDatePlusThreeWeeks);
            return ingredients.find("Salt");
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        } catch (NotFoundException e) {
            fail("Ingredient should be in the repository by definition");
            return null;
        }
    }

    //@@author JoshHDs

    public static IngredientRepository generateEmptyIngredientRepository() {
        return new IngredientRepository();
    }


    /**
     * Creates an ingredient repository with 3 ingredients, one which is expired, one which is expiring, and one that
     * is not expiring any time soon.
     *
     * @return the ingredient repository consisting of the three type of ingredients.
     */
    public static IngredientRepository generateIngredientRepositoryWithSomeExpiredIngredients() {
        final LocalDate pastDate = LocalDate.of(2021, 10, 8);
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeDays = currentDate.plus(3, ChronoUnit.DAYS);
        final LocalDate currentDatePlusThreeWeeks = currentDate.plus(3, ChronoUnit.WEEKS);

        final IngredientRepository ingredientRepository = new IngredientRepository();

        try {
            //expired
            ingredientRepository.add("Red Apple", null, 1, pastDate);
            //expiring
            ingredientRepository.add("Blue Apple", null, 2, currentDatePlusThreeDays);
            //fresh
            ingredientRepository.add("Green Apple", null, 3, currentDatePlusThreeWeeks);
            return ingredientRepository;
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    /**
     * Creates an ingredient repository with 2 ingredients, both of which have not expired.
     *
     * @return the ingredient repository consisting of the three type of ingredients.
     */
    public static IngredientRepository generateIngredientRepositoryWithoutExpiredIngredients() {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeDays = currentDate.plus(3, ChronoUnit.DAYS);
        final LocalDate currentDatePlusThreeWeeks = currentDate.plus(3, ChronoUnit.WEEKS);

        final IngredientRepository ingredientRepository = new IngredientRepository();

        try {
            //expiring
            ingredientRepository.add("Blue Apple", null, 2, currentDatePlusThreeDays);
            //fresh
            ingredientRepository.add("Green Apple", null, 3, currentDatePlusThreeWeeks);
            return ingredientRepository;
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    /**
     * Gets the units of the salt ingredient in the ingredient repository.
     *
     * @return the units of the salt ingredient in the ingredient repository.
     */
    public static String getUpdatedUnitsForIngredientRepo(IngredientRepository ingredientRepository) {
        try {
            IngredientStorage ingredientStorage = ingredientRepository.find("Salt");
            return ingredientStorage.getIngredient().getUnit();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the units of the salt ingredient in the recipe list.
     *
     * @return the units of the salt ingredient in the recipe list.
     */
    public static String getUpdatedUnitsForRecipeList(RecipeList recipes) {
        try {
            Recipe chickenSoupRecipe = recipes.findRecipe("Chicken Soup");
            IngredientQuantity ingredientAndQuantity = chickenSoupRecipe.getIngredientQuantities().get("Salt");
            String newUnit = ingredientAndQuantity.getIngredient().getUnit();
            return newUnit;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
