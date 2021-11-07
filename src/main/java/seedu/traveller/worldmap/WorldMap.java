package seedu.traveller.worldmap;

import seedu.traveller.worldmap.exceptions.EmptyVertexException;
import seedu.traveller.worldmap.exceptions.NonZeroDistanceException;
import seedu.traveller.worldmap.exceptions.WorldMapException;
import seedu.traveller.worldmap.exceptions.NonStringDistanceException;

//@@author jach23
/**
 * This class manages the overall WorldMap functionalities for the Traveller program.
 * It initialises and calls the major shortest path functionalities.
 */
public class WorldMap {
    protected static GraphList graphList = new GraphList();
    protected static DataLoader loader = new DataLoader();
    protected static Logic logic = new Logic();

    /**
     * This function is responsible for initialising the world map by reading
     * the saved time data from the time data files.
     */
    public static void initWorldMap() {
        try {
            graphList = loader.readData();
        } catch (WorldMapException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * This function is responsible for initialising the alternative world map which
     * corresponds to the world map based on flight costs. It does so by reading
     * the cost data from the cost data files.
     */
    public static void altWorldMap() {
        try {
            graphList = loader.readAltData();
        } catch (WorldMapException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * This function is responsible for printing the current world map based
     * on the countries and distances between them.
     */
    public static void printWorld() {
        for (String countryCode : graphList.getNameArray()) {
            System.out.print(countryCode + " ");
        }
        System.out.println("");
        int numberOfCountries = graphList.getNameArray().size();
        System.out.println(numberOfCountries);
        Country startCountry;
        Country endCountry;
        for (int i = 0; i < numberOfCountries; i++) {
            for (int j = 0; j < numberOfCountries; j++) {
                startCountry = graphList.getVertexArray().get(i);
                endCountry = graphList.getVertexArray().get(j);
                int startCountryKey = startCountry.getKey();
                int endCountryKey = endCountry.getKey();
                System.out.println("From : " + startCountry + " To : " + endCountry
                        + " is " + graphList.getEdgeMatrix()[startCountryKey][endCountryKey]);
            }
        }
    }

    /**
     * This function is responsible for finding the shortest time between two countries and returning the
     * corresponding path.
     * @param sourceCountryName The starting country.
     * @param targetCountryName The destination country.
     * @return MinCalcResult which contains the source and destination countries along with the path of least time.
     */
    public static MinCalcResult calcMinTime(String sourceCountryName, String targetCountryName) {
        Country sourceCountry = getValidCountry(sourceCountryName);
        Country targetCountry = getValidCountry(targetCountryName);

        if (sourceCountry.getKey() == -1 || targetCountry.getKey() == -1) {
            MinCalcResult result = new MinCalcResult(sourceCountry, targetCountry, null, null);
            result.setError();
            return result;
        }
        logic.computeSource(sourceCountry, graphList);

        return logic.getToGoal(sourceCountry,targetCountry);
    }

    /**
     * This function is responsible for finding the least cost between two countries and returning the
     * corresponding path.
     * @param sourceCountryName The starting country.
     * @param targetCountryName The destination country.
     * @return MinCalcResult which contains the source and destination countries along with the path of the least cost.
     */
    public static MinCalcResult calcMinCost(String sourceCountryName, String targetCountryName) {
        altWorldMap();

        Country sourceCountry = getValidCountry(sourceCountryName);
        Country targetCountry = getValidCountry(targetCountryName);
        if (sourceCountry.getKey() == -1 || targetCountry.getKey() == -1) {
            MinCalcResult result = new MinCalcResult(sourceCountry, targetCountry, null, null);
            result.setError();
            return result;
        }
        logic.computeSource(sourceCountry, graphList);
        MinCalcResult minResult = logic.getToGoal(sourceCountry,targetCountry);

        initWorldMap();

        return minResult;
    }

    /**
     * This function is responsible for ensuring the validity of the given country
     * based from the user input.
     * @param countryName The name of the country.
     * @return Country which contains the corresponding country in the class.
     */
    public static Country getValidCountry(String countryName) {
        Country country = new Country("",-1);;
        try {
            country = graphList.findVertex(countryName);
        } catch (EmptyVertexException e) {
            System.out.println(e.getMessage());
        }

        return country;
    }

    /**
     * This function is responsible for ensuring the distance input is non-negative and is
     * above or equal to 0.1.
     * @param dist The distance to be checked against.
     * @throws NonZeroDistanceException When the distance is less than 0.1.
     */
    public static void distanceNonZero(double dist) throws NonZeroDistanceException {
        if (dist < 0.1) {
            throw new NonZeroDistanceException(dist);
        }
    }

    /**
     * This function is responsible for ensuring the input distance is of the type
     * Double.
     * @param rawDist The distance to be checked against.
     * @throws NonStringDistanceException When the distance is not of double form.
     */
    public static void distanceNonString(String rawDist)
            throws NonStringDistanceException {
        try {
            Double.parseDouble(rawDist);
        } catch (NumberFormatException e) {
            throw new NonStringDistanceException(rawDist);
        }
    }

    /**
     * This function is responsible for updating the map with a given distance between two
     * corresponding countries.
     * @param dist The new distance.
     * @param sourceCountryName The starting country.
     * @param targetCountryName The destination country.
     */
    public static void editMap(Double dist, String sourceCountryName, String targetCountryName) {
        try {
            Country sourceCountry = getValidCountry(sourceCountryName);
            Country targetCountry = getValidCountry(targetCountryName);

            graphList.modifyEdge(dist, sourceCountry, targetCountry);
            distanceNonZero(dist);
        } catch (NonZeroDistanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
