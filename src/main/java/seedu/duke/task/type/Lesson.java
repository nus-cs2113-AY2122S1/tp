package seedu.duke.task.type;

import seedu.duke.nusmods.NusModsParser;
import seedu.duke.task.Task;

import java.io.IOException;

public class Lesson extends Task {
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

    @Override
    public String getTaskEntryDescription() {
        return moduleCode + ' ' + classNo;
    }

    public Lesson(String moduleCode, String classNo) {
        super(moduleCode + ' ' + classNo);
        this.moduleCode = moduleCode;
        this.classNo = classNo;
    }
}
