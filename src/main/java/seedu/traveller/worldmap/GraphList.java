package seedu.traveller.worldmap;

import seedu.traveller.Parser;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jach23
/**
 * This class is responsible for converting the World Map into a simple graph aka in terms of vertex and edges.
 */
public class GraphList {
    private static final Logger logger = Logger.getLogger(GraphList.class.getName());
    private final ArrayList<Country> vertexArray;
    private final ArrayList<String> nameArray;
    private final double[][] edgeMatrix;

    /**
     * This is the constructor of the class GraphList.
     */
    public GraphList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a GraphList");
        this.vertexArray = new ArrayList<>();
        this.nameArray = new ArrayList<>();
        this.edgeMatrix = new double[10][10];
    }

    /**
     * This function is responsible for modifying and updating the edge between two given countries.
     * @param distance The new distance to be updated.
     * @param startCountry The starting country.
     * @param endCountry The destination country.
     */
    public void modifyEdge(Double distance, Country startCountry, Country endCountry) {
        logger.log(Level.INFO, "Modifying an edge: "
                + "\n\tdistance: " + distance
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry);
        int startCountryKey = startCountry.getKey();
        int endCountryKey = endCountry.getKey();
        edgeMatrix[startCountryKey][endCountryKey] = distance;
        edgeMatrix[endCountryKey][startCountryKey] = distance;
        Country.updateNeighbour(distance, startCountry, endCountry);
        Country.updateNeighbour(distance, endCountry, startCountry);
    }

    /**
     * This function is responsible for creating a new edge between two given countries which is presently not inside
     * the database.
     * @param distance The new distance to be updated.
     * @param startCountry The starting country.
     * @param endCountry The destination country.
     */
    public void createEdge(Double distance, Country startCountry, Country endCountry) {
        logger.log(Level.INFO, "Creating an edge: "
                + "\n\tdistance: " + distance
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry);
        int startCountryKey = startCountry.getKey();
        int endCountryKey = endCountry.getKey();
        edgeMatrix[startCountryKey][endCountryKey] = distance;
        edgeMatrix[endCountryKey][startCountryKey] = distance;
        Country.addNeighbour(distance, startCountry, endCountry);
        Country.addNeighbour(distance, endCountry, startCountry);
    }

    /**
     * This function is responsible for adding a new country to the existing Country array and a string array of
     * names of the existing countries.
     * @param v The country to be added.
     */
    public void addVertex(Country v) {
        this.vertexArray.add(v);
        this.nameArray.add(v.name);
    }

    /**
     * This function is responsible for finding a given country in the string array of
     * names of the existing countries.
     * @param s The country to search for in String form.
     * @return v the Country as a Country class, if found.
     * @throws EmptyVertexException When the given country cannot be found.
     */
    public Country findVertex(String s) throws EmptyVertexException {
        for (Country v: this.vertexArray) {
            if (Objects.equals(v.name, s)) {
                return v;
            }
        }
        throw new EmptyVertexException(s);
    }

    public ArrayList<Country> getVertexArray() {
        return this.vertexArray;
    }

    public ArrayList<String> getNameArray() {
        return this.nameArray;
    }

    public double[][] getEdgeMatrix() {
        return this.edgeMatrix;
    }

}
