package seedu.duke.logic.commands;

import seedu.duke.DukeException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteAllCommand extends DeleteCommand {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList)
            throws DukeException, IOException {
        taskList.deleteAllTasks();
        lessonList.deleteAllLessons();
        assert taskList.isEmpty() : Ui.PADDING + "Task list should be empty";
        assert lessonList.isEmpty() : Ui.PADDING + "Lesson list should be empty";
        storage.saveData(taskList, lessonList);
        ui.printMessage("All your tasks and lessons have been successfully removed.");
    }
}
