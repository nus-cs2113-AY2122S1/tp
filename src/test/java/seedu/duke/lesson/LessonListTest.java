package seedu.duke.lesson;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LessonListTest {
    @Test
    public void isEmpty_emptyList_true() {
        assertTrue(new LessonList().isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_false() {
        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("CS2113T Lecture", "MON", "2pm", "4pm"));
        assertFalse(lessonList.isEmpty());
    }

    @Test
    public void testDeleteLesson() {
        try {
            LessonList lessonList = new LessonList();
            lessonList.addLesson(new Lesson("CS2113T Lecture", "MON", "2pm", "4pm"));
            lessonList.addLesson(new Lesson("CS2113T Tutorial", "TUE", "12am", "1pm"));
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
            lessonList.addLesson(new Lesson("CS2113T Lecture", "MON", "2pm", "4pm"));
            lessonList.deleteLesson(1);
        });
    }

    @Test
    public void deleteLesson_negativeIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> new LessonList().deleteLesson(-1));
    }
}
