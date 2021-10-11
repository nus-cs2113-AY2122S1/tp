package seedu.duke.lesson;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LessonList {

    private List<Lesson> lessonList;

    public LessonList() {
        lessonList = new ArrayList<>();
    }

    public LessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public void addLesson(Lesson newLesson) {
        lessonList.add(newLesson);
    }

    /**
     * Deletes all lessons from the lesson list.
     */
    public void clearLessonList() {
        lessonList.clear();
    }

    public boolean isEmpty() {
        return lessonList.isEmpty();
    }

    /**
     * Gets the size of the lesson list.
     *
     * @return the number of lessons in the lesson list
     */
    public int getSize() {
        return lessonList.size();
    }

    /**
     * Deletes a lesson from the list.
     *
     * @param index the index of the lesson to delete
     */
    public void deleteLesson(int index) throws DukeException {
        try {
            lessonList.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException exception) {
            throw new DukeException(Message.ERROR_NUMBER_FORMAT);
        }
    }

    /**
     * Gets information of a lesson from the lesson list.
     *
     * @param index the index of the lesson
     * @return the lesson represented by the specified index
     */
    public Lesson getLesson(int index) throws DukeException {
        try {
            return lessonList.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException exception) {
            throw new DukeException(Message.ERROR_NUMBER_FORMAT);
        }
    }

    /**
     * Gets lessons containing the specified keyword.
     *
     * @param keyword the keyword to find in lessons in the list
     * @return the filtered lessons list containing only lessons with the specified keyword
     */
    public LessonList filterLessonsByKeyword(String keyword) {
        return new LessonList(lessonList.stream()
                .filter(lesson -> lesson.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Gets lessons with the specified period.
     *
     * @param period the day of the week for the task/lesson
     * @return the filtered lessons list containing only lessons with the specified period
     */
    public LessonList filterLessonsByPeriod(String period) {
        return new LessonList(lessonList.stream()
                .filter(lesson -> lesson.getDayOfTheWeek().toLowerCase().contains(period))
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < lessonList.size(); i++) {
            Lesson lesson = lessonList.get(i);
            s.append(Ui.PADDING).append(i + 1).append(". ").append(lesson).append(System.lineSeparator());
        }
        return s.toString();
    }

    /**
     * Serializes the lesson list into its file data storage format.
     *
     * @return the serialized lesson list
     */
    public String serialize() {
        StringBuilder data = new StringBuilder();
        for (Lesson lesson : lessonList) {
            data.append(lesson.serialize()).append(System.lineSeparator());
        }
        return data.toString();
    }

    /**
     * Filters out strings representing lesson data from a list of strings and deserializes
     * them into a list of lesson objects.
     *
     * @param data the list of strings
     * @return the list of lesson objects
     * @throws DukeException when there is lesson data that is not deserializable
     */
    public static List<Lesson> deserialize(List<String> data) throws DukeException {
        List<Lesson> lessonList = new ArrayList<>();
        for (String entry : data) {
            if (entry.charAt(0) == 'L') {
                lessonList.add(Lesson.deserialize(entry));
            }
        }
        return lessonList;
    }
}
