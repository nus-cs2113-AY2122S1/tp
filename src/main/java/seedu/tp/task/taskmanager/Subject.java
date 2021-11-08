package seedu.tp.task.taskmanager;

import java.util.ArrayList;

//@@author SeanRobertDH
/**
 * Subject interface used by {@link seedu.tp.task.taskmanager.TaskManager}
 * to be observed by {@link seedu.tp.storage.DataManager}.
 * Uses Observer design pattern.
 */
interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer) {
        observers.add(observer);
    }

    default void updateObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
