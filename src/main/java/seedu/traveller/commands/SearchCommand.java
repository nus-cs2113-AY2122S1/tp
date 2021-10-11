package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.worldMap.MinCalcResult;
import seedu.traveller.worldMap.WorldMap;

import java.util.List;


public class SearchCommand extends Command {
    private final String startCountry;
    private final String endCountry;

    public SearchCommand(String startCountry, String endCountry) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
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
