package seedu.traveller.worldMap;

import seedu.traveller.worldMap.exceptions.EmptyVertexException;
import seedu.traveller.worldMap.exceptions.WorldMapException;


public class WorldMap {
    protected static GraphList graphList= new GraphList();
    protected static Loader loader = new Loader();
    protected static Logic logic = new Logic();

    public static void initWorldMap() {
        try {
            graphList = loader.readData();
        } catch (WorldMapException e) {
            System.out.println(e.getMessage());
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
