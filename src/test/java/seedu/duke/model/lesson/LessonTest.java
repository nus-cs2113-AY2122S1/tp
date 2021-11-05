package seedu.duke.model.lesson;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author richwill28
public class LessonTest {
    @Test
    public void launchUrl_emptyUrl_exceptionThrown() {
        try {
            LessonList lessonList = new LessonList();
            lessonList.addLesson(new Lesson("CS3243 Lecture", "Monday", "10:00 PM", "12:00 PM", " "));
            lessonList.addLesson(new Lesson("CS1101S Lecture", "Wednesday", "10:00 PM", "12:00 PM", "-"));
            assertThrows(DukeException.class, () -> lessonList.getLesson(1).launchUrl());
            assertThrows(DukeException.class, () -> lessonList.getLesson(2).launchUrl());
        } catch (DukeException e) {
            fail(); // The program should never reach this line
        }
    }

    @Test
    public void testCompareTo() {
        Lesson lesson1 = new Lesson("Lesson 1", "Tuesday", "10:00", "12:00", "-");
        Lesson lesson2 = new Lesson("Lesson 2", "Tuesday", "10:00", "13:00", "-");
        Lesson lesson3 = new Lesson("Lesson 3", "Tuesday", "15:00", "17:00", "-");
        Lesson lesson4 = new Lesson("Lesson 4", "Thursday", "09:00", "10:00", "-");

        assertEquals(0, lesson1.compareTo(lesson2));
        assertEquals(-1, lesson1.compareTo(lesson3));
        assertEquals(-1, lesson1.compareTo(lesson4));

        assertEquals(0, lesson2.compareTo(lesson1));
        assertEquals(-1, lesson2.compareTo(lesson3));
        assertEquals(-1, lesson2.compareTo(lesson4));

        assertEquals(1, lesson3.compareTo(lesson1));
        assertEquals(1, lesson3.compareTo(lesson2));
        assertEquals(-1, lesson3.compareTo(lesson4));

        assertEquals(1, lesson4.compareTo(lesson1));
        assertEquals(1, lesson4.compareTo(lesson2));
        assertEquals(1, lesson4.compareTo(lesson3));
    }
}
