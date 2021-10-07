package seedu.duke;

import seedu.duke.parser.CommandHandler;
import seedu.duke.parser.Parser;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        boolean isExit = false;

        while (!isExit) {
            userInputString = userInput.nextLine();

            Parser parseCommand = new Parser(userInputString);
            CommandHandler userCommand = new CommandHandler();
            isExit = userCommand.commandHandle(parseCommand, userInputString);
        }

        userInput.close();
    }
}
