package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.LotsException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {
    private static final String TEST_NAME = "hello";

    @Test
    public void addFoodToIndividualFoodOrders_menuIndexLessThanOne_expectException() throws LotsException {
        Person person = new Person(TEST_NAME);
        assertThrows(LotsException.class, () -> {
            person.addFoodToIndividualFoodOrders(-1, 1);
        });
        assertThrows(LotsException.class, () -> {
            person.addFoodToIndividualFoodOrders(0, 1);
        });
    }

    @Test
    public void addFoodToIndividualFoodOrders_menuIndexOutOfBounds_expectException() throws LotsException {
        Person person = new Person(TEST_NAME);
        assertThrows(LotsException.class, () -> {
            person.addFoodToIndividualFoodOrders(188, 1);
        });
    }
}

