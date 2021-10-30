package seedu.traveller.objects;

import org.junit.jupiter.api.Test;
import seedu.traveller.exceptions.DayNotFoundException;
import seedu.traveller.exceptions.TravellerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author Uxinnn
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
        try {
            daysList.deleteDay(1);
        } catch (TravellerException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, daysList.getSize());
    }

    @Test
    public void deleteDay_exceptionThrown() {
        assertThrows(DayNotFoundException.class, () -> {
            daysList.deleteDay(10);
        });
        assertThrows(DayNotFoundException.class, () -> {
            daysList.deleteDay(-1);
        });
    }

    @Test
    public void getDay_exceptionThrown() {
        assertThrows(DayNotFoundException.class, () -> {
            daysList.getDay(10);
        });
        assertThrows(DayNotFoundException.class, () -> {
            daysList.getDay(-1);
        });
    }

}
