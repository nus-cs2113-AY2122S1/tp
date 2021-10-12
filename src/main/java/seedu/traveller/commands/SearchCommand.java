package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;

import java.util.List;


public class SearchCommand extends Command {
    private final String startCountry;
    private final String endCountry;

    public SearchCommand(String startCountry, String endCountry) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    public String getStartCountry() {
        return this.startCountry;
    }

    public String getEndCountry() {
        return this.endCountry;
    }

    @Override
    public String toString() {
        return "search: " + getStartCountry() + " to " + getEndCountry();
    }

    public void execute(TripsList tripsList, Ui ui) {
        MinCalcResult result = WorldMap.calcMinDistance(this.startCountry, this.endCountry);
        List<Double> distances = result.getDistances();
        double curr;
        double sum = 0.0;
        for (double d : distances) {  // TODO: CHECK THIS, LOOKS V SUS
            curr = d - sum;
            sum += curr;
        }
        ui.printSearch(this.startCountry, this.endCountry, sum);
    }
}
