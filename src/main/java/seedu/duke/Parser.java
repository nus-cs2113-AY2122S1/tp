package seedu.duke;

import java.util.Scanner;

public class Parser {
    protected String nameRecipe;

    public void parseMaster(Cookbook cookbook) {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line  = in.nextLine();
            try {
                if (parseCommand(line).equalsIgnoreCase("add")) {
                    //Format of input:
                    //add [recipe name] /ingredients [ingredients separated by +] /steps [steps separated by +]
                    Recipe r = new Recipe(parseName(line));

                    System.out.println("Please add the ingredients, separated by +");
                    line = in.nextLine();
                    parseIngredients(line, r);

                    System.out.println("Please add the steps, separated by +");
                    line = in.nextLine();
                    parseSteps(line, r);

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
