package seedu.duke.model.lesson;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//@@author Roycius
public class LessonListTest {
    @Test
    public void isEmpty_emptyList_true() {
        assertTrue(new LessonList().isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_false() {
        LessonList lessonList = new LessonList();
        try {
            lessonList.addLesson(new Lesson("CS2113T Lecture", "Monday", "02:00 PM", "04:00 PM", "-"));
            assertFalse(lessonList.isEmpty());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDeleteLesson() {
        try {
            LessonList lessonList = new LessonList();
            lessonList.addLesson(new Lesson("CS2113T Lecture", "Monday", "02:00 PM", "04:00 PM", "-"));
            lessonList.addLesson(new Lesson("CS2113T Tutorial", "Tuesday", "12:00 PM", "01:00 PM", "-"));
            assertEquals(2, lessonList.getSize());
            lessonList.deleteLesson(1);
            assertEquals(1, lessonList.getSize());
            lessonList.deleteLesson(0);
            assertEquals(0, lessonList.getSize());
        } catch (DukeException e) {
            fail(); // the program should never reach this line
        }
    }

    @Test
    public void deleteLesson_indexOutOfBounds_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            LessonList lessonList = new LessonList();
            lessonList.addLesson(new Lesson("CS2113T Lecture", "Monday", "02:00 PM", "04:00 PM", ""));
            lessonList.deleteLesson(1);
        });
    }

    @Test
    public void deleteLesson_negativeIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> new LessonList().deleteLesson(-1));
    }

    @Test
    public void testSortLessons() {
        // Lessons should be sorted according to timeline by default
        try {
            LessonList lessonList = new LessonList();
            lessonList.addLesson(new Lesson("Lesson 3", "Wednesday", "12:00", "14:00", "-"));
            lessonList.addLesson(new Lesson("Lesson 5", "Friday", "14:30", "14:00", "-"));
            lessonList.addLesson(new Lesson("Lesson 1", "Monday", "08:00", "10:00", "-"));
            lessonList.addLesson(new Lesson("Lesson 2", "Monday", "09:00", "10:00", "-"));
            lessonList.addLesson(new Lesson("Lesson 4", "Friday", "13:00", "16:00", "-"));

            assertEquals("Lesson 1", lessonList.getLesson(0).getTitle());
            assertEquals("Lesson 2", lessonList.getLesson(1).getTitle());
            assertEquals("Lesson 3", lessonList.getLesson(2).getTitle());
            assertEquals("Lesson 4", lessonList.getLesson(3).getTitle());
            assertEquals("Lesson 5", lessonList.getLesson(4).getTitle());
        } catch (DukeException e) {
            fail(); // The program should never reach this line
        }
    }
}
