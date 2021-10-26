package seedu.duke.local;

import seedu.duke.storage.TaskFileWriter;
import seedu.duke.task.taskmanager.TaskManager;

import java.util.ArrayList;

public class DataManager {
    private static ArrayList<TaskLine> taskLines = new ArrayList<>(128);

    public DataManager() {

    }

    public static void setUpDataManager(TaskManager taskManager) {
        for (int i = 0; i < taskManager.getTaskListSize(); i++) {
            taskLines.add(TasktoLineConverter.convertTaskToLine(taskManager.getTask(i)));
        }
    }

    public static ArrayList<TaskLine> getLineList() {
        return taskLines;
    }

    public static int getSize() {
        return taskLines.size();
    }

    public static TaskLine getTaskLine(int index) {
        return taskLines.get(index);
    }

    public static ArrayList<String> getStringLineList() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            strings.add(taskLines.get(i).getString());
        }
        return strings;
    }

    public static void addTaskLine(TaskLine taskLine) {
        taskLines.add(taskLine);
        updateStorage();
    }

    public static void updateStorage() {
        TaskFileWriter.storetoHardDisk(getStringLineList());
    }

    public static void deleteTask(int index){
        if (index >= 0 && index < getSize()) {
            taskLines.remove(index);
        }
        updateStorage();
    }

    public static void clear() {
        taskLines.clear();
        updateStorage();
    }

    public static void updateReminderTime (int index, long time) {
        taskLines.get(index).updateTime(time);
        updateStorage();
    }

    public static void updateReminderMessage (int index, String message) {
        taskLines.get(index).updateMessage(message);
        updateStorage();
    }
}
