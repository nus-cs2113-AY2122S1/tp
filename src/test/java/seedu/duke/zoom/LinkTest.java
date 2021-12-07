package seedu.duke.zoom;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.zoom.AddZoomCommand;
import seedu.duke.exceptions.zoom.MissingParametersException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkTest {
    static AddZoomCommand addition = new AddZoomCommand("moduleName", "https://test.com");

    @Test
    void checkZoomLink() throws MissingParametersException {
        assertEquals(addition.getZoomLink().split("//")[0].trim(), "https:");
    }
}
