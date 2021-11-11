package seedu.traveller.objects;

import org.junit.jupiter.api.Test;
import seedu.traveller.exceptions.DayNotFoundException;
import seedu.traveller.worldmap.Country;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author Uxinnn
class TripTest {
    private final Trip trip;

    public TripTest() {
        ArrayList<Country> path = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();
        path.add(new Country("SIN", 0));
        path.add(new Country("MLY", 1));
        distances.add(1.0);
        this.trip = new Trip("trip1", "SIN", "MLY", path, distances);
    }

    @Test
    public void testStringConvention() {
        final String expectedOutput = "\t\tTripName: trip1"
                + "\n\t\t\t Origin: SIN"
                + "\n\t\t\t Destination: MLY"
                + "\n\t\t\t Path: [SIN, MLY]"
                + "\n\t\t\t Time: [1.0]"
                + "\n\t\t\t Days: ";
        assertEquals(expectedOutput, trip.toString());
    }

    @Test
    public void getDay_exceptionThrown() {
        assertThrows(DayNotFoundException.class, () -> {
            trip.getDay(10);
        });
        assertThrows(DayNotFoundException.class, () -> {
            trip.getDay(-1);
        });
    }

    @Test
    public void deleteDay_exceptionThrown() {
        assertThrows(DayNotFoundException.class, () -> {
            trip.deleteDay(10);
        });
        assertThrows(DayNotFoundException.class, () -> {
            trip.deleteDay(-1);
        });
    }
}
