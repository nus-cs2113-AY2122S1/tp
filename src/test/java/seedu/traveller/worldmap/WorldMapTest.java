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
        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinDistance("SIN", "MLY").toString());
    }

    @Test
    public void getCountry_sin_success() {
        Country countrySin = WorldMap.getCountry("SIN");
        assertEquals(new Country("SIN", 0).toString(), countrySin.toString());
    }

    @Test
    public void calcMinDistance_sinLax_success() {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);
        Country countryJpn = new Country("JPN", 3);

        List<Country> path = new ArrayList<>();
        path.add(countrySin);
        path.add(countryMly);
        path.add(countryJpn);

        List<Double> distances = new ArrayList<>();
        distances.add(1.0);
        distances.add(6.0);

        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryJpn, path, distances);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinDistance("SIN", "JPN").toString());
    }

    /*
    @Test
    public void calcMinDistance_laxSin_success() {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);
        Country countryJpn = new Country("JPN", 3);

        List<Country> path = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        path.add(countryJpn);
        path.add(countryMly);
        path.add(countrySin);

        distances.add(6.0);
        distances.add(1.0);

        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryJpn, path, distances);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinDistance("JPN", "SIN").toString());

    }

    @Test
    public void updateMinDistance_sinLax_success() {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);
        Country countryJpn = new Country("JPN", 3);

        List<Country> path = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        path.add(countryJpn);
        path.add(countryMly);
        path.add(countrySin);

        distances.add(6.0);
        distances.add(1.0);

        Country.updateNeighbour(999.0, countrySin, countryJpn);

        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryJpn, path, distances);

        assertEquals(minCalcResult.toString(),
        WorldMap.calcMinDistance("JPN", "SIN").toString());

      }
      */



}
