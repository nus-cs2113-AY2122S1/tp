package seedu.duke;

import seedu.utility.Ui;

import java.util.Scanner;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui.printWelcome();
        Scanner in = new Scanner(System.in);
        
        boolean exitFlag = true;
        while (exitFlag) {
            String command = Ui.readCommand(in);
            if (command.equals("end")) {
                exitFlag = false;
            }
            System.out.println("You typed this: " + command);
        }
        
        Ui.printBye();
    }
}
