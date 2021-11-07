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
    private static Ui ui;
    private static ClientList clients;
    private static TourList tours;
    private static FlightList flights;
    private static ClientPackageList clientPackages;

    private static ClientPackageStorage clientPackageStorage;
    private static ClientStorage clientStorage;
    private static TourStorage tourStorage;
    private static FlightStorage flightStorage;

    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public TourPlanner() {
    }

    /**
     * Main method of TourPlanner.
     * Initialises Ui and ClientList objects.
     * Reads, parses and executes command from user's input until exit condition is met.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        loadStorage();
        ui.showWelcome();
        runCommandLoopUntilExitCommand();
    }

    /**
     * Populates the ClientList, TourList, FlightList and ClientPackageList from previously stored files.
     */
    private static void loadStorage() {
        try {
            initialize();
            loadClientTourFlight();
            loadClientPackage();
        } catch (TourPlannerException e) {
            ui.showFileError();
            System.exit(0);
        }
    }

    private static void initialize() throws TourPlannerException {
        ui = new Ui();
        clientPackageStorage = new ClientPackageStorage();
        clientStorage = new ClientStorage();
        tourStorage = new TourStorage();
        flightStorage = new FlightStorage();
    }

    /**
     * Populates the ClientList, TourList, FlightList from previously stored files.
     *
     * @throws TourPlannerException if there are IOException, FileNotFoundException for the storage file
     */
    private static void loadClientTourFlight() throws TourPlannerException {
        clientStorage.loadFile();
        clients = clientStorage.getClients();
        tourStorage.loadFile();
        tours = tourStorage.getTours();
        flightStorage.loadFile();
        flights = flightStorage.getFlights();
    }

    /**
     * Populates ClientPackageList from previously stored files.
     *
     * @throws TourPlannerException if there are IOException, FileNotFoundException for the storage file
     */
    private static void loadClientPackage() throws TourPlannerException {
        clientPackageStorage.loadFile(clients, tours, flights, ui);
        clientPackages = clientPackageStorage.getClientPackages();
    }

    /**
     * Loop that reads command from Ui, parses command and executes command.
     * Loop ends when ByeCommand is called and isExit is set to true.
     */
    private static void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            try {
                Command specificCommand = Parser.parse(command);
                specificCommand.setData(clients, flights, tours, clientPackages, ui);
                specificCommand.execute();
                isExit = specificCommand.isExit();
            } catch (TourPlannerException e) {
                System.out.println(e.getMessage());
                logr.log(Level.SEVERE, e.getMessage());
            } finally {
                ui.showLine();
                saveToStorage();
            }
        }
    }

    /**
     * Saves the ClientList, FlightList, TourList and ClientPackageList to text files in the data folder.
     */
    private static void saveToStorage() {
        clientStorage.saveFile();
        flightStorage.saveFile();
        tourStorage.saveFile();
        clientPackageStorage.saveFile();
    }

}