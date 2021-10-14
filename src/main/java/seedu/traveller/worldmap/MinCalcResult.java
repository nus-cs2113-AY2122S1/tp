package seedu.traveller.worldmap;

import java.util.List;


public class MinCalcResult {
    Country startCountry;
    Country endCountry;
    List<Country> path;
    List<Double> distances;

    public MinCalcResult(Country startCountry, Country endCountry, List<Country> path, List<Double> distances) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.path = path;
        this.distances = distances;
    }

    public Country getStartCountry() {
        return this.startCountry;
    }

    public Country getEndCountry() {
        return endCountry;
    }

    public List<Country> getPath() {
        return path;
    }

    public List<Double> getDistances() {
        return distances;
    }

    @Override
    public String toString() {
        return "minCalcResult:\n\t" + getStartCountry() + " to " + getEndCountry()
                + "\n\t" + getPath() + "\n\t" + getDistances();
    }
}
