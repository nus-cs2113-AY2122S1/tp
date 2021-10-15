package seedu.duke.logic.commands.lesson;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteLessonCommand extends Command {
    private final int lessonIndex;

    public DeleteLessonCommand(int lessonIndex) {
        this.lessonIndex = lessonIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList)
            throws DukeException, IOException {
        Lesson deletedLesson = lessonList.getLesson(lessonIndex);
        lessonList.deleteLesson(lessonIndex);
        storage.saveData(taskList, lessonList);
        ui.printLessonDeleted(deletedLesson, lessonList.getSize());
    }
}
