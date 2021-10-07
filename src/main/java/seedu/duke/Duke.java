package seedu.duke;

import seedu.duke.data.BudgetList;
import seedu.duke.parser.CommandHandler;
import seedu.duke.parser.Parser;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        BudgetList currentBudgetList = new BudgetList();

        boolean isExit = false;

        while (!isExit) {
            userInputString = userInput.nextLine();
            Parser parseCommand = new Parser(userInputString);
            CommandHandler userCommand = new CommandHandler();
            isExit = userCommand.commandHandle(parseCommand, userInputString, currentBudgetList);
        }

        userInput.close();
    }
}
