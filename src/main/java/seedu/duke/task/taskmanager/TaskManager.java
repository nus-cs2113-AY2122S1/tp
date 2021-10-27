package seedu.duke.task.taskmanager;

import seedu.duke.command.Command;
import seedu.duke.command.flags.ListFlag;
import seedu.duke.command.flags.SortFlag;
import seedu.duke.exception.EmptySortCriteriaException;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.InvalidTaskIndexException;
import seedu.duke.exception.ListFormatException;
import seedu.duke.exception.MissingFilterArgumentException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.exception.SortFormatException;
import seedu.duke.exception.StartDateAfterEndDateException;
import seedu.duke.log.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.Task;

public class TaskManager implements Subject {

    static final int STARTING_SIZE = 128;

    private static final String LIST_HEADER = "-------------\n"
        + " MY TASKLIST\n"
        + "-------------\n";

    private List<Task> taskList;
    private List<Task> latestFilteredList;

    public TaskManager(TaskManagerObserver... observers) {
        taskList = new ArrayList<>(STARTING_SIZE);
        latestFilteredList = new ArrayList<>(STARTING_SIZE);

        for (TaskManagerObserver observer : observers) {
            addObserver(observer);
        }
    }

    //@@author APZH
    public String listTasklist(Map<String, String> filter) throws EmptyTasklistException,
        ListFormatException, MissingFilterArgumentException {
        assert taskList.size() >= 0 : "Tasklist cannot be negative";
        if (taskList.size() == 0) {
            Log.warning("tasklist is empty, throwing EmptyTasklistException");
            throw new EmptyTasklistException();
        }
        String taskEntries = "";
        List<Task> filteredTasks = new ArrayList<>(taskList);
        for (HashMap.Entry<String, String> entry : filter.entrySet()) {
            String flag = entry.getKey();
            String argument = entry.getValue();
            if (flag.equals(Command.MAIN_ARGUMENT)) {
                continue;
            }
            switch (flag) {
            case ListFlag.TASK_TYPE:
                filteredTasks = filterListByTaskType(filteredTasks, argument);
                break;
            case ListFlag.PRIORITY:
                filteredTasks = filterListByPriority(filteredTasks, argument);
                break;
            case ListFlag.RECURRENCE:
                filteredTasks = filterListByRecurrence(filteredTasks, argument);
                break;
            default:
                throw new ListFormatException();
            }
        }
        updateFilteredTaskList(filteredTasks);
        for (int i = 0; i < filteredTasks.size(); i++) {
            taskEntries += i + 1 + ". " + filteredTasks.get(i).getTaskEntryDescription() + "\n";
        }
        return LIST_HEADER + taskEntries;
    }

