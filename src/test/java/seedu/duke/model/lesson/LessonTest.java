package seedu.duke.model.lesson;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author richwill28
public class LessonTest {
    @Test
    public void launchUrl_emptyUrl_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            LessonList lessonList = new LessonList();
            lessonList.addLesson(new Lesson("CS3243 Lecture", "Monday", "10:00 PM", "12:00 PM", " "));
            lessonList.addLesson(new Lesson("CS1101S Lecture", "Wednesday", "10:00 PM", "12:00 PM", "-"));
            lessonList.getLesson(1).launchUrl();
            lessonList.getLesson(2).launchUrl();
        });
    }
}
