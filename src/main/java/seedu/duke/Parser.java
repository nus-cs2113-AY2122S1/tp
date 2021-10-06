package seedu.duke;

import java.util.Scanner;

public class Parser {
    protected String nameRecipe;

    public static final int NAME_INDEX = 0;
    public static final int INGREDIENTS_INDEX = 1;
    public static final int STEPS_INDEX = 2;

    public void parseMaster(Cookbook cookbook) {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line  = in.nextLine();
            try {
                if (parseCommand(line).equalsIgnoreCase("add")) {
                    //Format of input:
                    //add [recipe name] /ingredients [ingredients separated by +] /steps [steps separated by +]
                    String[] splitContent = line.split("/");
                    Recipe r = new Recipe(parseName(splitContent[NAME_INDEX]));
                    parseIngredients(splitContent[INGREDIENTS_INDEX], r);
                    parseSteps(splitContent[STEPS_INDEX], r);

                    cookbook.addRecipe(r);
                    System.out.println("Added " + r.name + " recipe! Yum!");
                    System.out.println(r);
                }
                if (parseCommand(line).equalsIgnoreCase("delete")) {
                    nameRecipe = parseName(line);
                }
                if (parseCommand(line).equalsIgnoreCase("check")) {
                    nameRecipe = parseName(line);
                }
                if (parseCommand(line).equalsIgnoreCase("exit")) {
                    System.out.println("Bye bye!");
                    break;
                }
            } catch (GordonException e) {
                System.out.println("GordonException:" + e.getMessage());
            }
        }
    }

    public String parseCommand(String line) {
        int spaceIndex = line.indexOf(" ");
        return spaceIndex == -1 ? line : line.substring(0, spaceIndex);
    }

    public String parseName(String line) {
        int spaceIndex = line.indexOf(" ");
        String name = line.substring(spaceIndex);

        return spaceIndex == -1 ? line : name;
    }


    public void parseIngredients(String line, Recipe r) throws GordonException {
        //For all ingredients,
        String[] ingredientsList = line.split("\\+");
        for (int i = 0; i < ingredientsList.length; i++) {
            Ingredient ingredient = new Ingredient(ingredientsList[i]);
            r.addIngredient(ingredient, i);
        }
    }

    public void parseSteps(String line, Recipe r) throws GordonException {
        //For all steps,
        String[] stepsList = line.split("\\+");
        for (int i = 0; i < stepsList.length; i++) {
            r.addStep(stepsList[i], i);
        }
    }


}
