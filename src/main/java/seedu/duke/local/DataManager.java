package seedu.duke.local;

import seedu.duke.storage.TaskFileWriter;
import seedu.duke.task.TaskManager;

import java.util.ArrayList;

public class DataManager {
    private static ArrayList<TaskLine> taskLines = new ArrayList<>(128);

    public DataManager(TaskManager taskManager) {
        for (int i = 0; i < TaskManager.getTaskListSize(); i++) {
            taskLines.add(TasktoLineConverter.convertTaskToLine(TaskManager.getTask(i)));
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
    }

    public static void updateStorage() {
        TaskFileWriter.storetoHardDisk(getStringLineList());
    }

    public static void deleteTask(int index){
        taskLines.remove(index);
    }

    public static void clear() {
        taskLines.clear();
    }
}
