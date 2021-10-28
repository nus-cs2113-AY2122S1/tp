import command.MainCommand;
import picocli.CommandLine;
import storage.DataManager;
import terminal.Ui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Accouminate {

    public static void main(String[] args) {
        new Accouminate().run();
    }

    public void run() {
        String[] splitUserInput;
        String userInput;

        DataManager dataMgr = DataManager.getDataMgr();
        Ui ui = Ui.getUi();
        ui.printGreeting();

        try {
            dataMgr.loadAllManagers();
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
            return;
        }

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
