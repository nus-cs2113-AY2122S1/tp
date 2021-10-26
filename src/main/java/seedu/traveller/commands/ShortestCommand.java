package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ShortestCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShortestCommand.class.getName());
    private final String startCountry;
    private final String endCountry;
    private final String distOrCost;

    public ShortestCommand(String distOrCost, String startCountry, String endCountry) {
        logger.setLevel(Level.INFO);
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.distOrCost = distOrCost;
        logger.log(Level.INFO, "Created an search command: \n" + this);
    }

    public String getStartCountry() {
        return this.startCountry;
    }

    public String getEndCountry() {
        return this.endCountry;
    }

    public String getDistOrCost() {
        return this.distOrCost;
    }

    @Override
    public String toString() {
        return "Shortest command: "
                + "\n\tdistOrCost: " + distOrCost
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry;
    }

    public void execute(TripsList tripsList, Ui ui) {
        MinCalcResult result;

        assert Objects.equals(this.distOrCost,"dist") || Objects.equals(this.distOrCost,"cost");
        if (Objects.equals(this.distOrCost, "dist")) {
            result = WorldMap.calcMinDistance(this.startCountry, this.endCountry);
        } else {
            WorldMap.altWorldMap();
            result = WorldMap.calcMinCost(this.startCountry, this.endCountry);
        }

        assert result != null;
        if (result.getError() == 1) {
            return;
        }
        List<Double> distances = result.getDistances();

        double sum = 0.0;
        for (double d : distances) {
            sum += d;
        }
        assert sum >= 0 : "The distance should be more than or equal to 0.";

        if (Objects.equals(this.distOrCost, "dist")) {
            ui.printShortestDist(this.startCountry, this.endCountry, sum, distances);
        } else {
            ui.printShortestCost(this.startCountry, this.endCountry, sum, distances);
        }

    }

}
