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
        boolean isExit = false;
        String command;
        ClientList clientList = new ClientList();
        while (!isExit) {
            command = ui.readCommand();
            try {
                Command dummy = Parser.parse(command);
                dummy.execute(clientList, ui);
                isExit = dummy.isExit();
//                if (command.contains("bye")) {
//                    break;
//                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println();
            } finally {
                ui.showLine();
            }
        }
    }


}
