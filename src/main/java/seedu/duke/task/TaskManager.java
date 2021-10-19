package seedu.duke.task;

import seedu.duke.command.flags.SortFlag;
import seedu.duke.exception.EmptySortCriteriaException;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.SortFormatException;
import seedu.duke.log.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

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

    public static String sortTasklist(HashMap<String, String> criteria) throws EmptyTasklistException,
            SortFormatException, EmptySortCriteriaException {
        Log.info("sortTasklist method called");
        String sortCriteria = "";

        if (taskList.size() == 0) {
            Log.warning("tasklist is empty, throwing EmptyTasklistException");
            throw new EmptyTasklistException();
        }
        if (criteria.containsKey(SortFlag.SORT_BY)) {
            Log.warning("user did not indicate 'by' flag, throwing SortFormatException");
            sortCriteria = criteria.get(SortFlag.SORT_BY);
        } else {
            throw new SortFormatException();
        }
        if (sortCriteria.isEmpty()) {
            Log.warning("user did not indicate any sort criteria, throwing EmptySortCriteriaException");
            throw new EmptySortCriteriaException();
        }

        switch (sortCriteria) {
        case "description":
            SortByDescription sortByDescription = new SortByDescription();
            Collections.sort(taskList, sortByDescription);
            return "Sorted Tasklist by Description";
        case "priority":
            SortByPriority sortByPriority = new SortByPriority();
            Collections.sort(taskList, sortByPriority);
            return "Sorted Tasklist by Priority";
        default:
            return "The sort criteria entered is not valid";
        }
    }

    public static class SortByDescription implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {

            return o1.getDescription().compareTo(o2.getDescription());

        }
    }

    public static class SortByPriority implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {

            if (o1.getPriority().equals(PriorityEnum.LOW) && (o2.getPriority().equals(PriorityEnum.MEDIUM)
                    || o2.getPriority().equals(PriorityEnum.HIGH))) {
                return -1;
            }

            if (o1.getPriority().equals(PriorityEnum.MEDIUM) && o2.getPriority().equals(PriorityEnum.HIGH)) {
                return -1;
            }

            if (o1.getPriority().equals(PriorityEnum.HIGH) && (o2.getPriority().equals(PriorityEnum.MEDIUM)
                    || o2.getPriority().equals(PriorityEnum.LOW))) {
                return 1;
            }

            if (o1.getPriority().equals(PriorityEnum.MEDIUM) && o2.getPriority().equals(PriorityEnum.LOW)) {
                return 1;
            }

            // Returns 0 if both priorities are equal
            return 0;
        }
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
