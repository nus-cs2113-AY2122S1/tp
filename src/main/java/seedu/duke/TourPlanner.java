package seedu.duke;

import java.util.Scanner;

public class TourPlanner {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public TourPlanner() {
        ;
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        boolean flag = true;
        String command;
        ClientList clientList = new ClientList();
        while (flag) {
            command = ui.readCommand();
            if (command.contains("bye")) {
                break;
            }
            Command dummy = Parser.parse(command);
            dummy.execute(clientList, ui);
        }
    }


}
