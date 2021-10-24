package expiryeliminator.storage;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientQuantity;
import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;

import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Properties;

public class LoadLists {
    public static void loadRecipeList(RecipeList recipes) {
        String pathName = "./data/";
        String fileName = "recipeList.txt";
        File file = new File(pathName + fileName);
        try {
            Scanner sc = new Scanner(file);
            Recipe currentRecipe = new Recipe(null);
            storeCurrentRecipe(recipes, sc, currentRecipe);
        } catch (DuplicateDataException | IllegalValueException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            SaveLists.createFileOrFolder(pathName, fileName);
        }
    }

    private static void storeCurrentRecipe(RecipeList recipes, Scanner sc, Recipe currentRecipe) throws IllegalValueException, DuplicateDataException {
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (!line.isBlank()) {
                if (!line.contains("-")) {
                    currentRecipe.setName(line);
                } else {
                    storeCurrentIngredient(currentRecipe, line);
                    if (!sc.hasNext()) {
                        recipes.add(currentRecipe);
                    }
                }
            } else {
                if (currentRecipe.getName() != null) {
                    recipes.add(currentRecipe);
                    currentRecipe = new Recipe(null);
                }
            }
        }
    }

    private static void storeCurrentIngredient(Recipe currentRecipe, String line) throws IllegalValueException {
        int ingredientNameSeparator = line.indexOf("(") - 1;
        String ingredientName = line.substring(2, ingredientNameSeparator);
        String theDigits = line.replaceAll("[^0-9]", "");
        int lengthOfQuantity = theDigits.length();
        int quantityStartIndex = line.indexOf(theDigits);
        int unitStartIndex = quantityStartIndex + lengthOfQuantity;
        int unitEndIndex = line.indexOf(")");
        int quantity = Integer.parseInt(theDigits);
        String unit = line.substring(unitStartIndex, unitEndIndex);
        if (unit.isBlank()) {
            unit = null;
        }
        Ingredient ingredient = new Ingredient(ingredientName, unit);
        IngredientQuantity ingredientQuantity = new IngredientQuantity(ingredient, quantity);
        currentRecipe.ingredientQuantities.put(ingredientName, ingredientQuantity);
    }

    /*public static void loadIngredientRepo(IngredientRepository ingredients) {
        String pathName = "./data/";
        String fileName = "ingredientList.txt";
        File file = new File(pathName + fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                int ingredientNameSeparator = line.indexOf("(") - 1;
                String ingredientName = line.substring(0, ingredientNameSeparator);
                int quantityStart = line.indexOf(":") + 2;
                int quantityEnd = line.indexOf(")");
                int ingredientQuantity = Integer.parseInt(line.substring(quantityStart, quantityEnd));
                int expiryDateStart = line.indexOf("expiry:") + 8;
                int expiryDateEnd = line.length() - 1;
                String expiryDateString = line.substring(expiryDateStart, expiryDateEnd);
                LocalDate expiryDate = LocalDate.parse(expiryDateString);
                Ingredient ingredient = new Ingredient(ingredientName, ingredientQuantity, expiryDate);
                ingredients.add(ingredient);
            }
        } catch (DuplicateDataException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            SaveLists.createFileOrFolder(pathName, fileName);
        }
    }*/
}