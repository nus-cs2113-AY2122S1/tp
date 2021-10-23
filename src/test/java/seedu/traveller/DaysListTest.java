package seedu.traveller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DaysListTest {
    private final DaysList daysList;

    public DaysListTest() {
        this.daysList = new DaysList();
    }

    @Test
    public void addDay_success() {
        daysList.addDay();
        daysList.addDay();
        assertEquals(2, daysList.getSize());
    }

    @Test
    public void deleteDay_success() {
        daysList.addDay();
        daysList.addDay();
        daysList.deleteDay(1);
        assertEquals(1, daysList.getSize());
    }
}
