package seedu.timetable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import seedu.module.Lesson;
import seedu.module.LessonType;
import seedu.module.Module;
import seedu.ui.TimetableUI;

import java.time.DayOfWeek;
import java.util.Arrays;

public class TimetableTest {

    @Test
    public void collisionEqualsOverride() {
        Timetable tt = new Timetable(1);
        assertNull(tt.getItem("Monday", 10));

        // Creating a testing module
        Module testMod = new Module("Test");
        testMod.setModuleCredit(2.0);
        testMod.setTitle("Test Title");

        // Creating conflicting lessons
        Lesson lesson1 = new Lesson("TestClass1", "1000", "1200", "TestVenue", "Lecture", "Monday");

        TimetableLesson timetableLesson1 = new TimetableLesson(testMod, 1, lesson1);
        tt.addLesson(testMod, 1, lesson1);

        TimetableLesson lesson = (TimetableLesson) tt.getItem("Monday", 10);
        assertEquals(lesson.getClassNo(), "TestClass1");

        lesson = (TimetableLesson) tt.getItem("Monday", 11);
        assertEquals(lesson.getClassNo(), "TestClass1");

        Lesson lesson2 =
                new Lesson("TestClass2", "1100", "1300", "TestVenue", "Tutorial", "Monday");

        TimetableLesson timetableLesson2 = new TimetableLesson(testMod, 1, lesson2);
        tt.addLesson(testMod, 1, lesson2);
        lesson = (TimetableLesson) tt.getItem("Monday", 10);
        assertEquals(lesson.getClassNo(), "TestClass1");

        lesson = (TimetableLesson) tt.getItem("Monday", 11);
        assertEquals(lesson.getClassNo(), "TestClass2");
    }

    @Test
    public void otherLessonTypeDefaultsLecture() {
        Timetable tt = new Timetable(1);
        assertNull(tt.getItem("Monday", 10));

        // Creating a testing module
        Module testMod = new Module("Test");
        testMod.setModuleCredit(2.0);
        testMod.setTitle("Test Title");

        // Creating conflicting lessons
        Lesson lesson = new Lesson("TestSeminar", "1000", "1200", "TestVenue", "Seminar", "Monday");

        TimetableLesson timetableLesson = new TimetableLesson(testMod, 1, lesson);
        tt.addLesson(testMod, 1, lesson);
        TimetableLesson ttLesson = (TimetableLesson) tt.getItem("Monday", 10);
        assertEquals(ttLesson.getLessonType(), LessonType.LECTURE);
    }

    @Test
    public void getUserItem_removeAllTimetableLesson() {
        Timetable timetable = new Timetable(1);
        Module testMod = new Module("Test");
        Lesson testLesson =
                new Lesson("TestClass1", "1000", "1200", "TestVenue", "Lecture", "Monday");
        timetable.addLesson(testMod, 1, testLesson);
        TimetableLesson timetableLesson = (TimetableLesson) timetable.getMonday()[2];

        assertTrue(Arrays.asList(timetable.getMonday()).contains(timetableLesson));
        TimetableItem[] schedule = TimetableUI.getUserItems(timetable.getMonday());
        for (TimetableItem item : schedule) {
            assertNull(item);
        }
    }

}
