package gordon.util;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.Scanner;

public class Parser {
    protected String nameRecipe;

    public static final int NAME_INDEX = 0;
    public static final int INGREDIENTS_INDEX = 1;
    public static final int STEPS_INDEX = 2;
    public static final int INGREDIENTS_WORD_LENGTH = 12;
    public static final int STEPS_WORD_LENGTH = 6;

    public void parseMaster(Cookbook cookbook, Storage storage) {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line  = in.nextLine();
            try {
                if (parseCommand(line).equalsIgnoreCase("add")) {
                    String[] splitContent = line.split("/");
                    if (splitContent.length < 3) {
                        throw new GordonException(GordonException.COMMAND_INVALID);
                    }
                    Recipe r = new Recipe(parseName(splitContent[NAME_INDEX]));
                    parseIngredients(splitContent[INGREDIENTS_INDEX], r);
                    parseSteps(splitContent[STEPS_INDEX], r);
                    cookbook.addRecipe(r);
                    System.out.println("Added " + r.getName() + " recipe! Yum!");
                    System.out.print(r);
                } else if (parseCommand(line).equalsIgnoreCase("delete")) {
                    nameRecipe = parseName(line);
                    String inputIndex = line.contains(" ") ? line.substring(line.indexOf(" ") + 1) : " ";
                    if (inputIndex.isEmpty() || inputIndex.equals(" ")) {
                        throw new GordonException(GordonException.COMMAND_INVALID);
                    }
                    try {
                        int index = Integer.parseInt(inputIndex);
                        cookbook.removeRecipe(index - 1);
                        System.out.println("OK! The recipe has been deleted from your cookbook.");
                    } catch (NumberFormatException e) {
                        throw new GordonException(GordonException.INDEX_INVALID);
                    }
                } else if (parseCommand(line).equalsIgnoreCase("check")) {
                    nameRecipe = parseName(line);
                    cookbook.checkRecipe(nameRecipe);
                } else if (parseCommand(line).equalsIgnoreCase("list")) {
                    System.out.print(cookbook);
                } else if (parseCommand(line).equalsIgnoreCase("exit")) {
                    System.out.println("Bye bye!");
                    break;
                } else if (parseCommand(line).equalsIgnoreCase("help")) {
                    System.out.println("add \"recipe name\" \"/ingredients\" 1+2+3 \"/steps\" 1+2+3");
                } else {
                    throw new GordonException(GordonException.COMMAND_INVALID);
                }
                storage.saveCookbook(cookbook);
            } catch (GordonException e) {
                System.out.println("GordonException: " + e.getMessage());
            }
        }
    }

    public String parseCommand(String line) throws GordonException {
        int spaceIndex = line.indexOf(" ");
        return (spaceIndex == -1) ? line : line.substring(0, spaceIndex);
    }

    public String parseName(String line) throws GordonException {
        int spaceIndex = line.indexOf(" ");

        if (spaceIndex == -1) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }

        return line.substring(spaceIndex).trim();
    }


    public void parseIngredients(String line, Recipe r) throws GordonException {
        int ingredientsIndex = line.indexOf("ingredients");
        if (ingredientsIndex == -1) {
            throw new GordonException(GordonException.INGREDIENTS_FORMAT);
        }
        String newLine = line.substring(ingredientsIndex + INGREDIENTS_WORD_LENGTH);
        String[] ingredientsList = newLine.split("\\+");
        for (int i = 0; i < ingredientsList.length; i++) {
            r.addIngredient(ingredientsList[i], i);
        }
    }

    public void parseSteps(String line, Recipe r) throws GordonException {
        int stepsIndex = line.indexOf("steps");
        if (stepsIndex == -1) {
            throw new GordonException(GordonException.STEPS_FORMAT);
        }
        String newLine = line.substring(stepsIndex + STEPS_WORD_LENGTH);
        String[] stepsList = newLine.split("\\+");
        for (int i = 0; i < stepsList.length; i++) {
            r.addStep(stepsList[i], i);
        }
    }
}
