package seedu.traveller.objects;

import seedu.traveller.exceptions.DayNotFoundException;
import seedu.traveller.exceptions.MaxNumberOfDaysAllowedExceededException;
import seedu.traveller.exceptions.TravellerException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a list of days in a trip.
 * Each <code>Trip</code> will have 1 <code>DaysList</code>.
 */
public class DaysList {
    private static final Logger logger = Logger.getLogger(TripsList.class.getName());
    private final ArrayList<Day> days;
    private final int maxDaysAllowed = 30;

    public DaysList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a days list");
        this.days = new ArrayList<>();
    }

    public void addDay() throws TravellerException {
        logger.log(Level.INFO, "Added a day to daysList");
        if (getSize() >= getMaxDaysAllowed()) {
            throw new MaxNumberOfDaysAllowedExceededException();
        }
        Day newDay = new Day();
        days.add(newDay);
    }

    public Day getDay(int dayNumber) throws TravellerException {
        Day day;
        try {
            day = days.get(dayNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DayNotFoundException(dayNumber);
        }
        return day;
    }

    public void deleteDay(int dayNumber) throws TravellerException {
        logger.log(Level.INFO, "Deleted a day of index " + dayNumber + " from daysList");
        try {
            days.remove(dayNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DayNotFoundException(dayNumber);
        }
    }

    public int getMaxDaysAllowed() {
        return maxDaysAllowed;
    }

    public int getSize() {
        return days.size();
    }

    /**
     * Used by the <code>Ui</code> to print out a <code>DaysList</code>.
     * @return <code>String</code> of the <code>Day</code> and its <code>ItemsList</code> contents.
     */
    @Override
    public String toString() {
        StringBuilder daysListString = new StringBuilder();
        for (int dayNumber = 0; dayNumber < getSize(); dayNumber++) {
            String dayHeader = "\n\t\t\t\tDay " + dayNumber + ": ";
            daysListString.append(dayHeader);
            daysListString.append(days.get(dayNumber).toString());
        }
        return daysListString.toString();
    }
}
