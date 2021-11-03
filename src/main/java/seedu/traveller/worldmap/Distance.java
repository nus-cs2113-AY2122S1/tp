package seedu.traveller.worldmap;

//@@author Jach23
public class Distance {
    private double distance;
    private Country startCountry;
    private Country endCountry;

    public Distance(double distance, Country startCountry, Country endCountry) {
        this.distance = distance;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Country getStartCountry() {
        return this.startCountry;
    }

    public void setStartCountry(Country startCountry) {
        this.startCountry = startCountry;
    }

    public Country getEndCountry() {
        return this.endCountry;
    }

    public void setEndCountry(Country endCountry) {
        this.endCountry = endCountry;
    }
}