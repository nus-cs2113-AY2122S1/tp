package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.exceptions.LotsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteCommandTest {

    protected PeopleManager peopleManager = new PeopleManager();

    /**
     * Sets up an order list consisting of 3 people with 1 order each for the test.
     *
     * @throws LotsException If the add command is of an invalid format.
     */
    @BeforeEach
    void setUp() throws LotsException {
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

    /**
     * Test the deletion order '1' from person of index '2'.
     * Expects the size of peopleManager to decrease from 3 to 2.
     *
     * @throws LotsException If the delete command is of an invalid format.
     */
    @Test
    void execute_validInput_sucess() throws LotsException {
        String input = "delete 2/1";
        Command command = new DeleteCommand(input);
        command.setData(peopleManager);
        command.execute();
        assertEquals(2, peopleManager.getSize());
    }

    /**
     * Test the deletion order '1' from person of index '99'.
     * Expects an exception as person of index 99 does not exist.
     *
     * @throws LotsException If the delete command is of an invalid format.
     */
    @Test
    void execute_personIndexOutOfBounds_expectException() throws LotsException {
        String input = "delete 99/1";
        Command command = new DeleteCommand(input);
        command.setData(peopleManager);
        assertThrows(LotsException.class,
            () -> command.execute());
    }

    /**
     * Test the deletion order '2' from person of index '2'.
     * Expects an exception as an order of index '2' does not exist for the person of index '2'.
     *
     * @throws LotsException If the delete command is of an invalid format.
     */
    @Test
    void execute_orderIndexOutOfBounds_expectException() throws LotsException {
        String input = "delete 2/2";
        Command command = new DeleteCommand(input);
        command.setData(peopleManager);
        assertThrows(LotsException.class,
            () -> command.execute());
    }

    /**
     * Testing an invalid input format (no order index) into the deleteCommand class.
     * Expects an exception as the format is invalid.
     */
    @Test
    void execute_inputWithoutFoodIndex_expectException() {
        String input = "delete 2";
        assertThrows(LotsException.class,
            () -> new DeleteCommand(input));
    }

    /**
     * Testing an invalid input format (person index in the form of a string instead of integer)
     * into the deleteCommand class.
     * Expects an exception as the format is invalid.
     */
    @Test
    void execute_invalidPersonIndex_expectException() {
        String input = "delete markus/1";
        assertThrows(LotsException.class,
            () -> new DeleteCommand(input));
    }

    /**
     * Testing an invalid input format (order index in the form of a character instead of integer)
     * into the deleteCommand class.
     * Expects an exception as the format is invalid.
     */
    @Test
    void execute_invalidOrderIndex_expectException() {
        String input = "delete 1/a";
        assertThrows(LotsException.class,
            () -> new DeleteCommand(input));
    }

    /**
     * Testing an invalid input format (both person and order index in the form of a string instead of integer)
     * into the deleteCommand class.
     * Expects an exception as the format is invalid.
     */
    @Test
    void execute_invalidInput_expectException() {
        String input = "delete Markus/a";
        assertThrows(LotsException.class,
            () -> new DeleteCommand(input));
    }

}
