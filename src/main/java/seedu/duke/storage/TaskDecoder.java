package seedu.duke.storage;

import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDecoder {

    private static final String TASK_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    private static final int INDEX_OF_TILE = 0;
    private static final int INDEX_OF_DEADLINE = 1;
    private static final int INDEX_OF_DESCRIPTION = 2;

    public static ArrayList<Task> decodeTaskList(List<String> encodedTaskList) {
        ArrayList<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return decodedTasks;
    }

    public static Task decodeTaskFromString(String encodedTask) {
        String[] taskDetails = encodedTask.trim().split(TASK_DATA_ARGS_DELIMITER);
        String taskTitle = taskDetails[INDEX_OF_TILE];
        String taskDeadline = taskDetails[INDEX_OF_DEADLINE];
        String taskDescription = taskDetails[INDEX_OF_DESCRIPTION];

        return new Task(taskTitle, taskDeadline, taskDescription);
    }
}
