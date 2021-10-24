package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidPriorityException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author SeanRobertDH
class PriorityEnumTest {
    @Test
    void getEnum_validInputs_expectPriorityEnum() throws InvalidPriorityException {
        assertEquals(PriorityEnum.HIGH, PriorityEnum.getPriority(2));
        assertEquals(PriorityEnum.MEDIUM, PriorityEnum.getPriority(1));
        assertEquals(PriorityEnum.LOW, PriorityEnum.getPriority(0));
    }

    @Test
    void getEnum_invalidInputs_expectPriorityEnumDoesNotExistException() {
        assertThrows(InvalidPriorityException.class,
            () -> PriorityEnum.getPriority(-1));
        assertThrows(InvalidPriorityException.class,
            () -> PriorityEnum.getPriority(10));
    }
}