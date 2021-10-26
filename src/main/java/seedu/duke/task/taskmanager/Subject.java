package seedu.duke.task.taskmanager;

import java.util.ArrayList;
import java.util.List;

//@@author SeanRobertDH
interface Subject {
    ArrayList<TaskManagerObserver> observers = new ArrayList<>();

    default void addObserver(TaskManagerObserver observer) {
        observers.add(observer);
    }

    default List<TaskManagerObserver> getObservers() {
        return observers;
    }


    default void updateObservers(TaskManager taskManager) {
        for (TaskManagerObserver observer : observers) {
            observer.update(taskManager);
        }
    }
}
