package seedu.traveller.worldmap;

import java.util.List;

//@@author Jach23
public class MinCalcResult {
    Country startCountry;
    Country endCountry;
    List<Country> path;
    List<Double> distances;
    int error;

    public MinCalcResult(Country startCountry, Country endCountry, List<Country> path, List<Double> distances) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.path = path;
        this.distances = distances;
        this.error = 0;
    }

    public void setError() {
        error = 1;
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

    public int getError() {
        return error;
    }

    @Override
    public String toString() {
        return "minCalcResult:\n\t" + getStartCountry() + " to " + getEndCountry()
                + "\n\t" + getPath() + "\n\t" + getDistances();
    }
}
