package seedu.duke.lesson;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Message;

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

    public Lesson getLesson(int index) throws DukeException {
        try {
            return lessonList.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException exception) {
            throw new DukeException(Message.ERROR_NUMBER_FORMAT);
        }
    }

    public LessonList findLessonsByKeyword(String keyword) {
        LessonList matchingLessonList = new LessonList();
        for (Lesson lesson: lessonList) {
            if (lesson.getTitle().contains(keyword)) {
                matchingLessonList.addLesson(lesson);
            }
        }
        return matchingLessonList;
    }
}
