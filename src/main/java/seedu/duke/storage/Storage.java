package seedu.duke.storage;

import seedu.duke.employee.Employee;
import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;

import seedu.duke.finance.Finance;
import seedu.duke.finance.FinanceList;
import seedu.duke.finance.FinanceParser;
import seedu.duke.dish.Dish;
import seedu.duke.dish.Menu;
import seedu.duke.dish.DishParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The storage to manage,load and save data.
 */
public class Storage {

    public static final String FILE_NAME = "Duke.txt";

    /**
     * Load data from the default file
     *
     * @param employeeList the list of employees
     * @param menu the list of dishes
     * @param ingredientList the list of ingredients
     * @param financeList the list of accounts
     */
    public static void loadStorage(EmployeeList employeeList, Menu menu, IngredientList ingredientList,
                                   FinanceList financeList) {
        File file = new File(FILE_NAME);
        EmployeeParser employeeParser = new EmployeeParser();
        DishParser dishParser = new DishParser();
        IngredientParser ingredientParser = new IngredientParser();
        FinanceParser financeParser = new FinanceParser();
        if (!file.exists()) {
            //StorageUI.printFileNotFound();
        }
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("add-employee")) {
                    String[] command = line.trim().split("/", 5);
                    employeeParser.addEmployeeFromStorage(command, employeeList);
                } else if (line.startsWith("add-ingredient")) {
                    Ingredient newIngredient = decodeIngredient(line);
                    ingredientParser.loadIngredientFromStorage(ingredientList, newIngredient);
                } else if (line.startsWith("add-dish")) {
                    Dish newDish = decodeDish(line);
                    dishParser.loadDishFromStorage(menu, newDish);
                } else if (line.startsWith("add-finance")) {
                    Finance newFinance = decodeFinance(line);
                    financeParser.loadFinanceFromStorage(financeList, newFinance);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            //StorageUI.printFileReadException();
        }
    }

    /**
     * Decode the account from in the file
     *
     * @param toRead the string format of the account
     * @return the decoded account
     */
    private static Finance decodeFinance(String toRead) {
        String[] description = toRead.trim().split("\\|", 3);
        Finance finance = new Finance(LocalDate.parse(description[1]), description[2]);
        return finance;
    }

    /**
     * Encode the account to be stored in the file
     *
     * @param toWrite the account to be encoded
     * @return the string format of the account
     */
    private static String encodeFinance(String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 2);
        encodedItem = "add-finance" + "|" + description[0] + "|" + description[1].substring(1);
        return encodedItem;
    }

    /**
     * Decode the ingredient from the file
     *
     * @param toRead the string format of the ingredient
     * @return the decoded ingredient
     */
    private static Ingredient decodeIngredient(String toRead) {
        String[] description = toRead.trim().split("\\|", 5);
        Ingredient ingredient = new Ingredient(description[1], description[2], description[3], LocalDate.parse(description[4]));
        return ingredient;
    }

    /**
     * Encode the ingredient to be stored in the file
     *
     * @param toWrite the ingredient to be encoded
     * @return the string format of the ingredient
     */
    private static String encodeIngredient(String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 4);
        encodedItem = "add-ingredient" + "|" + description[0] + "|"
                + description[1].substring(1, description[1].length() - 1) + "|"
                + description[2].substring(2, description[2].length() - 1) + "|"
                + description[3].substring(1, description[3].length() - 1);
        assert (!encodedItem.contains("["));
        assert (!encodedItem.contains("]"));
        return encodedItem;
    }

    /**
     * Decode the dish from the file
     *
     * @param toRead the string format of the dish
     * @return the decoded dish
     */
    private static Dish decodeDish(String toRead) {
        String[] description = toRead.trim().split("\\|", 4);
        double dishPrice = Double.parseDouble(description[2]);
        Dish dish = new Dish(description[1], dishPrice);
        double discount = Double.parseDouble(description[3]);
        dish.setDiscount(discount);
        return dish;
    }

    /**
     * Encode the dish to be stored in the file
     *
     * @param dish the dish to be encoded
     * @return the string format of the dish
     */
    private static String encodeDish(Dish dish) {
        String encodedItem = null;
        encodedItem = "add-dish" + "|" + dish.getName() + "|" + dish.getPrice() + "|" + dish.getDiscount();
        assert (!encodedItem.contains("$"));
        return encodedItem;
    }

    /**
     * Save the date into the default file
     *
     * @param employeeList the list of employees
     * @param menu the list of dishes
     * @param ingredientList the list of ingredients
     * @param financeList the list of accounts
     */
    public static void saveStorage(EmployeeList employeeList, Menu menu,
                                   IngredientList ingredientList, FinanceList financeList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);

            for (int i = 0; i < employeeList.employeeList.size(); i += 1) {
                Employee employee = employeeList.employeeList.get(i);
                fileWriter.write(String.format("%s\n", employee.toStringStorage()));
            }
            for (int i = 0; i < ingredientList.ingredientList.size(); i += 1) {
                Ingredient ingredient = ingredientList.ingredientList.get(i);
                fileWriter.write(String.format("%s\n", encodeIngredient(ingredient.toString())));
            }
            for (int i = 0; i < menu.menu.size(); i += 1) {
                Dish dish = menu.menu.get(i);
                fileWriter.write(String.format("%s\n", encodeDish(dish)));
            }
            for (int i = 0; i < financeList.financeList.size(); i += 1) {
                Finance finance = financeList.financeList.get(i);
                fileWriter.write(String.format("%s\n", encodeFinance(finance.toString())));
            }
            fileWriter.close();
        } catch (IOException e) {
            StorageUI.printFileWriteError();
        } catch (ArrayIndexOutOfBoundsException e) {
            StorageUI.printOutOfBoundsException();
        }
    }

}
