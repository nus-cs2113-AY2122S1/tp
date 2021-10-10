package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.PriorityEnumDoesNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PriorityEnumTest {
    @Test
    void getEnum_validInputs_expectPriorityEnum() throws PriorityEnumDoesNotExistException {
        assertEquals(PriorityEnum.HIGH, PriorityEnum.getEnum(2));
        assertEquals(PriorityEnum.MEDIUM, PriorityEnum.getEnum(1));
        assertEquals(PriorityEnum.LOW, PriorityEnum.getEnum(0));
    }

    @Test
    void getEnum_invalidInputs_expectPriorityEnumDoesNotExistException() {
        assertThrows(PriorityEnumDoesNotExistException.class,
            () -> PriorityEnum.getEnum(-1));
        assertThrows(PriorityEnumDoesNotExistException.class,
            () -> PriorityEnum.getEnum(10));
    }
}