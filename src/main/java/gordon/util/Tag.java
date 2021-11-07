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
        int initialSize = associatedRecipeNames.size();
        associatedRecipeNames.remove(recipeName);
        int finalSize = associatedRecipeNames.size();

        if (finalSize == 0 && initialSize != finalSize) {
            System.out.println(this.tagName
                    + " tag will no longer have any recipes under it. You might want to delete it!");
        }
    }

    public int numberOfAssociatedRecipes() {
        return this.associatedRecipeNames.size();
    }
}