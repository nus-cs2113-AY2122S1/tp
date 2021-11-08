package seedu.foodorama.storage;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class deals with reading and writing to the data folder in the root directory of foodorama.
 *
 * @author Dniv-ra
 */
public class Storage {
    private static final String INGREDIENT = "ingredient";
    private static final String DISH = "dish";
    private static Logger LOGGER = Logger.getLogger("Storage class");
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME_DISH = "dishes.txt";
    private static final String FILE_NAME_INGR = "ingredients.txt";
    private static final String FILE_NAME_FORMAT = "formats.txt";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;
    private static final int INDEX_FOUR = 4;
    private static final int LENGTH_CONTAINS_CONSTITUENTS = 4;

    /**
     * Deals with writing the data that's present during runtime into a text file.
     * Creates data directory and files if files aren't present.
     * @param mode String that determines which of the text files the writing happens to
     *
     * @author Dniv-ra
     */
    public static void write(String mode) {
        try {
            File newDirectory = new File(DIRECTORY_NAME);
            if (!newDirectory.exists()) {
                newDirectory.mkdirs();
            }
            switch (mode) {
            case INGREDIENT:
                FileWriter writer = new FileWriter(DIRECTORY_NAME + File.separator + FILE_NAME_INGR);
                for (Ingredient ingredient : IngredientList.ingredientList) {
                    writer.write(ingredient.formatData() + "\n");
                }
                writer.close();
                break;

            case DISH:
                writer = new FileWriter(DIRECTORY_NAME + File.separator + FILE_NAME_DISH);
                for (Dish dish : DishList.dishList) {
                    writer.write(dish.formatData() + "\n");
                }
                writer.close();
                break;

            default:
                assert false;
                break;
            }

        } catch (IOException e) {
            LOGGER.log(Level.INFO, "File creation failed / unable to retrieve file");
            System.out.println("____________________________________________");
            System.out.println("Unable to access file");
            System.out.println("____________________________________________");
        }
    }

    /**
     * Deals with writing the data that's present during runtime into a text file.
     * Calls the respective load commands corresponding to each text file.
     * Is called once when Foodorama is started
     * Creates data directory and empty files if files aren't present
     *
     * @author Dniv-ra
     */
    public static void load() {
        File newDirectory = new File(DIRECTORY_NAME);
        if (!newDirectory.exists()) {
            newDirectory.mkdirs();
        }
        loadIngredients();
        loadDishes();
        loadFormat();
    }

    /**
     * Deals with loading formats.txt that contains the relevant file formats for manual data entry into data files
     *
     * @author Dniv-ra
     */
    private static void loadFormat() {
        Ui ui = new Ui();
        try {
            FileWriter writer = new FileWriter(DIRECTORY_NAME + File.separator + FILE_NAME_FORMAT);
            writer.write(ui.getFormatMessage());
            writer.close();
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "File creation failed / unable to retrieve file");
            System.out.println("____________________________________________");
            System.out.println("Unable to access file");
            System.out.println("____________________________________________");
        }
    }

    /**
     * Deals with loading ingredients.txt that contains the last saved version of the ingredient list
     *
     * @author Dniv-ra, renzocanare
     */
    private static void loadIngredients() {
        File fileToReadIngr = new File(DIRECTORY_NAME + File.separator + FILE_NAME_INGR);
        try {
            Scanner s = new Scanner(fileToReadIngr);
            while (s.hasNext()) {
                try {
                    String in = s.nextLine();
                    String[] params = in.split("\\|");
                    for (int i = 0; i < params.length; i++) {
                        params[i] = params[i].trim();
                    }
                    String ingredientName = params[INDEX_ZERO];
                    double ingredientWeight = Double.parseDouble(params[INDEX_ONE]);
                    double ingredientWaste = Double.parseDouble(params[INDEX_TWO]);
                    double limit = Double.parseDouble(params[INDEX_THREE]);
                    String expiryDate = params[INDEX_FOUR];
                    Ingredient ingredientToAdd = new Ingredient(ingredientName, ingredientWeight,
                            ingredientWaste, expiryDate);
                    ingredientToAdd.setLimit(limit);
                    IngredientList.ingredientList.add(ingredientToAdd);
                } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeParseException ignored) {
                    System.out.println("Invalid data entry, disregarding.");
                }
            }
        } catch (FileNotFoundException e) {
            try {
                fileToReadIngr.createNewFile();
            } catch (IOException ex) {
                LOGGER.log(Level.INFO, "File creation failed / unable to retrieve file");
                System.out.println("____________________________________________");
                System.out.println("Unable to create ingredients.txt");
                System.out.println("____________________________________________");
            }
        }
    }

    /**
     * Deals with loading dishes.txt that contains the last saved version of the dish list
     */
    private static void loadDishes() {
        File fileToReadIngr = new File(DIRECTORY_NAME + File.separator + FILE_NAME_DISH);
        try {
            Scanner s = new Scanner(fileToReadIngr);
            while (s.hasNext()) {
                try {
                    String in = s.nextLine();
                    String[] params = in.split("\\|");
                    for (int i = 0; i < params.length; i++) {
                        params[i] = params[i].trim();
                    }
                    String dishName = params[INDEX_ZERO];
                    double wastage = Double.parseDouble(params[INDEX_ONE]);
                    double ingredientContribution = Double.parseDouble(params[INDEX_TWO]);
                    double limit = Double.parseDouble(params[INDEX_THREE]);
                    Dish dishToAdd = new Dish(dishName, wastage, ingredientContribution);
                    dishToAdd.setLimit(limit);
                    if (params.length > LENGTH_CONTAINS_CONSTITUENTS) {
                        for (int i = 4; i < params.length; i++) {
                            int ingredientIndex = IngredientList.find(params[i]);
                            Ingredient dishComponent = IngredientList.ingredientList.get(ingredientIndex);
                            dishToAdd.getParts().add(dishComponent);
                            dishComponent.addDishWaste(dishToAdd.getIngredientContribution());
                        }
                    }
                    DishList.dishList.add(dishToAdd);
                } catch (NumberFormatException | IndexOutOfBoundsException ignored) {
                    System.out.println("Invalid data entry, disregarding");
                }
            }
        } catch (FileNotFoundException e) {
            try {
                fileToReadIngr.createNewFile();
            } catch (IOException ex) {
                LOGGER.log(Level.INFO, "File creation failed / unable to retrieve file");
                System.out.println("____________________________________________");
                System.out.println("Unable to create dishes.txt");
                System.out.println("____________________________________________");
            }
        }
    }
}