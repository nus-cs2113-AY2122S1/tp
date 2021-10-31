package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;
import seedu.duke.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    void printList_listOfEvents_success() throws DukeException {
        // Setting up
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);
        event1.markAsDone();
        LocalDateTime event2DateTime = Parser.convertDateTime("20-02-2022 2030");
        Event event2 = new Event("Funfair",
                "For charity", event2DateTime,
                "Parade square", 2000.10);
        ArrayList<Item> eventsList = new ArrayList<>();
        eventsList.add(event1);
        eventsList.add(event2);

        Ui.printList(eventsList);
        String expectedOutput = "1. [E][X] Peppa Pig's Concert (at: 19 Feb 2022 - 20:00)"
                + System.lineSeparator()
                + "2. [E][ ] Funfair (at: 20 Feb 2022 - 20:30)"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printList_listOfTasks_success() throws DukeException {
        // Setting up
        LocalDateTime task1Deadline = Parser.convertDateTime("19-02-2022 2359");
        Task task1 = new Task("Do homework", "CS2113T tp V1.0", task1Deadline);
        task1.markAsDone();
        LocalDateTime task2Deadline = Parser.convertDateTime("19-02-2022 0800");
        Task task2 = new Task("Waking up", "This is difficult...", task2Deadline);
        ArrayList<Item> tasksList = new ArrayList<>();
        tasksList.add(task1);
        tasksList.add(task2);

        Ui.printList(tasksList);
        String expectedOutput = "1. [T][X] Do homework (by: 19 Feb 2022 - 23:59)"
                + System.lineSeparator()
                + "2. [T][ ] Waking up (by: 19 Feb 2022 - 08:00)"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printList_listOfEventsAndTasks_success() throws DukeException {
        // Setting up
        LocalDateTime eventDateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event = new Event("Peppa Pig's Concert",
                "Asia world tour", eventDateTime,
                "Indoor Stadium", 1000.90);
        event.markAsDone();
        LocalDateTime taskDeadline = Parser.convertDateTime("19-02-2022 2359");
        Task task = new Task("Do homework", "CS2113T tp V1.0", taskDeadline);
        task.markAsDone();
        ArrayList<Item> combinedList = new ArrayList<>();
        combinedList.add(event);
        combinedList.add(task);

        Ui.printList(combinedList);
        String expectedOutput = "1. [E][X] Peppa Pig's Concert (at: 19 Feb 2022 - 20:00)"
                + System.lineSeparator()
                + "2. [T][X] Do homework (by: 19 Feb 2022 - 23:59)"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printEvent_oneEvent_success() throws DukeException {
        // Setting up
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);
        event.markAsDone();

        Ui.printEvent(event);
        String expectedOutput = "Title: Peppa Pig's Concert"
                + System.lineSeparator()
                + "Date: 19 Feb 2022 - 20:00"
                + System.lineSeparator()
                + "Description: Asia world tour"
                + System.lineSeparator()
                + "Venue: Indoor Stadium"
                + System.lineSeparator()
                + "Budget: $1000.9"
                + System.lineSeparator()
                + "Tasks: "
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printTask_oneTask_success() throws DukeException {
        // Setting up
        LocalDateTime taskDeadline = Parser.convertDateTime("19-02-2022 2359");
        Task task = new Task("Do homework", "CS2113T tp V1.0", taskDeadline);
        task.markAsDone();

        Ui.printTask(task);
        String expectedOutput = "Title: Do homework"
                + System.lineSeparator()
                + "Deadline: 19 Feb 2022 - 23:59"
                + System.lineSeparator()
                + "Description: CS2113T tp V1.0"
                + System.lineSeparator()
                + "Members: "
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
