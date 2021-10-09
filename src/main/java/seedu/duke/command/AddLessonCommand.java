package seedu.duke.command;

import java.io.IOException;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class AddLessonCommand extends AddCommand {
    private String startTime;
    private String endTime;

    public AddLessonCommand(String title, String dayOfTheWeek, String startTime, String endTime) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList, Storage storage) throws DukeException {
        Lesson newLesson = new Lesson(title, dayOfTheWeek, startTime, endTime);
        lessonList.addLesson(newLesson);
        ui.printLessonAdded(newLesson, lessonList.getSize());
        try {
            storage.saveData(taskList, lessonList);
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }
}
