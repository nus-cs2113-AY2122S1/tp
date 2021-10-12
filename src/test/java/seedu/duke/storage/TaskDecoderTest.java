package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskDecoderTest {

    @Test
    void decodeTasksList_listOfTwoTasksAsStrings_expectListOfTwoTasks() {
        // Setting up
        String encodedTask1 = "Do homework | X | CS2113T tP v1.0 | 19-02-2022 2359";
        String encodedTask2 = "Laundry |   |  | 19-02-2022 2358";
        List<String> encodedTaskList = new ArrayList<>();
        encodedTaskList.add(encodedTask1);
        encodedTaskList.add(encodedTask2);
        ArrayList<Task> decodedTaskList = TaskDecoder.decodeTasksList(encodedTaskList);

        // Check number of items in decodedTaskList
        assertEquals(2, decodedTaskList.size());

        // Check values are added correctly to a task
        assertEquals("Do homework", decodedTaskList.get(0).getTitle());
        assertTrue(decodedTaskList.get(0).getIsDone());
        assertEquals("CS2113T tP v1.0", decodedTaskList.get(0).getDescription());
        LocalDateTime expectedDateTime = Parser.convertDateTime("19-02-2022 2359");
        assertEquals(expectedDateTime, decodedTaskList.get(0).getDateTime());
    }
}
