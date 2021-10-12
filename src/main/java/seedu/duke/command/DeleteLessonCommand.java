package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteLessonCommand extends DeleteCommand {
    private int lessonIndex;

    public DeleteLessonCommand() {
        this.isDeleteAll = true;
    }

    public DeleteLessonCommand(int lessonIndex) {
        this.isDeleteAll = false;
        this.lessonIndex = lessonIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList)
            throws DukeException, IOException {
        if (isDeleteAll) {
            lessonList.clearLessonList();
            ui.printDeletedAllLessons();
        } else {
            Lesson deletedLesson = lessonList.getLesson(lessonIndex);
            lessonList.deleteLesson(lessonIndex);
            ui.printDeletedLesson(deletedLesson, lessonList.getSize());
        }
        storage.saveData(taskList, lessonList);
    }
}
