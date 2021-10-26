package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.worldmap.WorldMap;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class EditMapCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditMapCommand.class.getName());
    private final String startCountry;
    private final String endCountry;
    private final double dist;

    public EditMapCommand(String startCountry, String endCountry, Double dist) {
        logger.setLevel(Level.INFO);
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.dist = dist;
        logger.log(Level.INFO, "Created an edit world map command: \n" + this);
    }

    public String getStartCountry() {
        return this.startCountry;
    }

    public String getEndCountry() {
        return this.endCountry;
    }

    public Double getDist() {
        return this.dist;
    }

    @Override
    public String toString() {
        return "Edit map command: "
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry
                + "\n\tdistance: " + dist;
    }

    public void execute(TripsList tripsList, Ui ui) throws EmptyVertexException {
        WorldMap.editMap(dist, this.startCountry, this.endCountry);
        ui.printEditMap(this.startCountry, this.endCountry, dist);
    }
}
