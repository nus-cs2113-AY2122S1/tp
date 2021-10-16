package seedu.duke.storage;

import seedu.duke.employee.Employee;
import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.dish.Dish;
import seedu.duke.dish.Menu;
import seedu.duke.dish.DishParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static final String FILE_NAME = "Duke.txt";

    public static void loadStorage(EmployeeList employeeList, Menu menu, IngredientList ingredientList) {
        File file = new File(FILE_NAME);
        EmployeeParser employeeParser = new EmployeeParser();
        DishParser dishParser = new DishParser();
        IngredientParser ingredientParser = new IngredientParser();
        if (!file.exists()) {
            System.out.println("File is not found!");
        }
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("add-employee")) {
                    Employee newEmployee = decodeEmployee(line);
                    employeeParser.loadEmployeeFromStorage(employeeList, newEmployee);
                } else if (line.startsWith("add-ingredient")) {
                    Ingredient newIngredient = decodeIngredient(line);
                    ingredientParser.loadIngredientFromStorage(ingredientList, newIngredient);
                } else if (line.startsWith("add-dish")) {
                    Dish newDish = decodeDish(line);
                    dishParser.loadDishFromStorage(menu, newDish);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read the file");
        }
    }

    private static Employee decodeEmployee(String toRead) {
        String[] description = toRead.trim().split("\\|", 3);
        Employee employee = new Employee(description[1], description[2]);
        return employee;
    }

    private static String encodeEmployee(String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 2);
        encodedItem = "add-employee" + "|" + description[0] + "|" + description[1];
        return encodedItem;
    }

    private static Ingredient decodeIngredient(String toRead) {
        String[] description = toRead.trim().split("\\|", 3);
        Ingredient ingredient = new Ingredient(description[1], description[2]);
        return ingredient;
    }

    private static String encodeIngredient(String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 2);
        encodedItem = "add-ingredient" + "|" + description[0] + "|"
                + description[1].substring(1, description[1].length() - 1);
        assert (!encodedItem.contains("["));
        assert (!encodedItem.contains("]"));
        return encodedItem;
    }

    private static Dish decodeDish(String toRead) {
        String[] description = toRead.trim().split("\\|", 4);
        double dishPrice = Double.parseDouble(description[2]);
        Dish dish = new Dish(description[1], dishPrice);
        double discount = Double.parseDouble(description[3]);
        dish.setDiscount(discount);
        return dish;
    }

    private static String encodeDish(String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ");
        encodedItem = "add-dish" + "|" + description[0] + "|" + description[2].substring(1) + "|" + description[3];
        assert (!encodedItem.contains("$"));
        return encodedItem;
    }

    public static void saveStorage(EmployeeList employeeList, Menu menu,
                                   IngredientList ingredientList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);

            for (int i = 0; i < employeeList.employeeList.size(); i += 1) {
                Employee employee = employeeList.employeeList.get(i);
                fileWriter.write(String.format("%s\n", encodeEmployee(employee.toString())));
            }
            for (int i = 0; i < ingredientList.ingredientList.size(); i += 1) {
                Ingredient ingredient = ingredientList.ingredientList.get(i);
                fileWriter.write(String.format("%s\n", encodeIngredient(ingredient.toString())));
            }
            for (int i = 0; i < menu.menu.size(); i += 1) {
                Dish dish = menu.menu.get(i);
                fileWriter.write(String.format("%s\n", encodeDish(dish.toString() + " " + dish.getDiscount())));
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot be written in the file!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception out of bounds array caught");
        }
    }

}
