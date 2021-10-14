package seedu.timetable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import seedu.module.Lesson;
import seedu.module.LessonType;
import seedu.module.Module;

/**
 * Timetable Lesson that can be added to a timetable if the user is taking this
 * lesson.
 */
public class TimetableLesson extends TimetableItem {

    // private Module module;
    // private String moduleCode;
    private int semester;
    // private DayOfWeek dayOfWeek;
    // private Lesson lesson;

    private String type;
    private String venue;
    private String classNo;

    /**
     * Creates a Timetable Lesson based off a valid Lesson in a Module.
     * 
     * @param module   Module of the Lesson to be created
     * @param semester Academic Year semester number (1/2) of the lesson to be added
     * @param lesson   Lesson to be created
     */
    public TimetableLesson(Module module, int semester, Lesson lesson) {
        super(module.getModuleCode(), lesson.getDay(), lesson.getStartTime(), lesson.getEndTime());
        // this.module = module;
        // this.moduleCode = module.getModuleCode();
        this.semester = semester;
        // this.lesson = lesson;
        // parseDayOfWeek(lesson.getDay());
        this.type = lesson.getLessonType();
        this.venue = lesson.getVenue();
        this.classNo = lesson.getClassNo();
    }

    /**
     * Takes in a string e.g. "Sunday", "Monday", and formats it as a DayOfWeek Enum
     * for easier handling.
     * 
     * @param day String to be parsed as a DayOfWeek
     */
    // private void parseDayOfWeek(String day) {
    // switch (day) {
    // case "Monday":
    // this.dayOfWeek = DayOfWeek.MONDAY;
    // break;
    // case "Tuesday":
    // this.dayOfWeek = DayOfWeek.TUESDAY;
    // break;
    // case "Wednesday":
    // this.dayOfWeek = DayOfWeek.WEDNESDAY;
    // break;
    // case "Thursday":
    // this.dayOfWeek = DayOfWeek.THURSDAY;
    // break;
    // case "Friday":
    // this.dayOfWeek = DayOfWeek.FRIDAY;
    // break;
    // case "Saturday":
    // this.dayOfWeek = DayOfWeek.SATURDAY;
    // break;
    // case "Sunday":
    // this.dayOfWeek = DayOfWeek.SUNDAY;
    // break;
    // default:
    // break;
    // }
    // }

    /**
     * Takes in a string e.g. "Lecture/Tutorial/Laboratory" And parses it as a
     * LessonType enum. "Tutorial" -> LessonType.TUTORIAL "Laboratory" ->
     * LessonType.LABORATORY DEFAULT : Lecture
     * 
     * @param type String to be parsed into a LessonType
     * @return the LessonType that was parsed
     */
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

    // /**
    // * Takes in a String in the time format "HHmm" e.g. 1800 And parses it into a
    // * LocalTime object
    // *
    // * @param time String in the format "HHmm" e.g. 1800
    // * @return a LocalTime object representing the input time
    // */
    // private LocalTime parseTime(String time) {
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
    // return LocalTime.parse(time, formatter);
    // }

    /**
     * Getter for the class number of the Timetable Lesson.
     * 
     * @return the class number
     */
    public String getClassNo() {
        return classNo;
        // return lesson.getClassNo();
    }

    /**
     * Getter for the lesson type of the TimetableLesson.
     * 
     * @return the LessonType (LECTURE/TUTORIAL/LABORATORY)
     */
    public LessonType getLessonType() {
        // return parseLessonType(lesson.getLessonType());
        return parseLessonType(this.type);
    }

    // /**
    // * Getter for the day of week the lesson is held on.
    // *
    // * @return DayOfWeek Enum e.g. DayOfWeek.MONDAY
    // */
    // public DayOfWeek getDayOfWeek() {
    // return dayOfWeek;
    // }

    // /**
    // * Getter for the module code of the lesson.
    // *
    // * @return String representation of the module code e.g. CS2113T
    // */
    // public String getModuleCode() {
    // return moduleCode;
    // }

    // /**
    // * Getter for the starting hour of the lesson.
    // *
    // * @return an Integer representing the hour in the day in 24H time format
    // */
    // public int getStartHour() {
    // LocalTime startTime = parseTime(lesson.getStartTime());
    // return startTime.getHour();
    // }

    // /**
    // * Getter for the ending hour of the lesson.
    // *
    // * @return an Integer representing the hour in the day in 24H time format
    // */
    // public int getEndHour() {
    // LocalTime endTime = parseTime(lesson.getEndTime());
    // return endTime.getHour();
    // }

    /**
     * Getter for the Module that the lesson belongs to.
     * 
     * @return the Module the lesson belongs to
     */
    // public Module getModule() {
    // return module;
    // }

    /**
     * Getter for the Venue that Lesson is held at.
     * 
     * @return a String representation of the venue that Lesson is held at
     */
    public String getVenue() {
        // return lesson.getVenue();
        return venue;
    }
}
