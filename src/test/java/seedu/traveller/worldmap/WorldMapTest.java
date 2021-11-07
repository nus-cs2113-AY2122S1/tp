package seedu.traveller.worldmap;

import org.junit.jupiter.api.Test;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WorldMapTest {
    public WorldMapTest() {
        WorldMap.initWorldMap();
    }

    @Test
    public void getCountry_sin_success() throws EmptyVertexException {
        Country countrySin = WorldMap.getValidCountry("SIN");
        assertEquals(new Country("SIN", 0).toString(), countrySin.toString());
    }

    @Test
    public void calcMinDistance_sinMly_success() throws EmptyVertexException {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);

        List<Country> path = new ArrayList<>();
        path.add(countrySin);
        path.add(countryMly);

        List<Double> distances = new ArrayList<>();
        distances.add(1.0);

        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryMly, path, distances);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinTime("SIN", "MLY").toString());
    }
    
    @Test
    public void calcMinCost_sinMly_success() throws EmptyVertexException {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);

        List<Country> path = new ArrayList<>();
        path.add(countrySin);
        path.add(countryMly);

        List<Double> distances = new ArrayList<>();
        distances.add(100.0);

        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryMly, path, distances);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinCost("SIN", "MLY").toString());
    }

    @Test
    public void calcMinDistance_sinJpn_success() throws EmptyVertexException {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);
        Country countryJpn = new Country("JPN", 3);

        List<Country> path = new ArrayList<>();
        path.add(countrySin);
        path.add(countryMly);
        path.add(countryJpn);

        List<Double> distances = new ArrayList<>();
        distances.add(1.0);
        distances.add(5.0);

        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryJpn, path, distances);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinTime("SIN", "JPN").toString());
    }

    @Test
    public void calcMinDistance_jpnSin_success() throws EmptyVertexException {
        Country countrySin = new Country("SIN", 0);
        Country countryMly = new Country("MLY", 1);
        Country countryJpn = new Country("JPN", 3);

        List<Country> path = new ArrayList<>();

        path.add(countryJpn);
        path.add(countryMly);
        path.add(countrySin);

        List<Double> distances = new ArrayList<>();
        distances.add(5.0);
        distances.add(1.0);

        MinCalcResult minCalcResult = new MinCalcResult(countryJpn, countrySin, path, distances);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinTime("JPN", "SIN").toString());

    }

    @Test
    public void updateMinDistance_jpnSin_success() throws EmptyVertexException {
        WorldMap.printWorld();
        Country countrySin = WorldMap.getValidCountry("SIN");
        Country countryJpn = WorldMap.getValidCountry("JPN");

        List<Country> path = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        path.add(countryJpn);
        path.add(countrySin);

        distances.add(4.0);

        WorldMap.graphList.modifyEdge(4.0, countryJpn, countrySin);

        MinCalcResult minCalcResult = new MinCalcResult(countryJpn, countrySin, path, distances);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinTime("JPN", "SIN").toString());
    }

    @Test
    public void calcMinCost_sinJpn_success() throws EmptyVertexException {
        Country countrySin = new Country("SIN", 0);
        Country countryChn = new Country("CHN", 2);
        Country countryJpn = new Country("JPN", 3);

        List<Country> path = new ArrayList<>();
        path.add(countrySin);
        path.add(countryChn);
        path.add(countryJpn);

        List<Double> costs = new ArrayList<>();
        costs.add(150.0);
        costs.add(400.0);

        MinCalcResult minCalcResult = new MinCalcResult(countrySin, countryJpn, path, costs);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinCost("SIN", "JPN").toString());
    }

    @Test
    public void calcMinCost_jpnSin_success() throws EmptyVertexException {
        Country countrySin = new Country("SIN", 0);
        Country countryChn = new Country("CHN", 2);
        Country countryJpn = new Country("JPN", 3);

        List<Country> path = new ArrayList<>();
        path.add(countryJpn);
        path.add(countryChn);
        path.add(countrySin);

        List<Double> costs = new ArrayList<>();
        costs.add(400.0);
        costs.add(150.0);

        MinCalcResult minCalcResult = new MinCalcResult(countryJpn, countrySin, path, costs);

        assertEquals(minCalcResult.toString(),
                WorldMap.calcMinCost("JPN", "SIN").toString());
    }


}