    private List<Task> filterListByTaskType(List<Task> taskList, String taskTypeFilter)
        throws MissingFilterArgumentException {
        if (taskTypeFilter.isEmpty()) {
            throw new MissingFilterArgumentException();
        }
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String currentTaskType = taskList.get(i).getTaskType().name();
            if (currentTaskType.equalsIgnoreCase(taskTypeFilter)) {
                filteredTasks.add(taskList.get(i));
            }
        }
        return filteredTasks;
    }

    private List<Task> filterListByPriority(List<Task> taskList, String priorityFilter)
        throws MissingFilterArgumentException {
        if (priorityFilter.isEmpty()) {
            throw new MissingFilterArgumentException();
        }
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String currentPriority = taskList.get(i).getPriority().name();
            if (currentPriority.equalsIgnoreCase(priorityFilter)) {
                filteredTasks.add(taskList.get(i));
            }
        }
        return filteredTasks;
    }

    private List<Task> filterListByRecurrence(List<Task> taskList, String recurrenceFilter)
        throws MissingFilterArgumentException {
        if (recurrenceFilter.isEmpty()) {
            throw new MissingFilterArgumentException();
        }
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String currentRecurrence = taskList.get(i).getRecurrence().name();
            if (currentRecurrence.equalsIgnoreCase(recurrenceFilter)) {
                filteredTasks.add(taskList.get(i));
            }
        }
        return filteredTasks;
    }

    //@@author APZH
    public String sortTasklist(Map<String, String> criteria) throws EmptyTasklistException,
        SortFormatException, EmptySortCriteriaException {
        Log.info("sortTasklist method called");
        String sortCriteria = "";

        if (getTaskListSize() == 0) {
            Log.warning("tasklist is empty, throwing EmptyTasklistException");
            throw new EmptyTasklistException();
        }
        if (criteria.containsKey(SortFlag.SORT_BY)) {
            sortCriteria = criteria.get(SortFlag.SORT_BY);
        } else {
            Log.warning("user did not indicate 'by' flag, throwing SortFormatException");
            throw new SortFormatException();
        }
        if (sortCriteria.isEmpty()) {
            Log.warning("user did not indicate any sort criteria, throwing EmptySortCriteriaException");
            throw new EmptySortCriteriaException();
        }

        switch (sortCriteria) {
        case "type":
            SortByTaskType sortByTaskType = new SortByTaskType();
            Collections.sort(taskList, sortByTaskType);
            break;
        case "description":
            SortByDescription sortByDescription = new SortByDescription();
            Collections.sort(taskList, sortByDescription);
            break;
        case "priority":
            SortByPriority sortByPriority = new SortByPriority();
            Collections.sort(taskList, sortByPriority);
            break;
        default:
            return "The sort criteria entered is not valid";
        }

        Log.info("end of sortTasklist - no issues detected");
        updateObservers(this);
        return "[!] Tasklist has been sorted by " + sortCriteria;
    }

    //@@author APZH
    private class SortByTaskType implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.getTaskType().name().compareTo(o2.getTaskType().name());
        }
    }

    //@@author APZH
    private class SortByDescription implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.getDescription().compareTo(o2.getDescription());
        }
    }

    //@@author APZH
    private class SortByPriority implements Comparator<Task> {
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

    //@@author SeanRobertDH
    public int getTaskListSize() {
        return taskList.size();
    }

    //@@author SeanRobertDH
    public boolean isEmpty() {
        return getTaskListSize() == 0;
    }

    //@@author SeanRobertDH
    public Task getTask(int index) {
        return taskList.get(index);
    }

    //@@author SeanRobertDH
    public void addTask(Task task) {
        taskList.add(task);
        updateObservers(this);
    }

    public void addTasks(List<Task> tasks) {
        taskList.addAll(tasks);
        updateObservers(this);
    }

    //@@author SeanRobertDH
    public void checkFilteredListIndexValid(int index) throws InvalidTaskIndexException {
        if (latestFilteredList.isEmpty()) {
            latestFilteredList = taskList;
        }
        if (index < 0 || index > latestFilteredList.size() - 1) {
            throw new InvalidTaskIndexException(++index);
        }
    }

    //@@author SeanRobertDH
    public Task deleteFilteredTask(int index) throws InvalidTaskIndexException {
        checkFilteredListIndexValid(index);
        Task deletedTask = latestFilteredList.remove(index);
        taskList.remove(deletedTask);
        updateObservers(this);
        return deletedTask;
    }

    //@@author SeanRobertDH
    private void updateFilteredTaskList(List<Task> replacementTaskList) {
        latestFilteredList = replacementTaskList;
    }

    //@@author SeanRobertDH
    public Task editFilteredTask(int index, Map<String, String> arguments)
        throws InvalidTaskIndexException, InvalidPriorityException,
            InvalidRecurrenceException, ParseDateFailedException, StartDateAfterEndDateException {
        checkFilteredListIndexValid(index);
        latestFilteredList.get(index).edit(arguments);
        return latestFilteredList.get(index);
    }

    /*
    //@@author SeanRobertDH
    public void checkIndexValid(int index) throws InvalidTaskIndexException {
        if (index < 0 || index > getTaskListSize() - 1) {
            throw new InvalidTaskIndexException(++index);
        }
    }

    //@@author SeanRobertDH
    public Task deleteTask(int index) throws InvalidTaskIndexException {
        checkIndexValid(index);
        Task deletedTask = taskList.remove(index);
        updateObservers(this);
        return deletedTask;
    }
    */

    //@@author SeanRobertDH
    public void clear() {
        taskList.clear();
    }
}
