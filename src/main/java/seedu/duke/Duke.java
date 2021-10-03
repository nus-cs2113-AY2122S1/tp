package seedu.duke;
import Utility.Ui;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui.printWelcome();
        
        boolean exitFlag = true;
        while (exitFlag) {
            String command = Ui.readCommand();
            if(command.equals("end")) {
                exitFlag = false;
            }
            System.out.println("You typed this: " + command);
            
        }
        
        Ui.printBye();
    }
}
