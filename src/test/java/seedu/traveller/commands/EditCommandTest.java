package seedu.traveller.commands;

import org.junit.jupiter.api.Test;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.worldmap.WorldMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author gavienwz
class EditCommandTest {

    private final Ui ui;
    private final TripsList tripsList;
    private final NewCommand beforeEditCommand = new NewCommand("Trip1", "MLY", "CHN");
    private final NewCommand afterEditCommand = new NewCommand("Trip2", "SIN", "JPN");
    private final EditCommand edit = new EditCommand("Trip1", "","SIN", "JPN");

    public EditCommandTest() {
        WorldMap.initWorldMap();
        this.ui = new Ui();
        this.tripsList = new TripsList();
    }

    @Test
    public void editCommand_Success() throws TravellerException {
        beforeEditCommand.execute(tripsList, ui);
        afterEditCommand.execute(tripsList, ui);
        Trip firstTrip = tripsList.getTrip(0);
        Trip secondTrip = tripsList.getTrip(1);

        assertNotEquals(firstTrip.getStartCountryCode(), secondTrip.getStartCountryCode());
        assertNotEquals(firstTrip.getEndCountryCode(), secondTrip.getEndCountryCode());
        assertNotEquals(firstTrip.getPath(), secondTrip.getPath());
        assertNotEquals(firstTrip.getTime(), secondTrip.getTime());

        edit.execute(tripsList, ui);
        Trip editedTrip = tripsList.getTrip(0);

        assertEquals(editedTrip.getStartCountryCode(), secondTrip.getStartCountryCode());
        assertEquals(editedTrip.getEndCountryCode(), secondTrip.getEndCountryCode());
        assertEquals(editedTrip.getPath(), secondTrip.getPath());
        assertEquals(editedTrip.getTime(), secondTrip.getTime());
    }

    @Test
    public void editCommand_create_success() {
        String expectedOutput = "Edit command:"
                + "\n\ttripName: Trip1"
                + "\n\tstartCountry: SIN"
                + "\n\tendCountry: JPN";

        assertEquals(expectedOutput, edit.toString());
    }

    @Test public void editCommand_execute_exceptionThrown() {
        assertThrows(TravellerException.class, () -> this.edit.execute(tripsList, ui));
    }
}