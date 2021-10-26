package seedu.duke.task.type;

import seedu.duke.nusmods.NusModsParser;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.reminder.ReminderInformation;

import java.io.IOException;
import java.time.LocalDateTime;

public class Lesson extends Task {

    private final TypeEnum taskType = TypeEnum.LESSON;

    public String getModuleCode() {
        return moduleCode;
    }

    public String getClassNo() {
        return classNo;
    }

    private final String moduleCode;
    private final String classNo;

    public Event[] toEvent() {
        try {
            return new NusModsParser().getLessonEvents(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public TypeEnum getTaskType() {
        return this.taskType;
    }

    @Override
    public String getTaskEntryDescription() {
        return moduleCode + ' ' + classNo;
    }

    public Lesson(String moduleCode, String classNo) {
        super(moduleCode + ' ' + classNo);
        this.moduleCode = moduleCode;
        this.classNo = classNo;
    }

    @Override
    public boolean needReminder() {
        return false;
    }

    @Override
    public String getReminder(LocalDateTime now) {
        return null;
    }

    @Override
    public void updateReminderMessage(String message) {

    }

    @Override
    public void updateReminderTime(long reminderTime) {

    }

    @Override
    public ReminderInformation getReminderInformation() {
        return null;
    }
}
