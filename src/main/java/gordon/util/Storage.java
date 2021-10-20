package gordon.util;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    public static final String FILENAME = "saveFile.txt";
    static Logger logger;

    public Storage(String baseDir, Cookbook cookbook) {
        logger = Logger.getLogger(GordonException.loggerName);
        File load = new File(baseDir, FILENAME);

        try {
            Scanner loadScanner = new Scanner(load);

            while (loadScanner.hasNext()) {
                Recipe r = new Recipe(loadScanner.nextLine());
                String buffer = loadScanner.nextLine();
                buffer = loadDifficulty(r, buffer, loadScanner);
                buffer = loadCalories(r, buffer, loadScanner);
                buffer = loadTimes(r, buffer, loadScanner);
                buffer = loadIngredients(r, buffer, loadScanner);
                loadSteps(r, buffer, loadScanner);
                loadTags(r, loadScanner, cookbook);
                cookbook.addRecipe(r);
            }

            logger.log(Level.INFO, "Previous session restored.");

        } catch (GordonException e) {
            System.out.println("GordonException:" + e.getMessage());
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            try {
                load.createNewFile();
                logger.log(Level.INFO, "Save file not found. Creating new file.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String loadDifficulty(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals("Difficulty:")) {
            String line = loadScanner.nextLine().trim();
            for (Difficulty d : Difficulty.values()) {
                if (line.equals(d.name())) {
                    r.setDifficulty(d);
                }
            }
            return null;
        } else {
            return buffer;
        }
    }

    public String loadCalories(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals("Calories (kcal):")) {
            String line = loadScanner.nextLine().trim();
            r.setCalories(Integer.parseInt(line));
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    //Not implemented yet
    public String loadTimes(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals("Preparation time:")) {
            String line = loadScanner.nextLine().trim();
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    public String loadIngredients(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals("Ingredients needed:")) {
            while (loadScanner.hasNext()) {
                String line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedIngredient = line.substring(dotIndex + 2);
                r.addIngredient(parsedIngredient);
            }
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    public String loadSteps(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals("Method:")) {
            while (loadScanner.hasNext()) {
                String line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedStep = line.substring(dotIndex + 2);
                r.addStep(parsedStep);
            }
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    public void loadTags(Recipe r, Scanner loadScanner, Cookbook cookbook) {
        while (loadScanner.hasNext()) {
            String line = loadScanner.nextLine().trim();
            int dotIndex = line.indexOf('.');

            if (dotIndex < 0) {
                break;
            }

            String parsedStep = line.substring(dotIndex + 2);
            Tag createdTag = new Tag(parsedStep, r.getName());

            if (!cookbook.doesCookbookTagExists(parsedStep)) {
                cookbook.addCookbookTag(createdTag);
            } else {
                cookbook.appendRecipeToCookbookTag(createdTag.getTagName(), r.getName());
            }
            r.addTagToRecipe(createdTag, r.getName());
        }
    }

    public void saveCookbook(Cookbook cookbook) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cookbook.numRecipes(); i++) {
            output.append(cookbook.saveString(i));
            output.append(System.lineSeparator());
        }
        try {
            FileWriter writer = new FileWriter(FILENAME);
            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
