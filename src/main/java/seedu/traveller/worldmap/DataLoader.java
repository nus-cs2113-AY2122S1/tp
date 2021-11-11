package seedu.traveller.worldmap;

import seedu.traveller.worldmap.exceptions.IllegalFlightFileException;
import seedu.traveller.worldmap.exceptions.WorldMapException;
import seedu.traveller.worldmap.exceptions.FlightDataNotFoundException;

import java.io.InputStream;
import java.util.ArrayList;
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
    private final int countryCodeLength = 3;
    private final String filePath = "/time.txt";
    private final String filePath2 = "/cost.txt";
    private final String separator = "\\|";

    /**
     * This function is responsible for adding the countries (vertexes).
     * @param countryCodes The relevant line that is read from the text file.
     * @param graphList The graphList that contains the country and distance matrices.
     * @param filePath To identify which text file throws an exception.
     * @throws IllegalFlightFileException When the text file contains unreadable data. (Invalid Country Codes)
     */
    protected void loadCountries(String[] countryCodes, GraphList graphList, String filePath)
            throws IllegalFlightFileException {
        logger.log(Level.INFO, "Loading countries: " + Arrays.toString(countryCodes));
        assert countryCodes.length == numberOfCountries;
        for (int j = 0; j < numberOfCountries; j++) {
            String countryCode = countryCodes[j];
            if (countryCode.length() != countryCodeLength) {
                throw new IllegalFlightFileException(filePath);
            }
            int countryIndex = j + 1;
            Country current = new Country(countryCode, countryIndex);
            graphList.addVertex(current);
        }
    }

    /**
     * This function is responsible for adding the distances (edges) between two countries (vertexes).
     * @param rawDistances The relevant line that is read from the text file.
     * @param graphList The graphList that contains the country and distance matrices.
     * @param filePath To identify which text file throws an exception.
     * @throws IllegalFlightFileException When the text file contains unreadable data. (Negative / Non numbers)
     */
    protected void loadDistances(String[] rawDistances, GraphList graphList, String filePath)
            throws IllegalFlightFileException {
        logger.log(Level.INFO, "Loading distances: " + Arrays.toString(rawDistances));
        assert rawDistances.length <= numberOfCountries;
        for (int k = 0; k < rawDistances.length; k++) {
            double distance;
            try {
                distance = Double.parseDouble(rawDistances[k]);
            } catch (NumberFormatException e) {
                throw new IllegalFlightFileException(filePath);
            }
            if (distance < 0) {
                throw new IllegalFlightFileException(filePath);
            }
            ArrayList<Country> countryArray = graphList.getVertexArray();
            Country sourceCountry = countryArray.get(k);
            Country destinationCountry = countryArray.get(rawDistances.length);
            if (distance != 0) {
                assert distance > 0;
                graphList.createEdge(distance, destinationCountry, sourceCountry);
            }
        }
    }

    /**
     * This is the main function of the class. It reads the text file for information
     * and passes the countries and distances to be added to the world map.
     * The first line read contains the countries, while the remaining lines read contains the distances.
     * @return graphList which contains the Country (vertex) matrix and Distance (edge matrix).
     * @throws WorldMapException When the text file doesn't exist or when it contains invalid data.
     */
    public GraphList readData() throws WorldMapException {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Reading data from " + filePath);
        InputStream data = getClass().getResourceAsStream(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(data);
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Flight data cannot be found.");
            throw new FlightDataNotFoundException();
        }
        GraphList graphList = new GraphList();
        int i = 0;
        while (scanner.hasNext() && i < numberOfCountries) {
            if (i == 0) {
                readCountries(scanner, graphList, filePath);
            } else {
                readDistance(scanner, graphList, i, filePath);
            }
            i++;
        }
        return graphList;
    }

    /**
     * This is the main function of the class. It reads the text file for information
     * and passes the countries and costs to be added to the world map.
     * The first line read contains the countries, while the remaining lines read contains the costs.
     * @return graphList which contains the Country (vertex) matrix and Cost (edge matrix).
     * @throws WorldMapException When the text file doesn't exist or when it contains invalid data.
     */
    public GraphList readAltData() throws WorldMapException {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Reading data from " + filePath2);
        InputStream data = getClass().getResourceAsStream(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(data);
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Flight data cannot be found.");
            throw new FlightDataNotFoundException();
        }
        GraphList graphList = new GraphList();
        int i = 0;
        while (scanner.hasNext() && i < numberOfCountries) {
            if (i == 0) {
                readCountries(scanner, graphList, filePath2);
            } else {
                readDistance(scanner, graphList, i, filePath2);
            }
            i++;
        }
        return graphList;
    }

    /**
     * This function reads the country data from the text file.
     * @param scanner Scanner object to read from the text file.
     * @param graphList GraphList to add countries to.
     * @param filePath To identify which text file throws an exception.
     * @throws IllegalFlightFileException If the text file contains invalid data (More country codes than expected.)
     */
    public void readCountries(Scanner scanner, GraphList graphList, String filePath) throws IllegalFlightFileException {
        String[] rawInput = parseNextLine(scanner, numberOfCountries);
        if (rawInput.length != numberOfCountries) {
            throw new IllegalFlightFileException(filePath);
        }
        loadCountries(rawInput, graphList, filePath);
    }

    /**
     * This function reads the time/cost data from the text file, depending on which text file is read from.
     * @param scanner Scanner object to read from the text file.
     * @param graphList GraphList to add time/cost to.
     * @param i Variable to ensure that data is read in lower triangular matrix. (Which the text file will contain.)
     * @param filePath To identify which text file throws an exception.
     * @throws IllegalFlightFileException If the text file contains invalid data
     *     (More time/cost numbers than expected.)
     */
    private void readDistance(Scanner scanner, GraphList graphList, int i, String filePath)
            throws IllegalFlightFileException {
        String[] rawInput = parseNextLine(scanner, i);
        if (rawInput.length != i) {
            throw new IllegalFlightFileException(filePath);
        }
        loadDistances(rawInput, graphList, filePath);
    }

    /**
     * This function parses the next line according to how readCountries and readDistance wants it to be.
     * @param scanner Scanner object to read from the text file.
     * @param limit Number to ensure only certain number of data is read.
     * @return Parsed string array that contains Country codes or Time/Costs to be added to the GraphList.
     */
    private String[] parseNextLine(Scanner scanner, int limit) {
        return scanner.nextLine().split(separator, limit);
    }


}
