package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.EmptyVertexException;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TripsList tripsList, Ui ui) throws TravellerException, EmptyVertexException;

    public boolean getExit() {
        return isExit;
    }

    public void setExit() {
        this.isExit = true;
    }
}
