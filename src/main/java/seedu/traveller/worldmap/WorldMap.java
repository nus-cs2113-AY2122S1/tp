package seedu.traveller.worldmap;

import seedu.traveller.worldmap.exceptions.EmptyVertexException;
import seedu.traveller.worldmap.exceptions.WorldMapException;


public class WorldMap {
    protected static GraphList graphList = new GraphList();
    protected static Loader loader = new Loader();
    protected static Logic logic = new Logic();

    public static void initWorldMap() {
        try {
            graphList = loader.readData();
            WorldMap.printWorld();
        } catch (WorldMapException e) {
            System.out.println(e.getMessage());
        }
    }

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

    public static MinCalcResult calcMinDistance(String sourceCountryName, String targetCountryName) {
        Country sourceCountry = getCountry(sourceCountryName);
        Country targetCountry = getCountry(targetCountryName);
        logic.computeSource(sourceCountry, graphList);
        return logic.getToGoal(sourceCountry,targetCountry);
    }

    public static Country getCountry(String countryName) {
        Country country = new Country("",-1);
        try {
            country = graphList.findVertex(countryName);
        } catch (EmptyVertexException e) {
            System.out.println(e.getMessage());
        }
        return country;
    }
}
