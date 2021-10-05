package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class AddTaskCommand extends Command {
    private String title;
    private String dayOfTheWeek;
    private String information;

    public AddTaskCommand(String title, String dayOfTheWeek, String information) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = information;
    }

    public void execute(Ui ui, TaskList tasklist, LessonList lessonList) {
        tasklist.addTask(new Task(title, dayOfTheWeek, information));
    }
}
