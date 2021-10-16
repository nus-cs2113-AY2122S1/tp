package seedu.duke.model.lesson;

import seedu.duke.DukeException;
import seedu.duke.commons.core.Messages;
import seedu.duke.model.lesson.exceptions.DeserializeLessonException;
import seedu.duke.model.lesson.exceptions.LessonIndexException;
import seedu.duke.ui.Ui;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LessonList {
    private final List<Lesson> lessonList;

    public LessonList() {
        lessonList = new ArrayList<>();
    }

    public LessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public int getSize() {
        return lessonList.size();
    }

    public Lesson getLesson(int lessonIndex) throws LessonIndexException {
        try {
            return lessonList.get(lessonIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new LessonIndexException(Messages.ERROR_INVALID_INDEX);
        } catch (NumberFormatException e) {
            throw new LessonIndexException(Messages.ERROR_INVALID_NUMBER);
        }
    }

    public boolean isEmpty() {
        return lessonList.isEmpty();
    }

    public void addLesson(Lesson newLesson) {
        lessonList.add(newLesson);
    }

    public void deleteLesson(int lessonIndex) throws LessonIndexException {
        try {
            lessonList.remove(lessonIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new LessonIndexException(Messages.ERROR_INVALID_INDEX);
        } catch (NumberFormatException e) {
            throw new LessonIndexException(Messages.ERROR_INVALID_NUMBER);
        }
    }

    public void deleteAllLessons() {
        lessonList.clear();
    }

    /**
     * Filters the list of lessons based on the specified keyword.
     *
     * @param keyword the specified keyword
     * @return the filtered lesson list
     */
    public LessonList filterLessonsByKeyword(String keyword) {
        return new LessonList(lessonList.stream()
                .filter(lesson -> lesson.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Filters the list of lessons based on the specified period.
     *
     * @param period the specified period
     * @return the filtered lesson list
     */
    public LessonList filterLessonsByPeriod(String period) {
        return new LessonList(lessonList.stream()
                .filter(lesson -> lesson.getDayOfTheWeek().toLowerCase().contains(period))
                .collect(Collectors.toList()));
    }

    /**
     * Serializes the lesson list data into the correct format for storage file.
     *
     * @return serialized lesson list
     */
    public String serialize() {
        StringBuilder data = new StringBuilder();
        for (Lesson lesson : lessonList) {
            data.append(lesson.serialize()).append(System.lineSeparator());
        }
        return data.toString();
    }

    /**
     * Deserializes the storage file and returns the correct lesson list.
     *
     * @param data a list of strings representing the serialized data
     * @return deserialized lesson list
     * @throws DeserializeLessonException if the data is invalid format
     */
    public static List<Lesson> deserialize(List<String> data) throws DeserializeLessonException {
        List<Lesson> lessonList = new ArrayList<>();
        try {
            for (String entry : data) {
                if (entry.charAt(0) == 'L') {
                    lessonList.add(Lesson.deserialize(entry));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeserializeLessonException(Messages.ERROR_DESERIALIZING_DATA);
        }
        return lessonList;
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
}
