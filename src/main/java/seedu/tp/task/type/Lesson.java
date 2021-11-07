package seedu.tp.task.type;

import seedu.tp.command.flags.LessonFlag;
import seedu.tp.exception.NoLinkException;
import seedu.tp.log.Log;
import seedu.tp.parser.DateParser;
import seedu.tp.task.TypeEnum;
import seedu.tp.nusmods.Semester;
import seedu.tp.task.RecurrenceEnum;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

//@@author SuibianP
/**
 * A single lesson class e.g. CS2101 C02 Thursday
 */
public class Lesson extends Event {

    private static final String LESSON_ICON = "[L]";
    private static final TypeEnum TASK_TYPE = TypeEnum.LESSON;

    public LocalDateTime getNextOccurrence() {
        LocalDateTime nearest = Arrays.stream(occurrences).mapToObj(occurrence -> getStartDate()
                .plusWeeks(occurrence - 1))
                .filter(date -> date.isAfter(LocalDateTime.now()))
                .min(LocalDateTime::compareTo)
                .orElseThrow();
        Log.info(Arrays.toString(occurrences));
        Log.info(nearest.toString());
        return nearest;
    }

    /**
     * 1-based occurrence.
     */
    private int[] occurrences;

    public boolean hasLink() {
        return link != null;
    }

    public URI getLink() throws NoLinkException {
        if (!hasLink()) {
            throw new NoLinkException();
        }
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

    private static final String LINK_INDICATOR = "(with link)";

    @Override
    public String getTaskEntryDescription() {
        return LESSON_ICON + " " + this.getModuleCode() + ' ' + this.getClassNo() + ": "
                + DateParser.dateToString(getStartDate())
                + " to " + DateParser.dateToString(getEndDate())
                + " Priority: " + getPriority().toString() + " " + (hasLink() ? LINK_INDICATOR : "");
    }

    @Override
    protected LessonFlag getTaskFlag() {
        return new LessonFlag();
    }
}
