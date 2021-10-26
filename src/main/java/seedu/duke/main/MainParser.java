package seedu.duke.main;

import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.finance.FinanceList;
import seedu.duke.finance.FinanceParser;
import seedu.duke.dish.Menu;
import seedu.duke.dish.DishParser;

public class MainParser {

    public static boolean handleCommand(EmployeeList employeeList, Menu menu, IngredientList ingredientList,
                                        FinanceList financeList, String userInput) {
        EmployeeParser employeeParser = new EmployeeParser();
        DishParser dishParser = new DishParser();
        IngredientParser ingredientParser = new IngredientParser();
        FinanceParser financeParser = new FinanceParser();

        String[] command = userInput.trim().split("/", 5);

        switch (command[0]) {
        case "add-employee":
            employeeParser.addEmployee(command, employeeList);
            break;
        case "remove-employee":
            employeeParser.deleteEmployee(command, employeeList);
            break;
        case "list-employee":
            employeeParser.listEmployee(employeeList);
            break;
        case "add-dish":
            dishParser.addDish(command, menu);
            break;
        case "remove-dish":
            dishParser.removeDish(command, menu);
            break;
        case "edit-dish":
            dishParser.editDish(command, menu);
            break;
        case "discount-dish":
            dishParser.discountDish(command, menu);
            break;
        case "list-menu":
            dishParser.listMenu(menu);
            break;
        case "add-ingredient":
            ingredientParser.addIngredient(command, ingredientList);
            break;
        case "remove-ingredient":
            ingredientParser.deleteIngredient(command, ingredientList);
            break;
        case "list-ingredient":
            ingredientParser.listIngredient(ingredientList);
            break;
        case "find-expired-ingredient":
            ingredientParser.findExpiredIngredient(command, ingredientList);
            break;
        case "add-finance":
            financeParser.addFinance(command, financeList);
            break;
        case "remove-finance":
            financeParser.deleteFinance(command, financeList);
            break;
        case "list-finance":
            financeParser.listFinance(financeList);
            break;
        case "show-finance":
            financeParser.showFinance(financeList);
            break;
        case "edit-finance":
            financeParser.editFinance(command, financeList);
            break;
        case "bye":
            return true;
        default:
            MainUI.printWrongCommandMessage();
            break;
        }
        return false;
    }

}
