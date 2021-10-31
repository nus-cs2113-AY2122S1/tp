package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;
import seedu.duke.items.Event;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventEncoderTest {

    @Test
    void encodeEventsList_listOfTwoEvents_expectListOfTwoStrings() throws DukeException {
        // Setting up
        ArrayList<Event> eventsList = createEventsList();
        List<String> encodedEventsList = EventEncoder.encodeEventsList(eventsList);

        // Check number of items in encodedEventsList
        assertEquals(4, encodedEventsList.size());

        // Check the event is saved in the correct String format
        String expectedEvent1 = "e | Peppa Pig's Concert | X | Asia world tour "
                + "| 19-02-2022 2000 | Indoor Stadium | 1000.9";
        assertEquals(expectedEvent1, encodedEventsList.get(0));
        String expectedSubTask1 = "t | Hype myself up | X | Drink lots of sugar | 19-02-2022 1950 | Biggest_Fan";
        assertEquals(expectedSubTask1, encodedEventsList.get(1));
        String expectedSubTask2 = "t | Enter venue |   |  | 19-02-2022 1955 | Biggest_Fan";
        assertEquals(expectedSubTask2, encodedEventsList.get(2));
        String expectedEvent2 = "e | Funfair |   | For charity | 20-02-2022 2030 | Parade square | 2000.1";
        assertEquals(expectedEvent2, encodedEventsList.get(3));
    }

    /**
     * Generates a list of events for testing.
     * Event 1 contains 2 sub-tasks, the first contains all fields whilst
     * the second contains an empty description fields.
     * Event 2 contains no sub-tasks.
     *
     * @return The generated list of events for testing with 2 events and 2 tasks in total.
     */
    private ArrayList<Event> createEventsList() throws DukeException {
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);

        LocalDateTime task1DateTime = Parser.convertDateTime("19-02-2022 1950");
        Task task1 = new Task("Hype myself up", "Drink lots of sugar", task1DateTime);
        task1.memberList.add(new Member("Biggest Fan"));
        task1.markAsDone();
        event1.addToTaskList(task1);
        event1.getFromTaskList(0).markAsDone();

        LocalDateTime task2DateTime = Parser.convertDateTime("19-02-2022 1955");
        Task task2 = new Task("Enter venue", "", task2DateTime);
        task2.memberList.add(new Member("Biggest Fan"));
        event1.addToTaskList(task2);
        event1.markAsDone();

        LocalDateTime event2DateTime = Parser.convertDateTime("20-02-2022 2030");
        Event event2 = new Event("Funfair",
                "For charity", event2DateTime,
                "Parade square", 2000.10);
        ArrayList<Event> eventsList = new ArrayList<>();
        eventsList.add(event1);
        eventsList.add(event2);

        return eventsList;
    }
}
