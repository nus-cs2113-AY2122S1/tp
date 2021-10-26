package seedu.traveller.worldmap;

import seedu.traveller.exceptions.IllegalFlightFileException;
import seedu.traveller.worldmap.exceptions.WorldMapException;
import seedu.traveller.worldmap.exceptions.FlightDataNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author gavienwz
/**
 * This class is responsible for creating the world map's countries and distances (vertexes and edges).
 */
public class DataLoader {
    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());
    private final int numberOfCountries = 5;
    private final String filePath = "./flightData/dist.txt";
    private final String filePath2 = "./flightData/cost.txt";
    private final String separator = "\\|";

    /**
     * This function is responsible for adding the countries (vertexes).
     * @param countryCodes The relevant line that is read from the text file.
     * @param graphList
     * @throws IllegalFlightFileException When the text file contains unreadable data. (Invalid Country Codes)
     */
    protected void loadCountries(String[] countryCodes, GraphList graphList) throws IllegalFlightFileException {
        logger.log(Level.INFO, "Loading countries: " + Arrays.toString(countryCodes));
        assert countryCodes.length == numberOfCountries;
        for (int j = 0; j < numberOfCountries; j++) {
            String countryCode = countryCodes[j];
            if (countryCode.length() != 3) {
                throw new IllegalFlightFileException();
            }
            int countryIndex = j + 1;
            Country v = new Country(countryCode, countryIndex);
            graphList.addVertex(v);
        }
    }

    /**
     * This function is responsible for adding the distances (edges) between two countries (vertexes).
     * @param rawDistances The relevant line that is read from the text file.
     * @param graphList The graphList that contains the country and distance matrices.
     * @throws IllegalFlightFileException When the text file contains unreadable data. (Negative / Non numbers)
     */
    protected void loadDistances(String[] rawDistances, GraphList graphList) throws IllegalFlightFileException {
        logger.log(Level.INFO, "Loading distances: " + Arrays.toString(rawDistances));
        assert rawDistances.length <= numberOfCountries;
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

    /**
     * This is the main function of the class. It reads the text file for information and passes the countries and distances to be added to the world map.
     * The first line read contains the countries, while the remaining lines read contains the distances.
     * @return graphlist which contains the Country (vertex) matrix and Distance (edge matrix).
     * @throws WorldMapException When the text file doesn't exist or when it contains invalid data.
     */
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
                    if (rawInput.length != numberOfCountries) {
                        throw new IllegalFlightFileException();
                    }
                    loadCountries(rawInput, graphList);
                    continue;
                }
                rawInput = scanner.nextLine().split(separator, i);
                if (rawInput.length != i) {
                    throw new IllegalFlightFileException();
                }
                loadDistances(rawInput, graphList);
            }
        }
        return graphList;
    }

    /**
     * This is the main function of the class. It reads the text file for information and passes the countries and costs to be added to the world map.
     * The first line read contains the countries, while the remaining lines read contains the costs.
     * @return graphlist which contains the Country (vertex) matrix and Cost (edge matrix).
     * @throws WorldMapException When the text file doesn't exist or when it contains invalid data.
     */
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
                    if (rawInput.length != numberOfCountries) {
                        throw new IllegalFlightFileException();
                    }
                    loadCountries(rawInput, graphList);
                    continue;
                }
                rawInput = scanner.nextLine().split(separator, i);
                if (rawInput.length != i) {
                    throw new IllegalFlightFileException();
                }
                loadDistances(rawInput, graphList);
            }
        }
        return graphList;
    }
}
