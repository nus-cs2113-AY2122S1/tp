package seedu.duke.lesson;

import java.util.ArrayList;

public class LessonList {
    private ArrayList<Lesson> lessonList = new ArrayList<>();

    public LessonList() {
    }

    /**
     * Replaces the current lesson list with a given one.
     *
     * @param newLessonList the new lesson list given
     */
    public LessonList(ArrayList<Lesson> newLessonList) {
        lessonList = newLessonList;
    }

    public void addLessonToList(Lesson newLesson) {
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
    public void deleteLessonFromList(int index) {
        lessonList.remove(index);
    }
}