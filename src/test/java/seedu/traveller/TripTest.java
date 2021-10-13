package seedu.traveller;

import org.junit.jupiter.api.Test;
import seedu.traveller.worldmap.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripTest {
    @Test
    public void testStringConvention() {
        List<Country> path = new ArrayList<>();
        List<Double> distances = new ArrayList<>();
        path.add(new Country("SIN", 0));
        path.add(new Country("MLY", 1));
        distances.add(1.0);
        assertEquals("\t\tTripName: trip1\n\t\t\t Origin: SIN\n\t\t\t "
                        + "Destination: MLY\n\t\t\t Path: [SIN, MLY]",
                new Trip("trip1", "SIN", "MLY", path, distances).toString());
    }
}
