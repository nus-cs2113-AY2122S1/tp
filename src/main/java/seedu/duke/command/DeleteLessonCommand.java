package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

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
    public void execute(Ui ui, TaskList taskList, LessonList lessonList) {
        if (isDeleteAll) {
            lessonList.clearLessonList();
        } else {
            lessonList.deleteLesson(lessonIndex);
        }
    }
}
