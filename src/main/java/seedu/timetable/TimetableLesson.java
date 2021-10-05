package seedu.timetable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import seedu.module.Lesson;
import seedu.module.LessonType;
import seedu.module.Module;

public class TimetableLesson implements Comparable<TimetableLesson> {

    private Module module;
    private String moduleCode;
    private int semester;
    private DayOfWeek dayOfWeek;
    private Lesson lesson;

    public TimetableLesson(Module module, int semester, Lesson lesson) {
        this.module = module;
        this.moduleCode = module.getModuleCode();
        this.semester = semester;
        this.lesson = lesson;
        parseDayOfWeek(lesson.getDay());
    }

    private void parseDayOfWeek(String day) {
        switch (day) {
        case "Monday":
            this.dayOfWeek = DayOfWeek.MONDAY;
            break;
        case "Tuesday":
            this.dayOfWeek = DayOfWeek.TUESDAY;
            break;
        case "Wednesday":
            this.dayOfWeek = DayOfWeek.WEDNESDAY;
            break;
        case "Thursday":
            this.dayOfWeek = DayOfWeek.THURSDAY;
            break;
        case "Friday":
            this.dayOfWeek = DayOfWeek.FRIDAY;
            break;
        case "Saturday":
            this.dayOfWeek = DayOfWeek.SATURDAY;
            break;
        case "Sunday":
            this.dayOfWeek = DayOfWeek.SUNDAY;
            break;
        default:
            break;
        }
    }

    private LessonType parseLessonType(String type) {
        switch (type.toLowerCase(Locale.ROOT)) {
        case "tutorial":
            return LessonType.TUTORIAL;
        case "laboratory":
            return LessonType.LABORATORY;
        default:
            return LessonType.LECTURE;
        }
    }

    private LocalTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(time, formatter);
    }


    public String getClassNo() {
        return lesson.getClassNo();
    }

    public LessonType getLessonType() {
        return parseLessonType(lesson.getLessonType());
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getStartHour() {
        LocalTime startTime = parseTime(lesson.getStartTime());
        return startTime.getHour();
    }

    public int getEndHour() {
        LocalTime endTime = parseTime(lesson.getEndTime());
        return endTime.getHour();
    }

    public Module getModule() {
        return module;
    }

    public String getVenue() {
        return lesson.getVenue();
    }

    @Override
    public int compareTo(TimetableLesson lesson) {
        return this.getStartHour() - lesson.getStartHour();
    }
}
