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

/** <h1>Storage class.</h1>
 *
 * <p> Handles the saving and loading of the cookbook to and from saveFile.txt respectively.</p>
 */
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
                Recipe recipe = new Recipe(loadScanner.nextLine());
                String buffer = loadScanner.nextLine();
                buffer = loadDifficulty(recipe, buffer, loadScanner);
                buffer = loadCalories(recipe, buffer, loadScanner);
                buffer = loadTimes(recipe, buffer, loadScanner);
                buffer = loadPrice(recipe, buffer, loadScanner);
                buffer = loadIngredients(recipe, buffer, loadScanner);
                buffer = loadSteps(recipe, buffer, loadScanner);
                loadTags(recipe, buffer, loadScanner, cookbook);
                cookbook.addRecipe(recipe);
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


    /**
     * <h2> String loadDifficulty(recipe, buffer, loadScanner).</h2>
     *
     * <p> This method loads the difficulty stored in saveFile.txt to a recipe.</p>
     *
     * @param recipe The recipe where difficulty is being loaded into
     * @param buffer The String that corresponds to the contents of a line in saveFile.txt
     * @param loadScanner The scanner used to load lines of text from saveFile.txt
     * @return the buffer string
     */
    private String loadDifficulty(Recipe recipe, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals(DIFF_PRIMER)) {
            String line = loadScanner.nextLine().trim();
            for (Difficulty d : Difficulty.values()) {
                if (line.equals(d.name())) {
                    recipe.setDifficulty(d);
                }
            }
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    /**
     * <h2> String loadCalories(recipe, buffer, loadScanner).</h2>
     *
     * <p> This method loads the calories stored in saveFile.txt to a recipe.</p>
     *
     * @param recipe The recipe where calories are being loaded into
     * @param buffer The String that corresponds to the contents of a line in saveFile.txt
     * @param loadScanner The scanner used to load lines of text from saveFile.txt
     * @return the buffer string
     */
    private String loadCalories(Recipe recipe, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals(CALORIES_PRIMER)) {
            String line = loadScanner.nextLine().trim();
            recipe.setCalories(Integer.parseInt(line));
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    /**
     * <h2> String loadTimes(recipe, buffer, loadScanner).</h2>
     *
     * <p> This method loads the preparation and cooking times stored in saveFile.txt to a recipe.</p>
     *
     * @param recipe The recipe where the preparation and cooking times are being loaded into
     * @param buffer The String that corresponds to the contents of a line in saveFile.txt
     * @param loadScanner The scanner used to load lines of text from saveFile.txt
     * @return
     */
    private String loadTimes(Recipe recipe, String buffer, Scanner loadScanner) {
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
            recipe.setTimes(prep, cook);
            return loadScanner.nextLine();
        } else {
            recipe.setTimes(prep, cook);
            return line;
        }
    }

    /**
     * <h2> String loadPrice(recipe, buffer, loadScanner).</h2>
     *
     * <p> This method loads the price stored in saveFile.txt to a recipe.</p>
     *
     * @param recipe The recipe where price is being loaded into
     * @param buffer The String that corresponds to the contents of a line in saveFile.txt
     * @param loadScanner The scanner used to load lines of text from saveFile.txt
     * @return
     */
    private String loadPrice(Recipe recipe, String buffer, Scanner loadScanner) {
        if (buffer.trim().equals(PRICE_PRIMER)) {
            String line = loadScanner.nextLine().trim();
            recipe.setTotalPrice(Float.parseFloat(line.substring(1)));
            return loadScanner.nextLine();
        } else {
            return buffer;
        }
    }

    /**
     * <h2> String loadIngredients(recipe, buffer, loadScanner).</h2>
     *
     * <p> This method loads the ingredients stored in saveFile.txt to a recipe.</p>
     *
     * @param recipe The recipe where ingredients are being loaded into
     * @param buffer The String that corresponds to the contents of a line in saveFile.txt
     * @param loadScanner The scanner used to load lines of text from saveFile.txt
     * @return
     */
    private String loadIngredients(Recipe recipe, String buffer, Scanner loadScanner) {
        String line = buffer;
        if (line.trim().equals(INGREDIENTS_PRIMER)) {
            while (loadScanner.hasNext()) {
                line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedIngredient = line.substring(dotIndex + 2);
                recipe.addIngredient(parsedIngredient);
            }
            return line;
        } else {
            return buffer;
        }
    }

    /**
     * <h2> String loadSteps(recipe, buffer, loadScanner).</h2>
     *
     * <p> This method loads the steps stored in saveFile.txt to a recipe.</p>
     *
     * @param recipe The recipe where steps are being loaded into
     * @param buffer The String that corresponds to the contents of a line in saveFile.txt
     * @param loadScanner The scanner used to load lines of text from saveFile.txt
     * @return the buffer string
     */
    private String loadSteps(Recipe recipe, String buffer, Scanner loadScanner) {
        String line = buffer;
        if (line.trim().equals(STEPS_PRIMER)) {
            while (loadScanner.hasNext()) {
                line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedStep = line.substring(dotIndex + 2);
                recipe.addStep(parsedStep);
            }
            return line;
        } else {
            return buffer;
        }
    }

    /**
     * <h2> String loadTags(recipe, buffer, loadScanner, cookbook).</h2>
     *
     * <p> This method loads the tags stored in saveFile.txt to a recipe.</p>
     *
     * @param recipe The recipe where tags are being loaded into
     * @param buffer The String that corresponds to the contents of a line in saveFile.txt
     * @param loadScanner The scanner used to load lines of text from saveFile.txt
     * @param cookbook The cookbook where the recipe is being stored
     */
    private void loadTags(Recipe recipe, String buffer, Scanner loadScanner, Cookbook cookbook) {
        String line = buffer;

        if (line.trim().equals(TAGS_PRIMER)) {
            while (loadScanner.hasNext()) {
                line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedStep = line.substring(dotIndex + 2);
                Tag createdTag = new Tag(parsedStep, recipe.getName());

                if (!cookbook.doesCookbookTagExists(parsedStep)) {
                    cookbook.addCookbookTag(createdTag);
                } else {
                    cookbook.appendRecipeToCookbookTag(createdTag.getTagName(), recipe.getName());
                }
                recipe.addTagToRecipe(createdTag, recipe.getName(), true);
            }
        }
    }

    /**
     * <h2> void saveCookbook(cookbook).</h2>
     *
     * <p> This method saves the current cookbook to saveFile.txt.</p>
     *
     * @param cookbook The cookbook to be saved in saveFile.txt
     */
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
