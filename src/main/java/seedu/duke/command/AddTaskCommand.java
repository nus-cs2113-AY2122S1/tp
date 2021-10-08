package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

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
    public void execute(Ui ui, TaskList tasklist, LessonList lessonList) {
        Task newTask = new Task(title, dayOfTheWeek, information);
        tasklist.addTask(newTask);
        ui.printTaskAdded(newTask, tasklist.getSize());
    }
}
