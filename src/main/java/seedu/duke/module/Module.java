package seedu.duke.module;

import seedu.duke.student.Assessment;
import seedu.duke.student.Student;

import java.util.ArrayList;

public class Module {
    private final String code;
    private final String name;
    private final ArrayList<Student> students;
    private final ArrayList<Assessment> assessments;

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
        this.assessments = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (name.isEmpty()) {
            return code;
        }

        return String.format("%s - %s", code, name);
    }
}
