package gordon.util;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    public static final String PATHNAME = Paths.get("saveFile.txt").toString();
    static Logger logger;

    public Storage(Cookbook cookbook) {
        logger = Logger.getLogger(GordonException.loggerName);
        File load = new File(PATHNAME);

        try {
            Scanner loadScanner = new Scanner(load);

            while (loadScanner.hasNext()) {
                Recipe r = new Recipe(loadScanner.nextLine().trim());
                loadIngredients(r, loadScanner);
                loadSteps(r, loadScanner);
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

    public void loadIngredients(Recipe r, Scanner loadScanner) {
        String line = loadScanner.nextLine().trim();
        if (line.equals("Ingredients needed:")) {
            while (loadScanner.hasNext()) {
                line = loadScanner.nextLine().trim();
                int dotIndex = line.indexOf('.');

                if (dotIndex < 0) {
                    break;
                }

                String parsedIngredient = line.substring(dotIndex + 2);
                r.addIngredient(parsedIngredient);
            }
        }
    }

    public void loadSteps(Recipe r, Scanner loadScanner) {
        while (loadScanner.hasNext()) {
            String line = loadScanner.nextLine().trim();
            int dotIndex = line.indexOf('.');

            if (dotIndex < 0) {
                break;
            }

            String parsedStep = line.substring(dotIndex + 2);
            r.addStep(parsedStep);
        }
    }

    public void saveCookbook(Cookbook cookbook) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cookbook.numRecipes(); i++) {
            output.append(cookbook.saveString(i));
            output.append(System.lineSeparator());
        }
        try {
            FileWriter writer = new FileWriter(PATHNAME);
            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
