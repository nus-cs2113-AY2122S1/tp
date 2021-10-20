package seedu.duke;

import seedu.duke.employee.EmployeeList;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.main.MainParser;
import seedu.duke.main.MainUI;
import seedu.duke.dish.Menu;
import seedu.duke.storage.Storage;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        EmployeeList employeeList = new EmployeeList();
        Menu menu = new Menu();
        IngredientList ingredientList = new IngredientList();

        // Load Storage
        Storage.loadStorage(employeeList, menu, ingredientList);

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
            isBye = MainParser.handleCommand(employeeList, menu, ingredientList, userInput);
        }

        // Bye
        MainUI.printGoodbyeMessage();

        // Save Storage
        Storage.saveStorage(employeeList, menu, ingredientList);
        MainUI.printStorageSaved();
    }

}
