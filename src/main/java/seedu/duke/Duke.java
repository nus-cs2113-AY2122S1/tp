package seedu.duke;

import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.main.MainParser;
import seedu.duke.main.MainUI;
import seedu.duke.menu.MenuList;
import seedu.duke.menu.MenuParser;
import seedu.duke.storage.Storage;

import java.io.File;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        EmployeeList employeeList = new EmployeeList();
        EmployeeParser employeeParser = new EmployeeParser();

        MenuList menuList = new MenuList();
        MenuParser menuParser = new MenuParser();

        IngredientList ingredientList = new IngredientList();
        IngredientParser ingredientParser = new IngredientParser();

        // Load Storage
        File file = Storage.loadStorage(employeeList, employeeParser, menuList, menuParser, ingredientList, ingredientParser);

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
            isBye = MainParser.handleCommand(employeeList, employeeParser, menuList, menuParser, ingredientList, ingredientParser, userInput);
        }

        // Bye
        MainUI.printGoodbyeMessage();

        // Save Storage
        Storage.saveStorage(employeeList, menuList, ingredientList, file);
        MainUI.printStorageSaved();

    }

}
