package seedu.duke.lesson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
