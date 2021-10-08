package seedu.duke.command;

import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
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
    public void execute(Ui ui, TaskList tasklist, LessonList lessonList) {
        lessonList.addLesson(new Lesson(title, dayOfTheWeek, startTime, endTime));
    }
}
