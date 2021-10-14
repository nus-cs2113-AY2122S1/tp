package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TourPlanner {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public TourPlanner() {
        ;
    }
    
    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
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
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println();
                logr.log(Level.SEVERE, "Null pointer or Number format error occur");
            } catch (TourPlannerException e) {
                System.out.println(e.getMessage());
                logr.log(Level.SEVERE,e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


}
