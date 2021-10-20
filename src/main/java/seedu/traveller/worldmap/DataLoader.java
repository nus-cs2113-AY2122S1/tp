package seedu.traveller.worldmap;

import seedu.traveller.worldmap.exceptions.WorldMapException;
import seedu.traveller.worldmap.exceptions.FlightDataNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataLoader {
    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());
    private final int numberOfCities = 5;
    private final String filePath = "./flightData/flights.txt";
    private final String separator = "\\|";

    protected void loadCountries(String[] countryCodes, GraphList graphList) {
        logger.log(Level.INFO, "Loading countries: " + Arrays.toString(countryCodes));
        for (int j = 0; j < numberOfCities; j++) {
            String countryCode = countryCodes[j];
            int countryIndex = j + 1;
            Country v = new Country(countryCode, countryIndex);
            graphList.addVertex(v);
        }
    }

    protected void loadDistances(String[] rawDistances, GraphList graphList) {
        logger.log(Level.INFO, "Loading distances: " + Arrays.toString(rawDistances));
        for (int k = 0; k < rawDistances.length; k++) {
            double distance = Double.parseDouble(rawDistances[k]);
            Country sourceCountry = graphList.getVertexArray().get(k);
            Country destinationCountry = graphList.getVertexArray().get(rawDistances.length);
            if (distance != 0) {
                graphList.createEdge(distance, destinationCountry, sourceCountry);
            }
        }
    }

    public GraphList readData() throws WorldMapException {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Reading data from " + filePath);
        File data = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(data);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Flight data cannot be found.");
            throw new FlightDataNotFoundException();
        }
        GraphList graphList = new GraphList();
        String[] rawInput;
        while (scanner.hasNext()) {
            for (int i = 0; i < numberOfCities; i++) {
                rawInput = scanner.nextLine().split(separator);

                // Reading first line of country codes
                if (i == 0) {
                    loadCountries(rawInput, graphList);
                    continue;
                }
                loadDistances(rawInput, graphList);
            }
        }
        return graphList;
    }
}
