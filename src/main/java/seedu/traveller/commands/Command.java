package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;


//@@author Uxinnn
/**
 * Abstract parent class of all <code>Command</code> class objects.
 */
public abstract class Command {

    /**
     * Will only be set to true when the application is closed by the user.
     */
    private boolean isExit = false;

    /**
     * Used to execute the action of the specific command.
     * @param tripsList The <code>TripsList</code> that the command will be executed on.
     * @param ui The <code>Ui</code> that will be used to print out the outcome of the execution.
     * @throws TravellerException To be thrown if an error is encountered during the execution.
     */
    public abstract void execute(TripsList tripsList, Ui ui) throws TravellerException, EmptyVertexException;

    public boolean getExit() {
        return this.isExit;
    }

    public void setExit() {
        this.isExit = true;
    }
}
