package seedu.traveller.worldmap;

import seedu.traveller.worldmap.exceptions.WorldMapException;
import seedu.traveller.worldmap.exceptions.FlightDataNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Loader {
    private final int numberOfCities = 5;
    private final String filePath = "./flightData/flights.txt";
    private final String separator = "\\|";
    private final GraphList graphList = new GraphList();

    protected void loadCountries(String[] countryCodes) {
        for (int j = 0; j < numberOfCities; j++) {
            String countryCode = countryCodes[j];
            int countryIndex = j + 1;
            Country v = new Country(countryCode, countryIndex);
            this.graphList.addVertex(v);
        }
    }

    protected void loadDistances(String[] rawDistances) {
        for (int k = 0; k < rawDistances.length; k++) {
            double distance = Double.parseDouble(rawDistances[k]);
            Country sourceCountry = this.graphList.getVertexArray().get(k);
            Country destinationCountry = this.graphList.getVertexArray().get(rawDistances.length);
            if (distance != 0) {
                this.graphList.createEdge(distance, destinationCountry, sourceCountry);
            }
        }
    }

    public GraphList readData() throws WorldMapException {
        File data = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(data);
        } catch (FileNotFoundException e) {
            throw new FlightDataNotFoundException();
        }
        String[] rawInput;
        while (s.hasNext()) {
            for (int i = 0; i < numberOfCities; i++) {
                rawInput = s.nextLine().split(separator);

                // Reading first line of country codes
                if (i == 0) {
                    loadCountries(rawInput);
                    continue;
                }
                loadDistances(rawInput);
            }
        }
        return this.graphList;
    }
}
