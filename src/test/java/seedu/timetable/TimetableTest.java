package seedu.timetable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import seedu.module.Lesson;
import seedu.module.LessonType;
import seedu.module.Module;

import java.time.DayOfWeek;

public class TimetableTest {

    @Test
    public void collisionEqualsOverride() {
        Timetable tt = new Timetable(1);
        assertNull(tt.getLesson(DayOfWeek.MONDAY, 10));

        // Creating a testing module
        Module testMod = new Module("Test");
        testMod.setModuleCredit(2.0);
        testMod.setTitle("Test Title");

        // Creating conflicting lessons
        Lesson lesson1 = new Lesson("TestClass1", "1000", "1200", "TestVenue", "Lecture", "Monday");

        TimetableLesson timetableLesson1 = new TimetableLesson(testMod, 1, lesson1);
        tt.addLesson(testMod, 1, lesson1);
        assertEquals(tt.getLesson(DayOfWeek.MONDAY, 10).getClassNo(), "TestClass1");
        assertEquals(tt.getLesson(DayOfWeek.MONDAY, 11).getClassNo(), "TestClass1");

        Lesson lesson2 = new Lesson("TestClass2", "1100", "1300", "TestVenue", "Tutorial", "Monday");

        TimetableLesson timetableLesson2 = new TimetableLesson(testMod, 1, lesson2);
        tt.addLesson(testMod, 1, lesson2);
        assertEquals(tt.getLesson(DayOfWeek.MONDAY, 10).getClassNo(), "TestClass1");
        assertEquals(tt.getLesson(DayOfWeek.MONDAY, 11).getClassNo(), "TestClass2");
    }

    @Test
    public void otherLessonTypeDefaultsLecture() {
        Timetable tt = new Timetable(1);
        assertNull(tt.getLesson(DayOfWeek.MONDAY, 10));

        // Creating a testing module
        Module testMod = new Module("Test");
        testMod.setModuleCredit(2.0);
        testMod.setTitle("Test Title");

        // Creating conflicting lessons
        Lesson lesson = new Lesson("TestSeminar", "1000", "1200", "TestVenue", "Seminar", "Monday");

        TimetableLesson timetableLesson = new TimetableLesson(testMod, 1, lesson);
        tt.addLesson(testMod, 1, lesson);
        assertEquals(tt.getLesson(DayOfWeek.MONDAY, 10).getLessonType(), LessonType.LECTURE);
    }

}
