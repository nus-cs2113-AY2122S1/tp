package seedu.duke.model.lesson;

import seedu.duke.commons.core.Messages;
import seedu.duke.model.lesson.exceptions.LessonIndexException;
import seedu.duke.ui.Ui;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

//@@author Roycius
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

    //@@author richwill28
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

    //@@author Roycius
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
     */
    public static List<Lesson> deserialize(Ui ui, List<String> data) {
        List<Lesson> lessonList = new ArrayList<>();
        for (String line : data) {
            Lesson lesson = Lesson.deserialize(ui, line);
            if (lesson != null) {
                lessonList.add(lesson);
            }
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
