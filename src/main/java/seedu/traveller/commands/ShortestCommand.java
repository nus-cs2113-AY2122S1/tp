package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jach23
public class ShortestCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShortestCommand.class.getName());
    private final String startCountry;
    private final String endCountry;
    private final String timeOrCost;

    public ShortestCommand(String timeOrCost, String startCountry, String endCountry) {
        logger.setLevel(Level.INFO);
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.timeOrCost = timeOrCost;
        logger.log(Level.INFO, "Created an shortest command: \n" + this);
    }

    public String getStartCountry() {
        return this.startCountry;
    }

    public String getEndCountry() {
        return this.endCountry;
    }

    public String getTimeOrCost() {
        return this.timeOrCost;
    }

    @Override
    public String toString() {
        return "Shortest command: "
                + "\n\ttimeOrCost: " + timeOrCost
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry;
    }

    public void execute(TripsList tripsList, Ui ui) {
        MinCalcResult result;

        assert Objects.equals(this.timeOrCost,"time") || Objects.equals(this.timeOrCost,"cost");


        if (Objects.equals(this.timeOrCost, "time")) {
            result = WorldMap.calcMinTime(this.startCountry, this.endCountry);
        } else {
            WorldMap.altWorldMap();
            result = WorldMap.calcMinCost(this.startCountry, this.endCountry);
        }

        assert result != null;
        if (result.getError() == 1) {
            return;
        }
        List<Double> time = result.getTime();

        double sum = 0.0;
        for (double d : time) {
            sum += d;
        }
        assert sum >= 0 : "The distance should be more than or equal to 0.";

        if (Objects.equals(this.timeOrCost, "time")) {
            ui.printShortestTime(this.startCountry, this.endCountry, sum, time);
        } else {
            ui.printShortestCost(this.startCountry, this.endCountry, sum, time);
        }

    }

}
