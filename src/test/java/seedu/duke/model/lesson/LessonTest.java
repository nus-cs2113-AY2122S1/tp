package seedu.duke.model.lesson;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author richwill28
public class LessonTest {
    @Test
    public void launchUrl_emptyUrl_exceptionThrown() {
        LessonList lessonList = new LessonList();
        try {
            lessonList.addLesson(new Lesson("CS3243 Lecture", "Monday", "10:00 PM", "12:00 PM", " "));
            lessonList.addLesson(new Lesson("CS1101S Lecture", "Wednesday", "10:00 PM", "12:00 PM", "-"));
        } catch (DukeException e) {
            fail();
        }

        assertThrows(DukeException.class, () -> lessonList.getLesson(1).launchUrl());
        assertThrows(DukeException.class, () -> lessonList.getLesson(2).launchUrl());
    }
}
