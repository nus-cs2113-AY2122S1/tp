package seedu.duke.main;

import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.dish.Menu;
import seedu.duke.dish.DishParser;

public class MainParser {

    public static boolean handleCommand(EmployeeList employeeList, Menu menu, IngredientList ingredientList,
                                        String userInput) {
        EmployeeParser employeeParser = new EmployeeParser();
        DishParser dishParser = new DishParser();
        IngredientParser ingredientParser = new IngredientParser();

        String[] command = userInput.trim().split("\\|", 3);

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
        case "bye":
            return true;
        default:
            MainUI.printWrongCommandMessage();
            break;
        }
        return false;
    }

}
