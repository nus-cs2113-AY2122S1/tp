package seedu.duke.lesson;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LessonList {
    // TODO: Implement serialization/deserialization

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

    public void clearLessonList() {
        lessonList.clear();
    }

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

    public boolean isEmpty() {
        return lessonList.isEmpty();
    }

    public LessonList filterLessonsByKeyword(String keyword) {
        return new LessonList(lessonList.stream()
                .filter(lesson -> lesson.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList()));
    }

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
}
