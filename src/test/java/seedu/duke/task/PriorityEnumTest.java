package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.PriorityEnumDoesNotExistException;

import static org.junit.jupiter.api.Assertions.*;

class PriorityEnumTest {
    @Test
    void getEnum_ValidInputs_expectPriorityEnum() throws PriorityEnumDoesNotExistException {
        assertEquals(PriorityEnum.HIGH, PriorityEnum.getEnum(1));
        assertEquals(PriorityEnum.MEDIUM, PriorityEnum.getEnum(2));
        assertEquals(PriorityEnum.MEDIUM, PriorityEnum.getEnum(3));
        assertEquals(PriorityEnum.MEDIUM, PriorityEnum.getEnum(4));
        assertEquals(PriorityEnum.MEDIUM, PriorityEnum.getEnum(5));
        assertEquals(PriorityEnum.LOW, PriorityEnum.getEnum(6));
        assertEquals(PriorityEnum.LOW, PriorityEnum.getEnum(7));
        assertEquals(PriorityEnum.LOW, PriorityEnum.getEnum(8));
        assertEquals(PriorityEnum.LOW, PriorityEnum.getEnum(9));
    }

    @Test
    void getEnum_InvalidInputs_expectPriorityEnumDoesNotExistException() {
        assertThrows(PriorityEnumDoesNotExistException.class,
                () -> PriorityEnum.getEnum(0));
        assertThrows(PriorityEnumDoesNotExistException.class,
                () -> PriorityEnum.getEnum(10));
    }
}