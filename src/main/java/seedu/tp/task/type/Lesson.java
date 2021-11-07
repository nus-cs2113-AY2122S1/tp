package seedu.tp.task.type;

import seedu.tp.command.flags.LessonFlag;
import seedu.tp.parser.DateParser;
import seedu.tp.task.TypeEnum;
import seedu.tp.nusmods.Semester;
import seedu.tp.task.RecurrenceEnum;

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

    private void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    private String moduleCode;

    public String getClassNumber() {
        return classNumber;
    }

    private void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    private String classNumber;

    @Override
    protected void taskEdit(Map<String, String> arguments) throws URISyntaxException {
        if (arguments.containsKey(LessonFlag.LINK)) {
            setLink(new URI(arguments.get(LessonFlag.LINK)));
        }
    }

    public Lesson(String moduleCode, String classNumber, DayOfWeek dayOfWeek, LocalTime start, LocalTime end, int[] weeks) {
        super(moduleCode, Semester.getSemester().getStartingMonday().with(dayOfWeek).atTime(start),
                Semester.getSemester().getStartingMonday().with(dayOfWeek).atTime(end),
                RecurrenceEnum.WEEKLY);
        setModuleCode(moduleCode);
        setClassNumber(classNumber);
        setOccurrences(weeks);
    }

    private void setOccurrences(int[] occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public String getTaskEntryDescription() {
        return LESSON_ICON + " " + this.getModuleCode() + ' ' + this.getClassNumber() + ": "
                + DateParser.dateToString(getStartDate())
                + " to " + DateParser.dateToString(getEndDate());
    }

    @Override
    protected LessonFlag getTaskFlag() {
        return new LessonFlag();
    }
}
