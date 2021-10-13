package seedu.duke.main;

import seedu.duke.employee.Employee;
import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.menu.MenuItem;
import seedu.duke.menu.MenuList;
import seedu.duke.menu.MenuParser;

public class MainParser {

    public static boolean handleCommand(EmployeeList employeeList, EmployeeParser employeeParser,
                                        MenuList menuList, MenuParser menuParser,
                                        IngredientList ingredientList, IngredientParser ingredientParser,
                                        String userInput) {

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
            case "add-menu":
                menuParser.addMenu(command, menuList);
                break;
            case "remove-menu":
                menuParser.deleteMenu(command, menuList);
                break;
            case "list-menu":
                menuParser.listMenu(menuList);
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
        }
        return false;

    }

}
