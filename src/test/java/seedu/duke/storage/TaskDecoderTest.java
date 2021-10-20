package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.items.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskDecoderTest {

    @Test
    void decodeTaskFromString_stringValidFormat_expectOneTask() {
        // Setting up
        String encodedTask = "t | Hype myself up | X | Drink lots of sugar | 19-02-2022 1950";
        Task decodedTask = TaskDecoder.decodeTaskFromString(encodedTask);

        String expectedTitle = "Hype myself up";
        assertEquals(expectedTitle, decodedTask.getTitle());
    }
}
