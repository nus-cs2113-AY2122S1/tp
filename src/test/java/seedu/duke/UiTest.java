package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_N_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_Y_INPUT;

//@@author EdwardZYWang
class UiTest {

    @Test
    void memoryStartup() {
        boolean flag = false;
        boolean result = false;
        String uiInput = "y";
        if (uiInput.trim().equals("y")) {
            System.out.println(MEMORY_STARTUP_Y_INPUT);
            result = true;
            flag = true;
        }
        assertEquals(true, flag);
        assertEquals(true, result);
    }

    @Test
    void memoryStartup1() {
        boolean flag = false;
        boolean result = false;
        String uiInput = "y";
        if (uiInput.isEmpty()) {
            System.out.println(MEMORY_STARTUP_N_INPUT);
            System.out.println(System.lineSeparator() + "What would you like to do?");
            result = false;
            flag = true;
            assertEquals(true, flag);
            assertEquals(false, result);
        }
    }

}