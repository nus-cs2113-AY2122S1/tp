package seedu.duke;

import seedu.duke.employee.Employee;
import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.menu.MenuItem;
import seedu.duke.menu.MenuList;
import seedu.duke.menu.MenuParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String path = "Duke.txt";

    public static void main(String[] args) {

        EmployeeList employeeList = new EmployeeList();
        EmployeeParser employeeParser = new EmployeeParser();

        MenuList menuList = new MenuList();
        MenuParser menuParser = new MenuParser();

        IngredientList ingredientList = new IngredientList();
        IngredientParser ingredientParser = new IngredientParser();

        // Load Storage
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File is not found!");
        }
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Employee newEmployee = decodeEmployee(line);
                employeeParser.addEmployee(employeeList,newEmployee);
//                Ingredient newIngredient = decodeIngredient(line);
//                ingredientParser.addIngredient(ingredientList,newIngredient);
//                Menu newMenuItem = decodeMenuItem(line);
//                employeeParser.addMenu(menuList,newMenuItem);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read the file");
        }
        // Hello
        System.out.println("Hello!");


        // Active Chat
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean isBye = false;

        while (!isBye) {
            //store input into String
            userInput = input.nextLine();
            //process input
            if (userInput.startsWith("add-employee")) {

                String[] description = userInput.trim().split("\\|", 3);
                Employee newEmployee = new Employee(description[1], description[2]);
                employeeParser.addEmployee(employeeList, newEmployee);

            } else if (userInput.startsWith("remove-employee")) {

                String[] description = userInput.trim().split(" ", 2);
                int employeeIndex = Integer.parseInt(description[1]) - 1;
                employeeParser.deleteEmployee(employeeList, employeeIndex);

            } else if (userInput.startsWith("list-employee")) {

                employeeParser.listEmployee(employeeList);

            } else if (userInput.startsWith("add-menu")) {
             employeeParser.listEmployee(employeeList);

                String[] description = userInput.trim().split("\\|", 3);
                MenuItem newMenuItem = new MenuItem(description[1], description[2]);
                menuParser.addMenu(menuList, newMenuItem);

                System.out.println("I have added: ");
                System.out.println(newMenuItem);

            } else if (userInput.startsWith("remove-menu")) {

                String[] description = userInput.trim().split("\\|", 2);
                int targetIndex = Integer.valueOf(description[1]) - 1;
                MenuItem removedMenuItem = menuList.menuList.get(targetIndex);
                menuParser.deleteMenu(menuList, targetIndex);

                System.out.println("I have removed: ");
                System.out.println(removedMenuItem);

            } else if (userInput.startsWith("list-menu")) {

                menuParser.listMenu(menuList);

            } else if (userInput.startsWith("add-ingredients")) {

            } else if (userInput.startsWith("add-ingredient")) {

            } else if (userInput.startsWith("remove-ingredient")) {
                String[] description = userInput.trim().split(" ", 2);
                int deletedIngredientIndex = Integer.parseInt(description[1]) - 1;
                ingredientParser.deleteIngredient(ingredientList, deletedIngredientIndex);

            } else if (userInput.startsWith("list-ingredient")) {
                ingredientParser.listIngredient(ingredientList);

            } else if (userInput.startsWith("bye")) {
                isBye = true;
            } else {
                System.out.println("I do not recognise the input.");
                System.out.println("Please try again!");
            }
        }

        // Bye
        System.out.println("Thank you. Goodbye!");

        // Save Storage
        try {
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < employeeList.employeeList.size(); i += 1) {
                Employee employee = employeeList.employeeList.get(i);
                fileWriter.write(String.format("%s\n", encodeEmployee(employee.toString())));
            }
//            for (int i = 0; i < ingredientList.ingredientList.size(); i += 1) {
//                Ingredient ingredient = ingredientList.ingredientList.get(i);
//                fileWriter.write(String.format("%s\n", encodeIngredient(ingredient.toString())));
//            }
//            for (int i = 0; i < menuList.menuList.size(); i += 1) {
//                Menu menuItem = menuList.menuList.get(i);
//                fileWriter.write(String.format("%s\n", encodeMenuItem(menuItem.toString())));
//            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot be written in the file!");
        }

    }

    public static Employee decodeEmployee (String toRead) {
        String[] description = toRead.trim().split("\\|", 3);
        Employee employee = new Employee(description[1], description[2]);
        return employee;
    }

    public static String encodeEmployee (String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 2);
        encodedItem = "add-employee" + "|" + description[0] + "|" + description[1];
        return encodedItem;
    }
//    public static Ingredient decodeIngredient (String toRead) {
//        String[] description = toRead.trim().split("\\|", 3);
//        Ingredient ingredient = new Ingredient(description[1], description[2]);
//        return ingredient;
//    }

    public static String encodeIngredient (String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 2);
        encodedItem = "add-ingredient" + "|" + description[0] + "|" + description[1];
        return encodedItem;
    }

//    public static MenuItem decodeMenuItem (String toRead) {
//        String[] description = toRead.trim().split("\\|", 3);
//        MenuItem menuItem = new MenuItem(description[1], description[2]);
//        return menuItem;
//    }

    public static String encodeMenuItem (String toWrite) {
        String encodedItem = null;
        String[] description = toWrite.trim().split(" ", 2);
        encodedItem = "add-menu" + "|" + description[0] + "|" + description[1];
        return encodedItem;
    }


}
