package seedu.duke.logic.commands.lesson;


import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class AddLessonCommandTest {
    @Test
    public void addLesson_duplicateLesson_exceptionThrown() {
        LessonList lessonList = new LessonList();
        try {
            lessonList.addLesson(new Lesson("lesson 1", "Tuesday", "12:00 PM", "02:00 PM", "-"));
        } catch (DukeException e) {
            fail();
        }

        assertThrows(DukeException.class, () -> lessonList.addLesson(new Lesson("lesson 1", "Tuesday", "12:00 PM",
                "02:00 PM", "-")));
    }

}
