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
    public static final String DIFF_PRIMER = "Difficulty:";
    public static final String CALORIES_PRIMER = "Calories (kcal):";
    public static final String TIME_PREP_PRIMER = "Preparation time:";
    public static final String TIME_COOK_PRIMER = "Cooking time:";
    public static final String PRICE_PRIMER = "Total price of ingredients:";
    public static final String INGREDIENTS_PRIMER = "Ingredients needed:";
    public static final String STEPS_PRIMER = "Method:";
    public static final String TAGS_PRIMER = "Tags:";
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
                buffer = loadPrice(r, buffer, loadScanner);
                buffer = loadIngredients(r, buffer, loadScanner);
                buffer = loadSteps(r, buffer, loadScanner);
                loadTags(r, buffer, loadScanner, cookbook);
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

    private String loadDifficulty(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals(DIFF_PRIMER)) {
            String line = loadScanner.nextLine().trim();
            for (Difficulty d : Difficulty.values()) {
                if (line.equals(d.name())) {
                    r.setDifficulty(d);
                }
            }
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    private String loadCalories(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals(CALORIES_PRIMER)) {
            String line = loadScanner.nextLine().trim();
            r.setCalories(Integer.parseInt(line));
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    private String loadTimes(Recipe r, String buffer, Scanner loadScanner) {
        String line = buffer;
        int prep = -1;
        int cook = -1;

        if (line.trim().equals(TIME_PREP_PRIMER)) {
            line = loadScanner.nextLine().trim();
            int spaceIndex = line.indexOf(" ");
            prep = Integer.parseInt(line.substring(0, spaceIndex));
            line = loadScanner.nextLine();
        }

        if (line.trim().equals(TIME_COOK_PRIMER)) {
            line = loadScanner.nextLine().trim();
            int spaceIndex = line.indexOf(" ");
            cook = Integer.parseInt(line.substring(0, spaceIndex));
            r.setTimes(prep, cook);
            return loadScanner.nextLine();
        } else {
            r.setTimes(prep, cook);
            return line;
        }
    }

    private String loadPrice(Recipe r, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals(PRICE_PRIMER)) {
            String line = loadScanner.nextLine().trim();
            r.setTotalPrice(Float.parseFloat(line.substring(1)));
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    private String loadIngredients(Recipe r, String buffer, Scanner loadScanner) {
        String line = buffer;
        if (line.trim().equals(INGREDIENTS_PRIMER)) {
            while (loadScanner.hasNext()) {
                line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedIngredient = line.substring(dotIndex + 2);
                r.addIngredient(parsedIngredient);
            }
            return line;
        } else {
            return buffer;
        }
    }

    private String loadSteps(Recipe r, String buffer, Scanner loadScanner) {
        String line = buffer;
        if (line.trim().equals(STEPS_PRIMER)) {
            while (loadScanner.hasNext()) {
                line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedStep = line.substring(dotIndex + 2);
                r.addStep(parsedStep);
            }
            return line;
        } else {
            return buffer;
        }
    }

    private void loadTags(Recipe r, String buffer, Scanner loadScanner, Cookbook cookbook) {
        String line = buffer;

        if (line.trim().equals(TAGS_PRIMER)) {
            while (loadScanner.hasNext()) {
                line = loadScanner.nextLine().trim();
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
                r.addTagToRecipe(createdTag, r.getName(), true);
            }
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

    //For JUnit tests
    public void deleteSaveFile(String baseDir) {
        File load = new File(baseDir, FILENAME);
        load.delete();
    }
}
