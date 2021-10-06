package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

abstract public class Command {
    private boolean isExit = false;

    public abstract void execute(TripsList tripsList, Ui ui) throws TravellerException;

    public boolean getExit() {
        return isExit;
    }

    public void setExit() {
        this.isExit = true;
    }
}
