package seedu.duke;

import seedu.duke.employee.EmployeeList;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.main.MainParser;
import seedu.duke.main.MainUI;
import seedu.duke.menu.MenuList;
import seedu.duke.storage.Storage;

import java.io.File;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        EmployeeList employeeList = new EmployeeList();
        MenuList menuList = new MenuList();
        IngredientList ingredientList = new IngredientList();

        // Load Storage
        File file = Storage.loadStorage(employeeList, menuList, ingredientList);

        // Hello
        MainUI.printWelcomeMessage();

        // Active Chat
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean isBye = false;

        while (!isBye) {
            //store input into String
            userInput = input.nextLine();
            //process input
            isBye = MainParser.handleCommand(employeeList, menuList, ingredientList, userInput);
        }

        // Bye
        MainUI.printGoodbyeMessage();

        // Save Storage
        Storage.saveStorage(employeeList, menuList, ingredientList, file);
        MainUI.printStorageSaved();
    }

}
