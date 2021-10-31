package seedu.traveller.commands;

import org.junit.jupiter.api.Test;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TripNotFoundException;
import seedu.traveller.objects.TripsList;

import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author Uxinnn
public class DeleteCommandTest {
    private final DeleteCommand deleteCommand;

    public DeleteCommandTest() {
        this.deleteCommand = new DeleteCommand("testTrip");
    }

    @Test
    public void deleteCommand_execute_exceptionThrown() {
        TripsList tripsList = new TripsList();
        Ui ui = new Ui();

        assertThrows(TripNotFoundException.class, () -> {
            this.deleteCommand.execute(tripsList, ui);
        });
    }
}
