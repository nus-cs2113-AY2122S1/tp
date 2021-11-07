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

    public Lesson(String moduleCode, String classNumber,
            DayOfWeek dayOfWeek, LocalTime start, LocalTime end, int[] weeks) {
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

    private static final String LINK_INDICATOR = "(with link)";

    @Override
    public String getTaskEntryDescription() {
        return LESSON_ICON + " " + this.getModuleCode() + ' ' + this.getClassNumber() + ": "
                + DateParser.dateToString(getStartDate())
                + " to " + DateParser.dateToString(getEndDate())
                + " <" + getPriority().toString() + ">"
                + " {" + getRecurrence().toString() + "} "
                + (hasLink() ? LINK_INDICATOR : "");
    }

    @Override
    protected LessonFlag getTaskFlag() {
        return new LessonFlag();
    }
}
