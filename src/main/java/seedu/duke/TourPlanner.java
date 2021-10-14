package seedu.duke;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Main entry-point of the TourPlanner application.
 * Initialises the application and starts interaction with application user.
 */
public class TourPlanner {

    public TourPlanner() {
        ;
    }

    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Main method of TourPlanner.
     * Initialises Ui and ClientList objects.
     * Reads, parses and executes command from user's input until exit condition is met.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        boolean isExit = false;
        String command;
        ClientList clientList = new ClientList();
        while (!isExit) {
            command = ui.readCommand();
            try {
                Command specificCommand = Parser.parse(command);
                specificCommand.execute(clientList, ui);
                isExit = specificCommand.isExit();
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println();
                logr.log(Level.SEVERE, "Null pointer or Number format error occur");
            } catch (TourPlannerException e) {
                System.out.println(e.getMessage());
                logr.log(Level.SEVERE, e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
