package seedu.traveller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DaysList {
    private static final Logger logger = Logger.getLogger(TripsList.class.getName());
    private final ArrayList<Day> days;

    public DaysList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a days list");
        this.days = new ArrayList<>();
    }

    public void addDay() {
        Day newDay = new Day();
        days.add(newDay);
    }

    public Day getDay(int i) {
        return days.get(i);
    }

    public void deleteDay(int i) {
        days.remove(i);
    }

    public int getSize() {
        return days.size();
    }

    @Override
    public String toString() {
        StringBuilder daysListString = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            String dayHeader = "\n\t\t\t\tDay " + i + ": ";
            daysListString.append(dayHeader);
            daysListString.append(getDay(i).toString());
        }
        return daysListString.toString();
    }
}
