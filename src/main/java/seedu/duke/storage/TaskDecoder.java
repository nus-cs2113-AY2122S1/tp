package seedu.duke.storage;

import seedu.duke.Parser;
import seedu.duke.items.Task;

import java.time.LocalDateTime;

public class TaskDecoder {

    // Index 0 is reserved for indicator for event/task
    private static final int INDEX_OF_TILE = 1;
    private static final int INDEX_OF_STATUS = 2;
    private static final int INDEX_OF_DESCRIPTION = 3;
    private static final int INDEX_OF_DEADLINE = 4;

    public static Task decodeTaskFromString(String encodedTask) {
        String[] taskDetails = encodedTask.trim().split(Task.TASK_DATA_ARGS_DELIMITER);
        String taskTitle = taskDetails[INDEX_OF_TILE];
        String taskStatus = taskDetails[INDEX_OF_STATUS];
        String taskDescription = taskDetails[INDEX_OF_DESCRIPTION];
        LocalDateTime taskDeadline = Parser.convertDateTime(taskDetails[INDEX_OF_DEADLINE]);

        Task task = new Task(taskTitle, taskDescription, taskDeadline);
        if (taskStatus.equals("X")) {
            task.markAsDone();
        }
        return task;
    }
}
