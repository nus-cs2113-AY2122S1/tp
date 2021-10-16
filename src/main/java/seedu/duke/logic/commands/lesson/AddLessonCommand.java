package seedu.duke.logic.commands.lesson;

import java.io.IOException;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.DukeException;
import seedu.duke.ui.Ui;

public class AddLessonCommand extends Command {
    private final String title;
    private final String dayOfTheWeek;
    private final String startTime;
    private final String endTime;

    public AddLessonCommand(String title, String dayOfTheWeek, String startTime, String endTime) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList)
            throws DukeException, IOException {
        Lesson newLesson = new Lesson(title, dayOfTheWeek, startTime, endTime);
        lessonList.addLesson(newLesson);
        storage.saveData(taskList, lessonList, "lessons.txt");
        ui.printLessonAdded(newLesson, lessonList.getSize());
    }
}
