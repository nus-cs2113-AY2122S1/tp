package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class AddTaskCommand extends AddCommand {
    private String information = "";

    public AddTaskCommand(String title, String dayOfTheWeek) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public AddTaskCommand(String title, String dayOfTheWeek, String information) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = information;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList) throws DukeException {
        Task newTask = new Task(title, dayOfTheWeek, information);
        taskList.addTask(newTask);
        ui.printTaskAdded(newTask, taskList.getSize());
        Storage storage = new Storage();
        try {
            StringBuilder dataToWrite = new StringBuilder();
            dataToWrite.append(taskList.serialize()).append(lessonList.serialize());
            storage.saveData(dataToWrite.toString());
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }
}
