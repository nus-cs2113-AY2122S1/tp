package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.EditCommand;
import seedu.duke.exceptions.LotsException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EditCommandTest {

    protected static PeopleManager peopleManager = new PeopleManager();

    @BeforeAll
    public static void setUp() throws LotsException {
        String[] names = {"Markus", "Adam", "Andrew"};
        String[] foodIndex = {"1", "4", "8"};
        PeopleManager.clearListOfPeople();

        for (int i = 0; i < 3; i++) {
            String input = "add /n " + names[i] + " /i " + foodIndex[i] + " /q 1";
            Command command = new AddCommand(input);
            command.setData(peopleManager);
            command.execute();
        }
    }

    @Test
    void execute_personIndexOutOfBounds_expectException() throws LotsException {
        String input = "edit 100/1 /q 2";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_orderIndexOutOfBounds_expectException() throws LotsException {
        String input = "edit 2/800 /q 2";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_quantityOutOfBounds_expectException() throws LotsException {
        String input = "edit 2/1 /q -2123 ";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_quantityOverLimit_expectException() throws LotsException {
        String input = "edit 2/1 /q 1000";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_inputWithoutFoodIndex_expectException() {
        String input = "edit 2 /q 4";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_invalidPersonIndex_expectException() {
        String input = "edit markus/1 /q 3";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_invalidOrderIndex_expectException() {
        String input = "edit 1/a /q 3";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_invalidQuantityIndex_expectException() {
        String input = "edit 1/1 /q c";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

    @Test
    void execute_invalidInput_expectException() {
        String input = "edit Markus/a";
        assertThrows(LotsException.class,
            () -> new EditCommand(input));
    }

}
