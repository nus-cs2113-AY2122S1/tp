package seedu.traveller.objects;

import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldmap.Country;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Each TripsList will store an ArrayList of this class.
 * Represents 1 trip plan created by the user.
 */
public class Trip {
    private static final Logger logger = Logger.getLogger(TripsList.class.getName());
    private String tripName;
    private String startCountryCode;
    private String endCountryCode;
    private List<Country> path;
    private List<Double> time;
    private final DaysList daysList;

    public Trip(String tripName, String startCountryCode, String endCountryCode,
                List<Country> path, List<Double> time) {
        logger.setLevel(Level.INFO);
        assert !tripName.equals("all") : "'all' is an invalid tripName.";
        assert !tripName.equals("") : "'' is an invalid tripName.";
        this.tripName = tripName;
        this.startCountryCode = startCountryCode;
        this.endCountryCode = endCountryCode;
        this.path = path;
        this.time = time;
        this.daysList = new DaysList();
        logger.log(Level.INFO, "Trip created with details: \n" + this);
    }

    @Override
    public String toString() {
        return "\t\tTripName: " + getTripName()
                + "\n\t\t\t Origin: " + getStartCountryCode()
                + "\n\t\t\t Destination: " + getEndCountryCode()
                + "\n\t\t\t Path: " + getPath()
                + "\n\t\t\t Time: " + getTime()
                + "\n\t\t\t Days: " + daysList;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String newTripName) {
        this.tripName = newTripName;
    }

    public String getStartCountryCode() {
        return startCountryCode;
    }

    public void setStartCountryCode(String startCountryCode) {
        this.startCountryCode = startCountryCode;
    }

    public String getEndCountryCode() {
        return endCountryCode;
    }

    public void setEndCountryCode(String endCountryCode) {
        this.endCountryCode = endCountryCode;
    }

    public List<Country> getPath() {
        return path;
    }

    public void setPath(List<Country> path) {
        this.path = path;
    }

    public List<Double> getTime() {
        return time;
    }

    public void setTime(List<Double> time) {
        this.time = time;
    }

    public Day getDay(int dayNumber) throws TravellerException {
        return daysList.getDay(dayNumber);
    }

    public void addDay() throws TravellerException {
        daysList.addDay();
    }

    public void deleteDay(int dayNumber) throws TravellerException {
        daysList.deleteDay(dayNumber);
    }

    public String getSaveTrip() {
        return "new " + getTripName() + " /from " + getStartCountryCode() + " /to " + getEndCountryCode() + "\n";
    }

    public int getDaySize() {
        return daysList.getSize();
    }

    public String getSaveDay() {
        String saveDataForDays = "";
        if (getDaySize() > 0) {
            saveDataForDays = "add-day " + getTripName() + " /day " + getDaySize() + "\n";
        }
        return saveDataForDays;
    }

    public String getSaveItem() throws TravellerException {
        String saveDataForItems = "";
        for (int i = 0; i < getDaySize(); i++) {
            Day current = daysList.getDay(i);
            if (current.getItemsListSize() > 0) {
                for (int j = 0; j < current.getItemsListSize(); j++) {
                    saveDataForItems += "add-item " + getTripName() + " /day " + i + current.itemsList.getItemInfo(j);
                }
            }
        }
        return saveDataForItems;
    }
}