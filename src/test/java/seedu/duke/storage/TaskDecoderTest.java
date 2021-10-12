package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskDecoderTest {

    @Test
    void decodeTasksList_listOfTwoTasksAsStrings_expectListOfTwoTasks() {
        // Setting up
        String encodedTask1 = "Do homework |   | CS2113T tP v1.0 | 19-02-2022 2359";
        String encodedTask2 = "Laundry | | | 19-02-2022 2358";
        List<String> encodedTaskList = new ArrayList<>();
        encodedTaskList.add(encodedTask1);
        encodedTaskList.add(encodedTask2);
        ArrayList<Task> decodedTaskList = TaskDecoder.decodeTasksList(encodedTaskList);

        DateTimeFormatter requiredFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime expectedDateTime = LocalDateTime.parse("19-02-2022 2359", requiredFormat);

        // Check number of items in decodedTaskList
        assertEquals(2, decodedTaskList.size());

        // Check values are added correctly to a task
        assertEquals("Do homework", decodedTaskList.get(0).getTitle());
        assertFalse(decodedTaskList.get(0).getIsDone());
        assertEquals("CS2113T tP v1.0", decodedTaskList.get(0).getDescription());
        assertEquals(expectedDateTime, decodedTaskList.get(0).getDateTime());
    }
}