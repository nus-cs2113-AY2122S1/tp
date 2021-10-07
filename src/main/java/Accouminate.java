import command.MainCommand;
import picocli.CommandLine;
import terminal.Ui;

import java.util.concurrent.TimeUnit;

public class Accouminate {

    public static void main(String[] args) {
        new Accouminate().run();
    }

    public void run() {
        String[] splitUserInput;
        String userInput;

        Ui ui = Ui.getUi();
        ui.printGreeting();

        CommandLine cmd = new CommandLine(new MainCommand());

        while (true) {
            userInput = ui.getUserInput();
            splitUserInput = userInput.trim().split(" +"); //remove all excessive spaces in input
            Integer returnCode = cmd.execute(splitUserInput);

            //Allows error message to be printed out before the trying to get next user input
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
