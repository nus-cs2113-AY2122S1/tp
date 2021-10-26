package seedu.traveller.worldmap;

import seedu.traveller.worldmap.exceptions.IllegalFlightFileException;
import seedu.traveller.worldmap.exceptions.WorldMapException;
import seedu.traveller.worldmap.exceptions.FlightDataNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author gavienwz
public class DataLoader {
    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());
    private final int numberOfCountries = 5;
    private final String filePath = "./flightData/dist.txt";
    private final String filePath2 = "./flightData/cost.txt";
    private final String separator = "\\|";

    protected void loadCountries(String[] countryCodes, GraphList graphList) throws IllegalFlightFileException {
        logger.log(Level.INFO, "Loading countries: " + Arrays.toString(countryCodes));
        assert countryCodes.length == numberOfCountries;
        for (int j = 0; j < numberOfCountries; j++) {
            String countryCode = countryCodes[j];
            if (countryCode.length() != 3) {
                throw new IllegalFlightFileException();
            }
            assert countryCode.length() == 3;
            int countryIndex = j + 1;
            Country v = new Country(countryCode, countryIndex);
            graphList.addVertex(v);
        }
    }

    protected void loadDistances(String[] rawDistances, GraphList graphList) throws IllegalFlightFileException {
        logger.log(Level.INFO, "Loading distances: " + Arrays.toString(rawDistances));
        for (int k = 0; k < rawDistances.length; k++) {
            double distance;
            try {
                distance = Double.parseDouble(rawDistances[k]);
            } catch (NumberFormatException e) {
                throw new IllegalFlightFileException();
            }
            if (distance < 0) {
                throw new IllegalFlightFileException();
            }
            Country sourceCountry = graphList.getVertexArray().get(k);
            Country destinationCountry = graphList.getVertexArray().get(rawDistances.length);
            if (distance != 0) {
                assert distance > 0;
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
            for (int i = 0; i < numberOfCountries; i++) {
                if (i == 0) {
                    rawInput = scanner.nextLine().split(separator, numberOfCountries);
                    assert rawInput.length == numberOfCountries;
                    loadCountries(rawInput, graphList);
                    continue;
                }
                rawInput = scanner.nextLine().split(separator, i);
                assert rawInput.length == i;
                loadDistances(rawInput, graphList);
            }
        }
        return graphList;
    }

    public GraphList readAltData() throws WorldMapException {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Reading data from " + filePath2);
        File data = new File(filePath2);
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
            for (int i = 0; i < numberOfCountries; i++) {
                if (i == 0) {
                    rawInput = scanner.nextLine().split(separator, numberOfCountries);
                    assert rawInput.length == numberOfCountries;
                    loadCountries(rawInput, graphList);
                    continue;
                }
                rawInput = scanner.nextLine().split(separator, i);
                assert rawInput.length == i;
                loadDistances(rawInput, graphList);
            }
        }
        return graphList;
    }
}
