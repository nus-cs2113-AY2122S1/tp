package seedu.traveller.worldmap;

import org.junit.jupiter.api.Test;
import seedu.traveller.worldmap.Country;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WorldMapTest {
    public WorldMapTest() {
        WorldMap.initWorldMap();
    }

    @Test
    public void calcMinDistance_sinMly_success() {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);
        List<Country> path = new ArrayList<>();
        List<Double> distances = new ArrayList<>();
        path.add(countrySin);
        path.add(countryMly);
        distances.add(1.0);
        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryMly, path, distances);
        assertEquals(minCalcResult.toString(), WorldMap.calcMinDistance("SIN", "MLY").toString());
    }

    @Test
    public void getCountry_sin_success() {
        Country countrySin = WorldMap.getCountry("SIN");
        assertEquals(new Country("SIN", 0).toString(), countrySin.toString());
    }
}
