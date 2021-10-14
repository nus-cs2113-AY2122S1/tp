package seedu.duke.storage;

import seedu.duke.employee.Employee;
import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.menu.Menu;
import seedu.duke.menu.MenuList;
import seedu.duke.menu.MenuParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static final String FILE_NAME = "Duke.txt";

    public static void loadStorage(EmployeeList employeeList, MenuList menuList, IngredientList ingredientList) {
        File file = new File(FILE_NAME);
        EmployeeParser employeeParser = new EmployeeParser();
        MenuParser menuParser = new MenuParser();
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
                } else if (line.startsWith("add-menu")) {
                    Menu newMenuItem = decodeMenuItem(line);
                    menuParser.loadMenuFromStorage(menuList, newMenuItem);
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

    private static Menu decodeMenuItem(String toRead) {
        String[] description = toRead.trim().split("\\|", 3);
        double menuPrice = Double.valueOf(description[2]);
        Menu menu = new Menu(description[1], menuPrice);
        return menu;
    }

    private static String encodeMenuItem(String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 2);
        encodedItem = "add-menu" + "|" + description[0] + "|" + description[1].substring(3);
        assert (!encodedItem.contains("$"));
        return encodedItem;
    }

    public static void saveStorage(EmployeeList employeeList, MenuList menuList,
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
            for (int i = 0; i < menuList.menuList.size(); i += 1) {
                Menu menu = menuList.menuList.get(i);
                fileWriter.write(String.format("%s\n", encodeMenuItem(menu.toString())));
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot be written in the file!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception out of bounds array caught");
        }
    }

}
