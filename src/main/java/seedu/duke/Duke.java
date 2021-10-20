package seedu.duke;

import seedu.duke.employee.EmployeeList;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.main.MainParser;
import seedu.duke.main.MainUI;
import seedu.duke.menu.MenuList;
import seedu.duke.finance.FinanceList;
import seedu.duke.storage.Storage;

import java.io.File;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        EmployeeList employeeList = new EmployeeList();
        MenuList menuList = new MenuList();
        IngredientList ingredientList = new IngredientList();
        FinanceList financeList = new FinanceList();

        // Load Storage
        Storage.loadStorage(employeeList, menuList, ingredientList, financeList);

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
            isBye = MainParser.handleCommand(employeeList, menuList, ingredientList, financeList, userInput);
        }

        // Bye
        MainUI.printGoodbyeMessage();

        // Save Storage
        Storage.saveStorage(employeeList, menuList, ingredientList, financeList);
        MainUI.printStorageSaved();
    }

}
