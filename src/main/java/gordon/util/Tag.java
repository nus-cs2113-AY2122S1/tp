package gordon.util;

import java.util.ArrayList;

public class Tag {
    protected String tagName;
    protected ArrayList<String> associatedRecipeNames = new ArrayList<>();

    public Tag(String tagName, String associatedRecipeName) {
        this.tagName = tagName;
        associatedRecipeNames.add(associatedRecipeName);
    }

    public String getTagName() {
        return tagName.trim();
    }

    public boolean containsAssociatedRecipeNames(String recipeNames) {
        for (String associatedRecipeName : associatedRecipeNames) {
            if (associatedRecipeName.equalsIgnoreCase(recipeNames)) {
                return true;
            }
        }
        return false;
    }

    public void addAssociatedRecipeName(String recipeName) {
        if (!containsAssociatedRecipeNames(recipeName)) {
            associatedRecipeNames.add(recipeName);
        }
    }

    public void removeAssociatedRecipeName(String recipeName) {
        associatedRecipeNames.remove(recipeName);
    }

    public int numberOfAssociatedRecipes() {
        return this.associatedRecipeNames.size();
    }
}