package seedu.duke.task;

import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.log.Log;

import java.util.ArrayList;

public class TaskManager {

    private static final int STARTING_SIZE = 128;

    private static ArrayList<Task> taskList = new ArrayList<>(128);

    public static String listTasklist() throws EmptyTasklistException {
        Log.info("listTasklist method called");
        assert taskList.size() >= 0 : "Tasklist cannot be negative";

        if (taskList.size() == 0) {
            Log.warning("tasklist is empty, throwing EmptyTasklistException");
            throw new EmptyTasklistException();
        }

        String tasks = "-------------\n"
            + " MY TASKLIST\n"
            + "-------------\n";

        for (int i = 0; i < taskList.size(); i++) {
            tasks += i + 1 + ". " + taskList.get(i).getTaskEntryDescription() + "\n";
        }

        Log.info("end of listTasklist - no issues detected");
        return tasks;
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static int getTaskListSize() {
        return taskList.size();
    }

    public static Task getTask(int index) {
        return taskList.get(index);
    }

    public static void addTask(Task task) {
        taskList.add(task);
    }

}
