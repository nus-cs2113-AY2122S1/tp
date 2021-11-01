package seedu.traveller.worldmap;

import org.junit.jupiter.api.Test;
import seedu.traveller.worldmap.exceptions.IllegalFlightFileException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DataLoaderTest {
    private final GraphList graphList;
    private final DataLoader dataLoader;
    private final String missingCountryInput = "SIN|MLY|CHN|JPN|";
    private final String missingSeparatorInput = "SIN|MLY|CHN|JAPAN|SKR";
    private final String nonNumberDistance = "a|b|c|d|e";
    private final String negativeNumberDistance = "-1|3|0|-4|6";

    public DataLoaderTest() {
        this.graphList = new GraphList();
        this.dataLoader = new DataLoader();
    }

    private String[] parseInput(String input, int limit) {
        return input.split("\\|", limit);
    }

    @Test
    public void loadCountries_exceptionThrown() {
        assertThrows(IllegalFlightFileException.class,
                () -> dataLoader.loadCountries(parseInput(missingCountryInput, 5), graphList));
        assertThrows(IllegalFlightFileException.class,
                () -> dataLoader.loadCountries(parseInput(missingSeparatorInput, 5), graphList));
    }

    @Test
    public void loadDistances_exceptionThrown() {
        assertThrows(IllegalFlightFileException.class,
                () -> dataLoader.loadDistances(parseInput(nonNumberDistance, 5), graphList));
        assertThrows(IllegalFlightFileException.class,
                () -> dataLoader.loadDistances(parseInput(negativeNumberDistance, 5), graphList));
    }
}