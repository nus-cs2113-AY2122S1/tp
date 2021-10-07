package seedu.duke.parser;

public class CommandHandler {
    public boolean commandHandle(Parser commandHandle, String userInputString) {
        if (commandHandle.isAddBudget()) {
            System.out.println("Add Budget Command Triggered!");
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
