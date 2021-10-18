package seedu.duke.storage;

import seedu.duke.Parser;
import seedu.duke.items.Event;
import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.List;

public class EventEncoder {

    public static List<String> encodeEventsList(ArrayList<Event> eventsToSave) {
        List<String> encodedEvents = new ArrayList<>();
        eventsToSave.forEach(event -> encodedEvents.addAll(encodeEventToString(event)));
        return encodedEvents;
    }

    private static List<String> encodeEventToString(Event event) {
        List<String> encodedEvent = new ArrayList<>();
        encodedEvent.add("e | "
                + event.getTitle()
                + " | "
                + event.getStatusIcon()
                + " | "
                + event.getDescription()
                + " | "
                + Parser.convertDateTimeForSaving(event.getDateTime())
                + " | "
                + event.getVenue()
                + " | "
                + event.getBudget());

        // Append the additional subtasks to the current event as new lines
        List<String> encodedSubTasks = encodeSubTasksToString(event);
        encodedEvent.addAll(encodedSubTasks);

        return encodedEvent;
    }

    private static List<String> encodeSubTasksToString(Event event) {
        ArrayList<Task> tasksToEncode = event.getTaskList();
        return TaskEncoder.encodeTasksList(tasksToEncode);
    }
}
