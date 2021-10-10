package seedu.duke;

import seedu.duke.employee.Employee;
import seedu.duke.employee.EmployeeList;
import seedu.duke.employee.EmployeeParser;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientParser;
import seedu.duke.menu.MenuList;
import seedu.duke.menu.MenuParser;

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

                System.out.println("I have added: ");
                System.out.println(employeeList.employeeList.get(0));

            } else if (userInput.startsWith("remove-employee")) {

            } else if (userInput.startsWith("list-employee")) {

            } else if (userInput.startsWith("add-menu")) {

            } else if (userInput.startsWith("remove-menu")) {

            } else if (userInput.startsWith("list-menu")) {

            } else if (userInput.startsWith("add-ingredients")) {

            } else if (userInput.startsWith("remove-ingredients")) {

            } else if (userInput.startsWith("list-ingredients")) {

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


    }


}
