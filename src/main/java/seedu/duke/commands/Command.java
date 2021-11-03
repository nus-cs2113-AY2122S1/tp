package seedu.duke.commands;

import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

/**
 * Abstract command that outlines the 'execute' and 'isExit' method.
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

    public void setData(ClientList clients, FlightList flights, TourList tours,
                        ClientPackageList clientPackages, Ui ui) {
        this.clients = clients;
        this.flights = flights;
        this.tours = tours;
        this.clientPackages = clientPackages;
        this.ui = ui;
    }

    /**
     * Function that controls the exit condition of the loop.
     *
     * @return the exit condition from the loop
     */
    public boolean isExit() {
        return false;
    }
}
