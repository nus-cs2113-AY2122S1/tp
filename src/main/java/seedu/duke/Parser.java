package seedu.duke;

import java.util.ArrayList;
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
                    //Do something
                    cookbook.addRecipe(r);
                    System.out.println("Added " + r.name + " recipe! Yum!");
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


    public void parseIngredients(String line, Recipe r) {
        //For all ingredients,
        //r.addIngredient(newIngredient);
    }

    public void parseSteps(String line, Recipe r) {
        //For all steps,
        //r.addStep(newStep);
    }


}
