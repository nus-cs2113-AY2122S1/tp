package seedu.duke.command;

import java.io.IOException;

import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class AddLessonCommand extends AddCommand {
    private final String startTime;
    private final String endTime;

    public AddLessonCommand(String title, String dayOfTheWeek, String startTime, String endTime) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) throws IOException {
        Lesson newLesson = new Lesson(title, dayOfTheWeek, startTime, endTime);
        lessonList.addLesson(newLesson);
        storage.saveData(taskList, lessonList);
        ui.printLessonAdded(newLesson, lessonList.getSize());
    }
}
