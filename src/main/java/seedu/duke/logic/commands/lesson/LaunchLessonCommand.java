package seedu.duke.logic.commands.lesson;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author richwill28
public class LaunchLessonCommand extends Command {
    private final int lessonIndex;

    public LaunchLessonCommand(int lessonIndex) {
        this.lessonIndex = lessonIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        Lesson lesson = lessonList.getLesson(lessonIndex);
        lesson.launchUrl();
        ui.printMessage("Launching URL...");
    }
}
