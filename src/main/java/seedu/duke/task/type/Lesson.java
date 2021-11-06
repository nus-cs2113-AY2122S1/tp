package seedu.duke.task.type;

import seedu.duke.command.flags.LessonFlag;
import seedu.duke.parser.DateParser;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.reminder.ReminderInformation;
import seedu.duke.nusmods.Semester;
import seedu.duke.task.RecurrenceEnum;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

/**
 * A single lesson class e.g. CS2101 C02 Thursday
 */
public class Lesson extends Event {

    private static final String LESSON_ICON = "[L]";
    private static final TypeEnum TASK_TYPE = TypeEnum.LESSON;
    private int[] occurrences;

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }

    private URI link;

    public String getModuleCode() {
        return moduleCode;
    }

    public TypeEnum getTaskType() {
        return this.TASK_TYPE;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    private String moduleCode;

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    private String classNo;

    @Override
    protected void taskEdit(Map<String, String> arguments) throws URISyntaxException {
        if (arguments.containsKey(LessonFlag.LINK)) {
            setLink(new URI(arguments.get(LessonFlag.LINK)));
        }
    }

    public Lesson(String moduleCode, String classNo, DayOfWeek weekday, LocalTime start, LocalTime end, int[] weeks) {
        super(moduleCode, Semester.getSemester().getStartingMonday().with(weekday).atTime(start),
                Semester.getSemester().getStartingMonday().with(weekday).atTime(end),
                RecurrenceEnum.WEEKLY);
        setModuleCode(moduleCode);
        setClassNo(classNo);
        setOccurrences(weeks);
    }

    public void setOccurrences(int[] occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public String getTaskEntryDescription() {
        return LESSON_ICON + " " + this.getModuleCode() + ' ' + this.getClassNo() + ": "
                + DateParser.dateToString(getStartDate())
                + " to " + DateParser.dateToString(getEndDate());
    }

    @Override
    protected LessonFlag getTaskFlag() {
        return new LessonFlag();
    }
}
