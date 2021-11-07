package seedu.duke.commands;

import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

/**
 * Abstract executable command.
 */
public abstract class Command {
    protected ClientList clients;
    protected FlightList flights;
    protected TourList tours;
    protected ClientPackageList clientPackages;
    protected Ui ui;

    /**
     * Executes the specific command depending on the command constructed.
     */
    public abstract void execute() throws TourPlannerException;

    /**
     * Setter for ClientList, FlightList, TourList, ClientPackageList and Ui
     * for subclasses of Command to access when executing their command.
     *
     * @param clients list of all clients
     * @param flights list of all flights
     * @param tours list of all tours
     * @param clientPackages list of all client packages
     * @param ui user interface in charge of reading user input and showing messages to user
     */
    public void setData(ClientList clients, FlightList flights, TourList tours,
                        ClientPackageList clientPackages, Ui ui) {
        this.clients = clients;
        this.flights = flights;
        this.tours = tours;
        this.clientPackages = clientPackages;
        this.ui = ui;
    }

    /**
     * Function that controls the exit condition of the loop in TourPlanner class.
     *
     * @return the exit condition from the loop
     */
    public boolean isExit() {
        return false;
    }
}
