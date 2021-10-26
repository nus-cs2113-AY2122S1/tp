package seedu.duke.logic.commands.lesson;

import java.io.IOException;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author Roycius
public class AddLessonCommand extends Command {
    private final String title;
    private final String dayOfTheWeek;
    private final String startTime;
    private final String endTime;
    private final String meetingUrl;

    public AddLessonCommand(String title, String dayOfTheWeek, String startTime, String endTime, String meetingUrl) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingUrl = meetingUrl;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws IOException {
        Lesson newLesson = new Lesson(title, dayOfTheWeek, startTime, endTime, meetingUrl);
        lessonList.addLesson(newLesson);
        storage.saveData(lessonList);
        ui.printLessonAdded(newLesson, lessonList.getSize());
    }
}
