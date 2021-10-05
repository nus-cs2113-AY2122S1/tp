package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    protected ArrayList<String> ingredientsList = new ArrayList<>();
    protected ArrayList<String> stepsList = new ArrayList<>();
    protected String nameRecipe;


    public void parseMaster() {
        Scanner in = new Scanner(System.in);
        String line  = in.nextLine();

        while (true) {
            if (parseCommand(line).equalsIgnoreCase("add")) {
                nameRecipe = parseName(line);
                System.out.println("What are the Ingredients for this dish? Input them separated by “+”.");
                line = in.nextLine();
                ingredientsList = parseIngredients(line);
                System.out.println("What are the steps for this dish? Input them separated by “+”.");
                line = in.nextLine();
                stepsList = parseSteps(line);
                System.out.println("Added " + nameRecipe + " recipe! Yum!");
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
            line = in.nextLine();
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


    public ArrayList<String> parseIngredients(String line) {
        String[] ingredientsString = line.split("\\+");
        for (int i = 0; i < ingredientsString.length; i++) {
            ingredientsList.add(ingredientsString[i]);
        }
        return ingredientsList;
    }

    public ArrayList<String> parseSteps(String line) {
        String[] stepsString = line.split("\\+");
        for (int i = 0; i < stepsString.length; i++) {
            stepsList.add(stepsString[i]);
        }
        return stepsList;
    }


}
