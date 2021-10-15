package seedu.duke.logic.commands.task;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class AddTaskCommand extends Command {
    private final String title;
    private final String dayOfTheWeek;
    private final String information;

    public AddTaskCommand(String title, String dayOfTheWeek, String information) {
        assert information != null : Ui.PADDING + "Information should not be null.";
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = information;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) throws IOException {
        Task newTask = new Task(title, dayOfTheWeek, information);
        taskList.addTask(newTask);
        storage.saveData(taskList, lessonList);
        ui.printTaskAdded(newTask, taskList.getSize());
    }
}
