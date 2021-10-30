package seedu.traveller.objects;

import org.junit.jupiter.api.Test;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;
import seedu.traveller.worldmap.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author Uxinnn
public class TripsListTest {
    private final TripsList tripsList = new TripsList();
    private final Trip tripSinMly;
    private final Trip tripSkrJpn;

    public TripsListTest() throws TravellerException {
        List<Country> pathSinMly = new ArrayList<>();
        List<Double> distancesSinMly = new ArrayList<>();
        pathSinMly.add(new Country("SIN", 0));
        pathSinMly.add(new Country("MLY", 1));
        distancesSinMly.add(1.0);
        this.tripSinMly = new Trip("tripSinMly", "SIN", "MLY", pathSinMly, distancesSinMly);

        List<Country> pathSkrJpn = new ArrayList<>();
        List<Double> distancesSkrJpn = new ArrayList<>();
        pathSkrJpn.add(new Country("SKR", 2));
        pathSkrJpn.add(new Country("JPN", 3));
        distancesSkrJpn.add(1.0);
        this.tripSkrJpn = new Trip("tripSkrJpn", "SKR", "JPN", pathSkrJpn, distancesSkrJpn);

        this.tripsList.addTrip(this.tripSinMly);
        this.tripsList.addTrip(this.tripSkrJpn);
    }

    @Test
    public void getTrip_success() throws TravellerException {
        assertEquals(this.tripSinMly, this.tripsList.getTrip(0));
        assertEquals(this.tripSkrJpn, this.tripsList.getTrip(1));
    }

    @Test
    public void getTripIndex_success() throws TravellerException {
        assertEquals(0, tripsList.getTripIndex("tripSinMly"));
        assertEquals(1, tripsList.getTripIndex("tripSkrJpn"));
    }

    @Test
    public void getSize_success() {
        assertEquals(2, this.tripsList.getSize());
    }

    @Test
    public void addTrip_exceptionThrown() {
        assertThrows(AssertionError.class, () -> {
            tripsList.addTrip(this.tripSinMly);
        });
    }

    @Test
    public void getTrip_exceptionThrown() {
        assertThrows(TripNotFoundException.class, () -> {
            tripsList.getTrip("abc");
        });
    }

    @Test
    public void deleteTrip_exceptionThrown() {
        assertThrows(AssertionError.class, () -> {
            tripsList.deleteTrip(10);
        });
        assertThrows(AssertionError.class, () -> {
            tripsList.deleteTrip(-1);
        });
    }
}
