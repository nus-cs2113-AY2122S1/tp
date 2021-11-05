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
    private final EditCommand edit = new EditCommand("Trip1", "SIN", "JPN");

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
        assertNotEquals(firstTrip.getDistances(), secondTrip.getDistances());

        edit.execute(tripsList, ui);
        Trip editedTrip = tripsList.getTrip(0);

        assertEquals(editedTrip.getStartCountryCode(), secondTrip.getStartCountryCode());
        assertEquals(editedTrip.getEndCountryCode(), secondTrip.getEndCountryCode());
        assertEquals(editedTrip.getPath(), secondTrip.getPath());
        assertEquals(editedTrip.getDistances(), secondTrip.getDistances());
    }

    @Test
    public void editItemCommand_Success() throws TravellerException {
        beforeEditCommand.execute(tripsList, ui);
        afterEditCommand.execute(tripsList, ui);

        AddDayCommand addDayCommand = new AddDayCommand("Trip1", 1);
        AddDayCommand addDayCommand2 = new AddDayCommand("Trip2", 1);

        addDayCommand.execute(tripsList, ui);
        addDayCommand2.execute(tripsList, ui);

        AddItemCommand addItemCommand =
                new AddItemCommand("Trip1", 0, "1300", "sleep at home");
        AddItemCommand addItemCommand2 =
                new AddItemCommand("Trip2", 0, "1900", "wake up from bed");

        addItemCommand.execute(tripsList, ui);
        addItemCommand2.execute(tripsList, ui);

        Trip firstTrip = tripsList.getTrip(0);
        Trip secondTrip = tripsList.getTrip(1);

        assertNotEquals(firstTrip.getStartCountryCode(), secondTrip.getStartCountryCode());
        assertNotEquals(firstTrip.getEndCountryCode(), secondTrip.getEndCountryCode());

        assertEquals(firstTrip.getDaySize(), secondTrip.getDaySize());
        assertNotEquals(firstTrip.getDay(0).getItem(0).getItemName(), secondTrip.getDay(0).getItem(0).getItemName());
        assertNotEquals(firstTrip.getDay(0).getItem(0).getItemTime(), secondTrip.getDay(0).getItem(0).getItemTime());

        EditItemCommand editItem = new EditItemCommand("Trip1", 0, 0, "1900", "wake up from bed");

        editItem.execute(tripsList, ui);

        Trip editedTrip = tripsList.getTrip(0);

        assertEquals(editedTrip.getDaySize(), secondTrip.getDaySize());
        assertEquals(editedTrip.getDay(0).getItem(0).getItemName(), secondTrip.getDay(0).getItem(0).getItemName());
        assertEquals(editedTrip.getDay(0).getItem(0).getItemTime(), secondTrip.getDay(0).getItem(0).getItemTime());
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