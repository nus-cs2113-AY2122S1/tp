package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskEncoderTest {

    @Test
    void encodeTasksList_listOfTwoTasks_expectListOfTwoStrings() {
        // Setting up
        LocalDateTime task1Deadline = Parser.convertDateTime("19-02-2022 2359");
        Task task1 = new Task("Do homework", "CS2113T tp V1.0", task1Deadline);
        task1.markAsDone();
        LocalDateTime task2Deadline = Parser.convertDateTime("19-02-2022 0800");
        Task task2 = new Task("Waking up", "This is difficult...", task2Deadline);
        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(task1);
        tasksList.add(task2);
        List<String> encodedTasksList = TaskEncoder.encodeTasksList(tasksList);

        // Check number of items in encodedTasksList
        assertEquals(2, encodedTasksList.size());

        // Check the item is saved in the correct String format
        String expectedResult1 = "t | Do homework | X | CS2113T tp V1.0 | 19-02-2022 2359";
        String expectedResult2 = "t | Waking up |   | This is difficult... | 19-02-2022 0800";
        assertEquals(expectedResult1, encodedTasksList.get(0));
        assertEquals(expectedResult2, encodedTasksList.get(1));
    }
}
