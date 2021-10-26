package seedu.timetable;

import java.util.Locale;

import seedu.module.Lesson;
import seedu.module.LessonType;
import seedu.module.Module;

/**
 * Timetable Lesson that can be added to a timetable if the user is taking this
 * lesson.
 */
public class TimetableLesson extends TimetableItem {
    private final int semester;
    private final String classNo;

    /**
     * Creates a Timetable Lesson based off a valid Lesson in a Module.
     * 
     * @param module   Module of the Lesson to be created
     * @param semester Academic Year semester number (1/2) of the lesson to be added
     * @param lesson   Lesson to be created
     */
    public TimetableLesson(Module module, int semester, Lesson lesson) {
        super(module.getModuleCode(), lesson.getDay(), lesson.getStartTime(), lesson.getEndTime());
        this.semester = semester;
        this.type = lesson.getLessonType();
        this.venue = lesson.getVenue();
        this.classNo = lesson.getClassNo();
    }

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

    /**
     * Getter for the class number of the Timetable Lesson.
     * 
     * @return the class number
     */
    public String getClassNo() {
        return classNo;
    }

    /**
     * Getter for the lesson type of the TimetableLesson.
     * 
     * @return the LessonType (LECTURE/TUTORIAL/LABORATORY)
     */
    public LessonType getLessonType() {
        return parseLessonType(this.type);
    }

    @Override
    public String getType() {
        return getLessonType().toString() + "[" + getClassNo() + "]";
    }

    /**
     * Getter for the Venue that Lesson is held at.
     * 
     * @return a String representation of the venue that Lesson is held at
     */
    public String getVenue() {
        return venue;
    }

    public int getSemester() {
        return this.semester;
    }

    public boolean equals(TimetableLesson lesson) {
        if (lesson == null) {
            return false;
        }
        return this.getTitle().equals(lesson.getTitle()) && this.getClassNo().equals(this.getClassNo())
                && this.getLessonType().equals(lesson.getLessonType());
    }

}
