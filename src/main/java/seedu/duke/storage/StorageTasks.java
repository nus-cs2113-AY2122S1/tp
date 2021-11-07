package seedu.duke.storage;

import seedu.duke.schedule.task.Task;
import seedu.duke.schedule.task.TaskList;
import seedu.duke.schedule.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.constants.Messages.TODO;
import static seedu.duke.constants.Messages.INDEX_TODO_DESCRIPTION;
import static seedu.duke.constants.Messages.INDEX_TODO_DATE;
import static seedu.duke.constants.Messages.INDEX_ZERO;

//@@author swatimahadevan
public class StorageTasks {
    public static final String folderName = "storage/tasksdata/";
    public static final String fileName   = "scheduleTasks.txt";
    public static final String filePath = folderName + fileName;

    /**
     * Gets tasks from storage.
     *
     * @param data data from file.
     * @return tasks
     */
    public static ArrayList<Task> dataToTask(ArrayList<String> data) {
        ArrayList<Task> tasks = new ArrayList<>();
        int i = 0;
        int dataSize = data.size();
        while (i < dataSize) {
            String dataLine = data.get(i);
            String[] todoArguments = dataLine.split("\\|");
            if (TODO.equals(todoArguments[INDEX_ZERO])) {
                String description = todoArguments[INDEX_TODO_DESCRIPTION].trim();
                String date = todoArguments[INDEX_TODO_DATE].trim();
                tasks.add(new Todo(description, date));
            }
            i++;
        }
        return tasks;
    }

    /**
     * Writes task list to storage in string format.
     *
     * @param taskList list of tasks.
     * @throws IOException if command entered is wrong.
     */
    public static void writeTaskList(TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getTaskList();
        ArrayList<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(task.toSaveFileFormat());
        }
        Storage.writeDataOntoSaveFile(StorageTasks.filePath, data);
    }

    /**
     * Reads data in string format from storage and returns task list.
     *
     * @return task list
     * @throws NullPointerException in case if empty.
     * @throws IOException if command entered is wrong.
     */
    public static TaskList readTaskList() throws NullPointerException, IOException {
        TaskList tasksList = new TaskList();
        ArrayList<Task> tasks;
        try {
            Storage.checkAndAddDirectory(StorageTasks.folderName);
            ArrayList<String> data = Storage.loadDataFromSaveFile(StorageTasks.filePath);
            tasks = StorageTasks.dataToTask(data);
            for (int i = 0; i < tasks.size(); i++) {
                tasksList.addTask(tasks.get(i));
            }
            return tasksList;
        } catch (FileNotFoundException e) {
            File f = new File(StorageTasks.filePath);
        }
        return tasksList;
    }
}