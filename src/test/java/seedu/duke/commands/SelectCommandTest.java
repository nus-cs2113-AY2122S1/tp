package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.Duke.eventCatalog;

class SelectCommandTest {


    @Test
    void selectTaskUnderFirstEvent_TwoEventsWithOneTaskEach_correctTaskOutput() throws DukeException,
            NoCommandAttributesException, InvalidBudgetException, InvalidItemTypeException {
        createEventsList();
        Command command1 = Parser.parseCommand("select -e 1");
        CommandResult feedback1 = command1.execute();
        assertTrue(feedback1.feedbackToUser.contains("Peppa Pig's Concert"));

        Command command2 = Parser.parseCommand("select -t 1");
        CommandResult feedback2 = command2.execute();
        assertTrue(feedback2.feedbackToUser.contains("Hype myself up"));
        eventCatalog.clear();
    }


    private void createEventsList() throws DukeException {
        eventCatalog.clear();
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
        eventCatalog.add(event1);
        eventCatalog.add(event2);
    }
}