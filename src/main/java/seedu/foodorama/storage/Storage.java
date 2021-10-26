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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    private static Logger logger = Logger.getLogger("Storage class");
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME_DISH = "dishes.txt";
    private static final String FILE_NAME_INGR = "ingredients.txt";
    private static final String FILE_NAME_FORMAT = "formats.txt";

    public static void write(String mode) {
        try {
            File newDirectory = new File(DIRECTORY_NAME);
            if (!newDirectory.exists()) {
                newDirectory.mkdirs();
            }
            switch (mode) {
            case "ingredient":
                FileWriter writer = new FileWriter(DIRECTORY_NAME + File.separator + FILE_NAME_INGR);
                for (Ingredient ingredient : IngredientList.ingredientList) {
                    writer.write(ingredient.formatData() + "\n");
                }
                writer.close();
                break;

            case "dish":
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
            logger.log(Level.INFO, "File creation failed / unable to retrieve file");
            System.out.println("____________________________________________");
            System.out.println("Unable to access file");
            System.out.println("____________________________________________");
        }
    }

    public static void load() {
        File newDirectory = new File(DIRECTORY_NAME);
        if (!newDirectory.exists()) {
            newDirectory.mkdirs();
        }
        loadIngredients();
        loadDishes();
        loadFormat();
    }

    private static void loadFormat() {
        Ui ui = new Ui();
        try {
            FileWriter writer = new FileWriter(DIRECTORY_NAME + File.separator + FILE_NAME_FORMAT);
            writer.write(ui.getFormatMessage());
            writer.close();
        } catch (IOException e) {
            logger.log(Level.INFO, "File creation failed / unable to retrieve file");
            System.out.println("____________________________________________");
            System.out.println("Unable to access file");
            System.out.println("____________________________________________");
        }
    }


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
                    Ingredient ingredientToAdd = new Ingredient(params[0], Double.parseDouble(params[1]),
                            Double.parseDouble(params[2]));
                    ingredientToAdd.setLimit(Double.parseDouble(params[3]));
                    IngredientList.ingredientList.add(ingredientToAdd);
                } catch (NumberFormatException | IndexOutOfBoundsException ignored) {
                    System.out.println("Invalid data entry, disregarding");
                }
            }
        } catch (FileNotFoundException e) {
            try {
                fileToReadIngr.createNewFile();
            } catch (IOException ex) {
                logger.log(Level.INFO, "File creation failed / unable to retrieve file");
                System.out.println("____________________________________________");
                System.out.println("Unable to create ingredients.txt");
                System.out.println("____________________________________________");
            }
        }
    }

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
                    Dish dishToAdd = new Dish(params[0], Double.parseDouble(params[1]), Double.parseDouble(params[2]));
                    dishToAdd.setLimit(Double.parseDouble(params[3]));
                    if (params.length > 4) {
                        //System.out.println("Contains constituents");
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
                logger.log(Level.INFO, "File creation failed / unable to retrieve file");
                System.out.println("____________________________________________");
                System.out.println("Unable to create dishes.txt");
                System.out.println("____________________________________________");
            }
        }
    }
}