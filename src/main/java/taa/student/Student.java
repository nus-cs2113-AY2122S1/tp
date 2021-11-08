package taa.student;

//@@author hozhenhong99
import taa.ClassChecker;
import taa.attendance.AttendanceList;

import java.util.HashMap;

/**
 * Represents students.
 */
public class Student implements ClassChecker {
    private String id;
    private String name;
    private String comment;
    private final AttendanceList attendanceList;
    private final HashMap<String, Double> results;

    public Student(String id, String name) {
        setId(id);
        setName(name);
        setComment("");
        this.attendanceList = new AttendanceList();
        this.results = new HashMap<>();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    /**
     * Gets the AttendanceList object associated to the student.
     *
     * @return An AttendanceList object.
     */
    public AttendanceList getAttendanceList() {
        return attendanceList;
    }

    /**
     * Sets the name of the student.
     *
     * @param name The name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the student.
     *
     * @return The name of the student
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the student ID of the student.
     *
     * @param id the student ID of the student
     */
    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    /**
     * returns the student ID of the student.
     *
     * @return the student ID of the student
     */
    public String getId() {
        return this.id;
    }

    //@@author jon-the-melon
    /**
     * Adds a key,value pair to the hashmap.
     *
     * @param assessmentName key of the hashmap
     * @param marks          value to be stored under the key given
     */
    public void setMarks(String assessmentName, double marks) {
        results.put(assessmentName, marks);
    }

    /**
     * Deletes the marks for the given assessment.
     *
     * @param assessmentName Assessment whose marks are to be deleted.
     */
    public void deleteMark(String assessmentName) {
        results.remove(assessmentName);
    }

    /**
     * Deletes the assessment and marks for the assessment in the results HashMap.
     *
     * @param assessmentName Key of the hashmap.
     * @param marks Value to be stored under the key given.
     */
    public void deleteAssessmentAndMarks(String assessmentName, double marks) {
        results.remove(assessmentName, marks);
    }

    /**
     * Gets the marks for the given assessment.
     *
     * @param assessmentName Assessment to get marks for.
     * @return The marks for the inputted assessment if it exists, else -1.
     */
    public double getMarks(String assessmentName) {
        if (!results.containsKey(assessmentName)) {
            return -1;
        }

        return results.get(assessmentName);
    }

    /**
     * Gets all assessments and their marks in a hashmap.
     *
     * @return Hashmap containing assessment names as keys and the marks as values.
     */
    public HashMap<String, Double> getResults() {
        return new HashMap<>(results);
    }

    /**
     * Changes the assessment name in the results Hashmap from oldName to newName.
     * @param oldName Previous assessment name
     * @param newName New assessment name
     */
    public void changeAssessmentName(String oldName, String newName) {
        double value = results.get(oldName);
        results.remove(oldName, value);
        results.put(newName, value);
    }

    //@@author hozhenhong99
    /**
     * Overrides default toString method with the custom print message.
     *
     * @return the custom print message
     */
    @Override
    public String toString() {
        return String.format("%s - %s", id, name);
    }

    /**
     * Returns true if marks have been inputted for a given assessment.
     *
     * @param assessmentName Assessment to be checked.
     * @return True if marks have been inputted. Returns false otherwise.
     */
    public boolean marksExist(String assessmentName) {
        return results.containsKey(assessmentName);
    }

    @Override
    public boolean verify() {
        return !id.isEmpty() && !name.isEmpty();
    }
}
