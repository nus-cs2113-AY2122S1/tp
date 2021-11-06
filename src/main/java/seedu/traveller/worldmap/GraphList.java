package seedu.traveller.worldmap;

import seedu.traveller.Parser;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GraphList {
    private static final Logger logger = Logger.getLogger(GraphList.class.getName());
    private final ArrayList<Country> vertexArray;
    private final ArrayList<String> nameArray;
    private final double[][] edgeMatrix;

    public GraphList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a GraphList");
        this.vertexArray = new ArrayList<>();
        this.nameArray = new ArrayList<>();
        this.edgeMatrix = new double[10][10];
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

    public void addVertex(Country v) {
        this.vertexArray.add(v);
        this.nameArray.add(v.name);
    }

    public Country findVertex(String s) throws EmptyVertexException {
        for (Country v: this.vertexArray) {
            if (Objects.equals(v.name, s)) {
                return v;
            }
        }
        throw new EmptyVertexException(s);
    }

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

}
