package seedu.duke.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;
import seedu.duke.items.Task;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.Duke.memberRoster;

class TaskEncoderTest {

    @AfterEach
    public void reset() {
        eventCatalog.clear();
        memberRoster.clear();
    }

    @Test
    void encodeTasksList_listOfTwoTasks_expectListOfTwoStrings() throws DukeException {
        // Setting up
        ArrayList<Task> tasksList = setUp();
        List<String> encodedTasksList = TaskEncoder.encodeTasksList(tasksList);

        // Check number of items in encodedTasksList
        assertEquals(2, encodedTasksList.size());

        // Check the item is saved in the correct String format
        String expectedResult1 = "t | Hype myself up | X | Drink lots of sugar | 19-02-2022 1950 | JOHN_DOE"
                + " | 0 | Peppa Pig's Concert";
        assertEquals(expectedResult1, encodedTasksList.get(0));
        String expectedResult2 = "t | Enter venue |   |  | 19-02-2022 1955 | JOHN_DOE JANE_DOE"
                + " | 0 | Peppa Pig's Concert";
        assertEquals(expectedResult2, encodedTasksList.get(1));
    }

    private ArrayList<Task> setUp() throws DukeException {
        Member johnDoe = new Member("JOHN DOE");
        memberRoster.add(johnDoe);
        Member janeDoe = new Member("JANE DOE");
        memberRoster.add(janeDoe);

        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);

        LocalDateTime task1DateTime = Parser.convertDateTime("19-02-2022 1950");
        Task task1 = new Task("Hype myself up", "Drink lots of sugar", task1DateTime);
        task1.memberList.add(johnDoe);
        task1.markAsDone();
        task1.setEvent(event1);
        event1.addToTaskList(task1);
        event1.getFromTaskList(0).markAsDone();

        LocalDateTime task2DateTime = Parser.convertDateTime("19-02-2022 1955");
        Task task2 = new Task("Enter venue", "", task2DateTime);
        task2.memberList.add(johnDoe);
        task2.memberList.add(janeDoe);
        task2.setEvent(event1);
        event1.addToTaskList(task2);
        event1.markAsDone();

        eventCatalog.add(event1);

        return event1.getTaskList();
    }
}
