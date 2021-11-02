package seedu.duke.local;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataManagerTest {
    private ArrayList<String> expectedOut = new ArrayList<>();
    Task todoReminder;
    Task todoNoReminder;
    Task deadline;
    Task event;
    private static final String DATE1 = "2021-10-30T02:00";
    private static final String DATE2 = "2021-10-30T05:00";

    LocalDateTime startDate = LocalDateTime.parse(DATE1);
    LocalDateTime endDate = LocalDateTime.parse(DATE2);

    DataManagerTest(){
    }

    @Test
    void getStringLineList() {
        todoReminder = new Todo("go jogging",PriorityEnum.LOW, startDate, RecurrenceEnum.DAILY);
        todoNoReminder = new Todo("go jogging");
        deadline = new Deadline("return book", startDate, RecurrenceEnum.WEEKLY);
        event = new Event("project meeting", startDate, endDate, RecurrenceEnum.MONTHLY);
        DataManager.addTaskLine(TasktoLineConverter.convertTaskToLine(todoReminder));
        DataManager.addTaskLine(TasktoLineConverter.convertTaskToLine(todoNoReminder));
        DataManager.addTaskLine(TasktoLineConverter.convertTaskToLine(deadline));
        DataManager.addTaskLine(TasktoLineConverter.convertTaskToLine(event));

        expectedOut.add("todo go jogging --priority low --recur daily --doOn 30/10/2021 02:00"
                + System.lineSeparator()
                + "reminder 1 --time 10 --message Reminder! 10 min before the following task:");
        expectedOut.add("todo go jogging --priority medium");
        expectedOut.add("deadline return book --priority medium --recur weekly --due 2021-10-30T02:00"
                + System.lineSeparator()
                + "reminder 3 --time 10 --message Reminder! 10 min before the following task:");
        expectedOut.add("event project meeting --priority medium --recur monthly "
                + "--start 2021-10-30T02:00 --end 2021-10-30T05:00"
                + System.lineSeparator()
                + "reminder 4 --time 10 --message Reminder! 10 min before the following task:");

        ArrayList<String> results = DataManager.getStringLineList();

        assertEquals(expectedOut, results);
    }
}

