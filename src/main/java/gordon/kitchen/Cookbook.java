package gordon.kitchen;

import gordon.exception.GordonException;
import gordon.util.Difficulty;
import gordon.util.Tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/** <h1>Cookbook class.</h1>
 *
 * <p>
 *     Handles the commands given in  {@code Command} class that relates to the cookbook and
 *     updates the cookbook accordingly
 * </p>
 *
 */
public class Cookbook {
    protected ArrayList<Recipe> recipes;
    protected ArrayList<Tag> cookbookTags;

    public Cookbook() {
        recipes = new ArrayList<>();
        cookbookTags = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        if (recipes.size() == 0) {
            return ("You have no recipes in your Cookbook.\n");
        }

        for (int i = 0; i < recipes.size(); i++) {
            output.append(i + 1).append(". ");
            output.append(recipes.get(i).getName());
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cookbook cookbook = (Cookbook) o;
        return Objects.equals(recipes, cookbook.recipes)
                && Objects.equals(cookbookTags, cookbook.cookbookTags);
    }

    public String saveString(int index) {
        return recipes.get(index).toString();
    }

    public int numRecipes() {
        return recipes.size();
    }

    /**
     * <h2>void addRecipe(Recipe).</h2>
     *
     * <p>This methods adds the recipe to the cookbook if it does not already exist.</p>
     *
     * @param newRecipe  Recipe to be added to the cookbook
     * @throws GordonException  if the recipe already exists in the cookbook
     */
    public void addRecipe(Recipe newRecipe) throws GordonException {
        boolean contains = recipes.stream()
                .map(Recipe::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(ArrayList::new))
                .contains(newRecipe.name.toLowerCase());

        if (contains) {
            throw new GordonException(GordonException.DUPLICATE_RECIPE_NAME);
        }

        recipes.add(newRecipe);
    }

    /**
     * <h2>void removeRecipe(index).</h2>
     *
     * <p>This method check removes the recipe at the specified index of the cookbook.</p>
     *
     * @param index  The index of the recipe to be removed from {@code ArrayList<Recipe> recipes}
     * @throws GordonException  if the index given is out of bounds of {@code ArrayList<Recipe> recipes}
     */
    public void removeRecipe(int index) throws GordonException {
        try {
            assert (index >= 0);
            recipes.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        }
    }

    /**
     * <h2>void isRecipeExist(name).</h2>
     *
     * <p>This method checks whether a specified recipe exists in the cookbook.</p>
     *
     * @param name  The name of the recipe being checked
     * @throws GordonException  if the recipe specified does not exist in the cookbook
     */
    public void isRecipeExist(String name) throws GordonException {
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(name.toLowerCase())) {
                return;
            }
        }
        throw new GordonException(GordonException.NO_RECIPE_FOUND);
    }

    /**
     * <h2>void checkRecipe(name).</h2>
     *
     * <p>This method checks whether there are recipes in the cookbook that matches the recipe being queried.</p>
     * @param name  Recipe name being queried
     * @throws GordonException  if there is no matching recipe found.
     */
    public void checkRecipe(String name) throws GordonException {
        boolean isRecipeFound = false;

        System.out.println("Finding recipes called " + name + ".....");
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(name.toLowerCase())) {
                isRecipeFound = true;
                System.out.println("====================");
                System.out.print(recipe);
                System.out.println("====================");
            }
        }

        if (!isRecipeFound) {
            throw new GordonException(GordonException.NO_RESULT_FOUND);
        }
    }

    /**
     * <h2>void setIngredients(name, newIngredients).</h2>
     *
     * <p>This method replaces the Ingredients list in the recipe with the new user input</p>
     * @param name Recipe name being queried
     * @param newIngredients The new Ingredients list to be added to the recipe
     * @throws GordonException If there is no matching recipe found
     */
    public void setIngredients(String name, ArrayList<String> newIngredients) throws GordonException {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.replaceIngredients(newIngredients);
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }

    /**
     * <h2>void setSteps(name, newSteps).</h2>
     *
     * <p>This method replaces the Steps list in the recipe with the new user input</p>
     * @param name Recipe name being queried
     * @param newSteps The new Steps list to be added to the recipe
     * @throws GordonException If there is no matching recipe found
     */
    public void setSteps(String name, ArrayList<String> newSteps) throws GordonException {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.replaceSteps(newSteps);
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }

    /**
     * <h2>void setCalories(name).</h2>
     *
     * <p>This method adds the calories to a specified recipe</p>
     *
     * @param name  The name of the recipe being updated
     * @param newCalories  The number of calories the recipe is being labelled with
     * @throws GordonException  if there is no matching recipe found
     */
    public void setCalories(String name, int newCalories) throws GordonException {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.setCalories(newCalories);
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }

    /**
     * <h2>void setTimes(name, prepTime, cookTime).</h2>
     *
     * <p>This method adds the preparation time and cooking time to the recipe.</p>
     *
     * @param name  The name of the recipe being updated
     * @param prepTime  The preparation time the recipe is being labelled with
     * @param cookTime  The cooking time the recipe is being labelled with
     * @throws GordonException  if there is no matching recipe found
     *
     */
    public void setTimes(String name, int prepTime, int cookTime) throws GordonException {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.setTimes(prepTime, cookTime);
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }

    /**
     * <h2>void setPrice(name, newPrice).</h2>
     *
     * <p>This method adds the price to a recipe.</p>
     *
     * @param name  The name of the recipe being updated
     * @param newPrice  The price the recipe is being labelled with
     * @throws GordonException  if there is no matching recipe found.
     */
    public void setPrice(String name, float newPrice) throws GordonException {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.setTotalPrice(newPrice);
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }

    /**
     * <h2>void setDifficulty(name, newDifficulty).</h2>
     *
     * <p>This method adds the difficulty level to a recipe.</p>
     *
     * @param name  The name of the recipe being updated
     * @param newDifficulty  The difficulty the recipe is being labelled with
     * @throws GordonException  if there is no matching recipe found
     */
    public void setDifficulty(String name, Difficulty newDifficulty) throws GordonException {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.setDifficulty(newDifficulty);
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }
  

    /////////////////////////// TAGGING FUNCTIONALITIES ///////////////////////////
    /**
     * <h2>void addCookbookTag(tag).</h2>
     *
     * <p>This method adds a new tag to the cookbook</p>
     *
     * @param tag  The tag to be added to cookbook
     */
    public void addCookbookTag(Tag tag) {
        if (!doesCookbookTagExists(tag.getTagName())) {
            cookbookTags.add(tag);
        }
    }

    /**
     * <h2>void deleteCookbookTag(tag).</h2>
     *
     * <p>This method deletes a tag from the cookbook</p>
     *
     * @param tag  The tag to be deleted from cookbook
     */
    public void deleteCookbookTag(Tag tag) {
        cookbookTags.remove(tag);
    }

    public void appendRecipeToCookbookTag(String tagName, String recipeName) {
        try {
            Tag extractedTag = extractCookbookTag(tagName);
            extractedTag.addAssociatedRecipeName(recipeName);
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }

    public void deleteRecipeFromCookbookTag(String tagName, String recipeName) {
        try {
            Tag extractedTag = extractCookbookTag(tagName);
            extractedTag.removeAssociatedRecipeName(recipeName);

            if (extractedTag.numberOfAssociatedRecipes() == 0) {
                System.out.println(tagName
                        + " tag will no longer have any recipes under it. You might want to delete it!");
            }

        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }

    /**
     * <h2>addTagToRecipes(tag).</h2>
     *
     * <p>This method links a Tag to all its associated Recipes</p>
     * @param tag The tag that contains the associated recipes
     */
    public void addTagToRecipes(Tag tag) {
        for (Recipe recipe : recipes) {
            // ensure that Tag corresponds to correct recipe

            String recipeName = recipe.getName();
            if (tag.containsAssociatedRecipeNames(recipeName)) {
                recipe.addTagToRecipe(tag, recipeName, false);
            }
        }
    }

    /**
     * <h2>void untagTagFromRecipe(tag, recipeName).</h2>
     *
     * <p>This method removes a specified tag from a specified recipe.</p>
     *
     * @param tag  The tag that is to be removed
     * @param recipeName  The recipe that requires tag removal
     */
    public void untagTagFromRecipe(Tag tag, String recipeName) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(recipeName) && tag.containsAssociatedRecipeNames(recipe.getName())) {
                recipe.deleteTagFromRecipe(tag);
                tag.removeAssociatedRecipeName(recipe.getName());
                System.out.println("Successfully untagged " + recipeName + " from " + tag.getTagName().trim());
                return;
            }
        }

        System.out.println(recipeName + " does not have " + tag.getTagName() + " as a Tag");
    }

    /**.
     * <h2>void deleteTagFromRecipes(tag).</h2>
     *
     * <p>This method removes all instances of a specified tag from all its associated recipes.</p>
     *
     * @param tag  The tag that needs to be removed from all associated recipes
     */
    public void deleteTagFromRecipes(Tag tag) {
        for (Recipe recipe : recipes) {
            if (tag.containsAssociatedRecipeNames(recipe.getName())) {
                recipe.deleteTagFromRecipe(tag);
                tag.removeAssociatedRecipeName(recipe.getName());
            }
        }
    }

    /**
     * <h2> String listCookbookTags().</h2>
     *
     * <p>This method prints out all tags that are currently in the cookbook</p>
     *
     * @return The list of all tags
     */
    public String listCookbookTags() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cookbookTags.size(); i++) {
            output.append(i + 1).append(". ");
            output.append(cookbookTags.get(i).getTagName());
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    /**
     * <h2>Tag extractCookbookTag(tagName).</h2>
     *
     * <p> This method returns the tag matching the tag name, if found.</p>
     * 
     * @param tagName  The name of the tag to be extracted
     * @return  The extracted tag
     * @throws GordonException  if no matching tag is found
     */
    public Tag extractCookbookTag(String tagName) throws GordonException {
        for (Tag tag : cookbookTags) {
            if (tag.getTagName().toLowerCase().contains(tagName.toLowerCase())) {
                return tag;
            }
        }
        throw new GordonException(GordonException.NO_TAG_FOUND);
    }

    public boolean doesCookbookTagExists(String tagName) {
        for (Tag cookbookTag : cookbookTags) {
            if (cookbookTag.getTagName().toLowerCase().contains(tagName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /////////////////////////// FILTER FUNCTIONALITIES ///////////////////////////

    /**
     * <h2> ArrayList<> filterByIngredients(ingredients).</h2>
     *
     * <p> This method finds which recipes has the specified ingredients.</p>
     *
     * @param ingredients The list of ingredients being queried
     * @return the list of recipes containing the specified ingredients
     */
    public ArrayList<Recipe> filterByIngredients(ArrayList<String> ingredients) {
        return recipes.stream()
                .filter(recipe -> recipe.containsIngredients(ingredients))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * <h2> ArrayList<> filterByTags(tags).</h2>
     *
     * <p> This method finds which recipes has the specified tags.</p>
     *
     * @param tags Ths list of tags being queried
     * @return the list of recipes containing the specified tag
     */
    public ArrayList<Recipe> filterByTags(ArrayList<String> tags) {
        return recipes.stream()
                .filter(recipe -> recipe.containsTags(tags))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * <h2> ArrayList<> filterByDifficulty(difficulty).</h2>
     *
     * <p> This method finds which recipes are labelled with the specified difficulty.</p>
     *
     * @param difficulty The difficulty being queried
     * @return the list of recipes labelled with the specified query
     */
    public ArrayList<Recipe> filterByDifficulty(Difficulty difficulty) {
        return recipes.stream()
                .filter(recipe -> recipe.difficulty == difficulty)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * <h2> ArrayList<> filterByPrice(price).</h2>
     *
     * <p> This method finds which recipes cost less than the specified price.</p>
     *
     * @param price The price being queried
     * @return the list of recipes that cost less than the price being queried.
     */
    public ArrayList<Recipe> filterByPrice(float price) {
        Comparator<Recipe> compareByPrice = Comparator.comparing(Recipe::getTotalPrice);
        return recipes.stream()
                .filter(recipe -> recipe.totalPrice <= price)
                .sorted(compareByPrice.reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * <h2> ArrayList<> filterByCalories(cal).</h2>
     *
     * <p> This method finds which recipes has fewer calories than the specified calories.</p>
     *
     * @param cal The calories being queried
     * @return the list of recipes that has fewer calories than the calories being queried
     */
    public ArrayList<Recipe> filterByCalories(int cal) {
        Comparator<Recipe> compareByCalories = Comparator.comparing(Recipe::getCalories);
        return recipes.stream()
                .filter(recipe -> recipe.calories <= cal)
                .sorted(compareByCalories.reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * <h2> ArrayList<> filterByTime(time).</h2>
     *
     * <p> This method finds which recipes has few total time than the specified time.</p>
     *
     * @param time The total time being queried
     * @return the list of recipes whose total time taken is less than the time being queried
     */
    public ArrayList<Recipe> filterByTime(int time) {
        Comparator<Recipe> compareByTime = Comparator.comparing(Recipe::getTotalTime);
        return recipes.stream()
                .filter(recipe -> recipe.getTotalTime() <= time)
                .sorted(compareByTime.reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
