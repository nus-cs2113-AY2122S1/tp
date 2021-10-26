package seedu.duke.logic.commands.task;

import java.io.IOException;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

//@@author Roycius
public class AddTaskCommand extends Command {
    private final String title;
    private final String dayOfTheWeek;
    private final String information;
    private final String priority;

    public AddTaskCommand(String title, String dayOfTheWeek, String priority, String information) {
        assert information != null : Ui.PADDING + "Information should not be null.";
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.priority = priority;
        this.information = information;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws IOException {
        Task newTask = new Task(title, dayOfTheWeek, priority, information);
        taskList.addTask(newTask);
        storage.saveData(taskList);
        ui.printTaskAdded(newTask, taskList.getSize());
    }
}
