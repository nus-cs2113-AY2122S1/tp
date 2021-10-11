package seedu.duke.storage;

import seedu.duke.Parser;
import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskEncoder {

    public static List<String> encodeTasksList(ArrayList<Task> tasksToSave) {
        List<String> encodedTasks = new ArrayList<>();
        tasksToSave.forEach(task -> encodedTasks.add(encodeTaskToString(task)));
        return encodedTasks;
    }

    public static String encodeTaskToString(Task task) {
        return task.getTitle()
                + " | "
                + task.getStatusIcon()
                + " | "
                + task.getDescription()
                + " | "
                + Parser.convertDateTimeForSaving(task.getDateTime());
    }
}
