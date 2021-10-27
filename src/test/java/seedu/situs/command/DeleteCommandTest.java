package seedu.situs.command;

import org.junit.jupiter.api.Test;
import seedu.situs.exceptions.SitusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {

    @Test
    public void deleteCommandTest_invalidNumberInput_expectException() {
        try {
            int groupNumber = -1;
            int ingredientNumber = 1;
            String resultMsg = new DeleteCommand(groupNumber, ingredientNumber).run();
            fail();
        } catch (SitusException e) {
            assertEquals("Ingredient number does not exist!", e.getMessage());
        }
    }

}
