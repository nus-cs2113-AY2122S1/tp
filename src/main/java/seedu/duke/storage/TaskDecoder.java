package seedu.duke.storage;

import seedu.duke.Parser;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDecoder {

    private static final int INDEX_OF_TILE = 0;
    private static final int INDEX_OF_DEADLINE = 1;
    private static final int INDEX_OF_DESCRIPTION = 2;

    public static ArrayList<Task> decodeTasksList(List<String> encodedTaskList) {
        ArrayList<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return decodedTasks;
    }

    private static Task decodeTaskFromString(String encodedTask) {
        String[] taskDetails = encodedTask.trim().split(Task.TASK_DATA_ARGS_DELIMITER);
        String taskTitle = taskDetails[INDEX_OF_TILE];
        LocalDateTime taskDeadline = Parser.convertDateTime(taskDetails[INDEX_OF_DEADLINE]);
        String taskDescription = taskDetails[INDEX_OF_DESCRIPTION];

        return new Task(taskTitle, taskDescription, taskDeadline);
    }
}
