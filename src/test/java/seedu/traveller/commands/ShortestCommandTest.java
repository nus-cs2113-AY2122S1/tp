package seedu.traveller.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShortestCommandTest {
    @Test
    public void shortestCommand_create_success() {
        final String expectedOutput = "Shortest command: "
                + "\n\tdistOrCost: dist"
                + "\n\tstartCountry: CHN"
                + "\n\tendCountry: SIN";
        ShortestCommand shortestCommand = new ShortestCommand("dist", "CHN", "SIN");
        assertEquals(expectedOutput, shortestCommand.toString());
    }
}
