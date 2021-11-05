package taa.student;

//@@author hozhenhong99
import taa.ClassChecker;

import java.util.ArrayList;

public class StudentList implements ClassChecker {
    private final ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public int getSize() {
        return students.size();
    }

    /**
     * Checks if an index is valid with respect to the students ArrayList.
     *
     * @param index The index to check.
     * @return true if valid, else false.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0 && index < getSize());
    }

    /**
     * Gets a list of students in the ArrayList. Note: This returns a new ArrayList instance.
     *
     * @return A new ArrayList containing all the students.
     */
    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Gets the Student object at a particular index.
     *
     * @param index The index of the Student object.
     * @return A Student object if the index is valid, else null.
     */
    public Student getStudentAt(int index) {
        if (isValidIndex(index)) {
            return students.get(index);
        }

        return null;
    }

    /**
     * Adds a Student object to the list of students.
     *
     * @param student The Student object to add.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Deletes a student with a particular index from the student list.
     *
     * @param index The index of the student.
     * @return A Student object if successfully deleted, else null.
     */
    public Student deleteStudentAt(int index) {
        if (isValidIndex(index)) {
            return students.remove(index);
        }

        return null;
    }

    /**
     * Gets a list of students based on a particular keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of students with the keyword.
     */
    public ArrayList<Student> findStudents(String keyword) {
        String keywordLower = keyword.toLowerCase();

        ArrayList<Student> studentsFound = new ArrayList<>();
        for (Student student : students) {
            String id = student.getId();
            String name = student.getName();

            if (id.toLowerCase().contains(keywordLower) || name.toLowerCase().contains(keywordLower)) {
                studentsFound.add(student);
            }
        }

        return studentsFound;
    }

    public Student getStudentWithId(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getSize(); i += 1) {
            if (i > 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(students.get(i));
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean verify() {
        return true;
    }
}
