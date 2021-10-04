package seedu.duke.lesson;

import java.util.List;
import java.util.ArrayList;

public class LessonList {
    private List<Lesson> lessonList;

    public LessonList() {
        lessonList = new ArrayList<>();
    }

    /**
     * Replaces the current lesson list with a given one.
     *
     * @param newLessonList the new lesson list given
     */
    public LessonList(List<Lesson> newLessonList) {
        lessonList = newLessonList;
    }

    public void addLesson(Lesson newLesson) {
        lessonList.add(newLesson);
    }

    public void clearLessonList() {
        lessonList.clear();
    }

    /**
     * Removes the lesson of the given index from the lesson list.
     *
     * @param index the index of the lesson to be removed
     */
    public void deleteLesson(int index) {
        lessonList.remove(index);
    }
}
