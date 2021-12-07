package seedu.duke.schedule.lecture;

import java.util.ArrayList;

//@@author swatimahadevan

/**
 * Represents the list of lectures.
 */
public class LectureList {
    private ArrayList<Lecture> lectures;

    /**
     * Constructor of LectureList.
     */
    public LectureList() {
        this.lectures = new ArrayList<>();
    }

    /**
     * Returns the list of lectures.
     */
    public ArrayList<Lecture> getLectureList() {
        return lectures;
    }

    /**
     * Allows for adding of lecture to list of lectures.
     *
     * @param lecture Lecture to be added.
     */
    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    /**
     * Allows for deleting of lecture from list of lectures.
     *
     * @param index Index of lecture to be deleted.
     */
    public void deleteLecture(int index) {
        lectures.remove(index - 1);
    }
}