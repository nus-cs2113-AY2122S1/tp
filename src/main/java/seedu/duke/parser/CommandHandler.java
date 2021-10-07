package seedu.duke.parser;

import seedu.duke.commands.AddBudget;
import seedu.duke.commands.ListBudget;
import seedu.duke.data.BudgetList;

public class CommandHandler {
    public boolean commandHandle(Parser commandHandle, String userInputString, BudgetList currentBudgetList) {
        if (commandHandle.isAddBudget()) {
            AddBudget newBudget = new AddBudget();
            newBudget.addBudget(userInputString, currentBudgetList);
            return false;
        } else if (commandHandle.isListBudget()) {
            ListBudget listBudget = new ListBudget();
            listBudget.listBudget(currentBudgetList);
            return false;
        } else if (commandHandle.isBye()) {
            System.out.println("Goodbye!");
            return true;
        } else {
            System.out.println("I don't Getz");
            return false;
        }
    }
}
