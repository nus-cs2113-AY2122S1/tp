package expiryeliminator.util;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import expiryeliminator.data.*;
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
            recipe.add("Chicken", 1, ingredientRepository);
            recipe.add("Salt", 20, ingredientRepository);
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Recipe should be valid by definition");
        }
    }

    public static void addIngredientsToPorkRecipe(Recipe recipe) {
        final IngredientRepository ingredientRepository = generateIngredientRepositoryForRecipe();
        assert ingredientRepository != null;
        try {
            recipe.add("Pork", 1, ingredientRepository);
            recipe.add("Salt", 20, ingredientRepository);
        } catch (DuplicateDataException | IllegalValueException e) {
            fail("Recipe should be valid by definition");
        }
    }

    public static void addIngredientsWithoutUnitsToRecipe(Recipe recipe) {
        final IngredientRepository ingredientRepository = generateIngredientRepositoryWithoutUnitsForRecipe();
        assert ingredientRepository != null;
        try {
            recipe.add("Chicken", 1, ingredientRepository);
            recipe.add("Salt", 20, ingredientRepository);
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

    public static ArrayList<String> generateIngredientNamesForRecipe() {
        final ArrayList<String> ingredientNames = new ArrayList<>();
        ingredientNames.add("Chicken");
        ingredientNames.add("Salt");
        return ingredientNames;
    }

    public static ArrayList<String> generateDuplicateIngredientNamesForRecipe() {
        final ArrayList<String> ingredientNames = new ArrayList<>();
        ingredientNames.add("Chicken");
        ingredientNames.add("Chicken");
        return ingredientNames;
    }

    public static ArrayList<Integer> generateQuantitiesForRecipe() {
        final ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(1);
        quantities.add(20);
        return quantities;
    }

    public static ArrayList<Integer> generateZeroQuantityForRecipe() {
        final ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(1);
        quantities.add(0);
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

    public static IngredientRepository generateIngredientRepositoryWithoutUnitsForRecipe() {
        final IngredientRepository ingredientRepository = new IngredientRepository();
        try {
            ingredientRepository.add("Chicken");
            ingredientRepository.add("Salt");
            return ingredientRepository;
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

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
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

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
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    public static IngredientRepository generateIngredientRepositoryForCookedRecipeCommand() {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeDays = currentDate.plus(3, ChronoUnit.DAYS);
        final LocalDate currentDatePlusThreeWeeks = currentDate.plus(3, ChronoUnit.WEEKS);

        final IngredientRepository ingredientRepository = new IngredientRepository();

        try {
            //expiring
            ingredientRepository.add("Chicken", null, 2, currentDatePlusThreeDays);
            //fresh
            ingredientRepository.add("Salt", null, 40, currentDatePlusThreeWeeks);
            return ingredientRepository;
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        }
    }

    public static IngredientStorage generateFirstIngredient() {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeDays = currentDate.plus(3, ChronoUnit.DAYS);

        try {
            final IngredientRepository ingredients = new IngredientRepository();
            ingredients.add("Chicken",null, 1, currentDatePlusThreeDays);
            return ingredients.find("Chicken");
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        } catch (NotFoundException e) {
            fail("Ingredient should be in the repository by definition");
            return null;
        }
    }

    public static IngredientStorage generateSecondIngredient() {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate currentDatePlusThreeWeeks = currentDate.plus(3, ChronoUnit.WEEKS);

        try {
            final IngredientRepository ingredients = new IngredientRepository();
            ingredients.add("Salt",null, 20, currentDatePlusThreeWeeks);
            return ingredients.find("Salt");
        } catch (DuplicateDataException e) {
            fail("Ingredient repository should be valid by definition");
            return null;
        } catch (NotFoundException e) {
            fail("Ingredient should be in the repository by definition");
            return null;
        }
    }

    public static IngredientRepository generateEmptyIngredientRepository() {
        return new IngredientRepository();
    }
}
