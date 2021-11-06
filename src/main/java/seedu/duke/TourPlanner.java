package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;
import seedu.duke.storage.ClientPackageStorage;
import seedu.duke.storage.ClientStorage;
import seedu.duke.storage.FlightStorage;
import seedu.duke.storage.TourStorage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main entry-point of the TourPlanner application.
 * Initialises the application and starts interaction with application user.
 */
public class TourPlanner {

    public TourPlanner() {
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
        ClientPackageStorage clientPackageStorage = null;
        ClientStorage clientStorage = null;
        TourStorage tourStorage = null;
        FlightStorage flightStorage = null;

        try {
            clientPackageStorage = new ClientPackageStorage();
            clientStorage = new ClientStorage();
            tourStorage = new TourStorage();
            flightStorage = new FlightStorage();

            clientPackageStorage.loadFile();
            clientStorage.loadFile();
            tourStorage.loadFile();
            flightStorage.loadFile();

        } catch (TourPlannerException e) {
            ui.show(e.getMessage());
        }

        TourList tours = tourStorage.getTours();
        FlightList flights = flightStorage.getFlights();
        ClientList clients = clientStorage.getClients();
        ClientPackageList clientPackages = clientPackageStorage.getClientPackages();
        ui.showWelcome();
        boolean isExit = false;
        String command;

        while (!isExit) {
            command = ui.readCommand();
            try {
                Command specificCommand = Parser.parse(command);
                specificCommand.setData(clients, flights, tours, clientPackages, ui);
                specificCommand.execute();
                isExit = specificCommand.isExit();
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println();
                logr.log(Level.SEVERE, "Null pointer or Number format error occurred");
            } catch (TourPlannerException e) {
                System.out.println(e.getMessage());
                logr.log(Level.SEVERE, e.getMessage());
            } finally {
                ui.showLine();
                clientStorage.saveFile();
                flightStorage.saveFile();
                tourStorage.saveFile();
                clientPackageStorage.saveFile();
            }
        }
    }
}