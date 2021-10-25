package taa.command;

import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.storage.Storage;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SortByScoresCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String KEY_SORT_ORDER = "o";
    private static final String[] SORT_SCORES_ARGUMENT_KEYS = {
        KEY_MODULE_CODE,
        KEY_ASSESSMENT_NAME,
        KEY_SORT_ORDER
    };
    private static final String MESSAGE_FORMAT_SORT_SCORES_USAGE = "%s %s/<MODULE_CODE> %s/<ASSESSMENT_NAME> "
            + "%s/<SORT_ORDER>";
    private static final String MESSAGE_LIST_MARKS_HEADER = "Here is the sorted list of students and "
            + "their marks for %s:";

    public SortByScoresCommand(String argument) {
        super(argument, SORT_SCORES_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModuleWithCode(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }
        AssessmentList assessmentList = module.getAssessmentList();
        String assessmentName = argumentMap.get(KEY_ASSESSMENT_NAME);
        Assessment assessment = assessmentList.getAssessment(assessmentName);
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }
        String order = argumentMap.get(KEY_SORT_ORDER);
        if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
            throw new TaaException("Please input a valid sort order.");
        }
        ArrayList<Student> studentList = module.getStudentList().getStudents();
        ArrayList<Student> studentListClone = new ArrayList<>();
        for (Student student : studentList) {
            Student clonedStudent = new Student(student.getId(), student.getName());
            studentListClone.add(clonedStudent);
        }

        if (order.equalsIgnoreCase("asc")) {
            sortAscending(ui, module, assessmentName, studentListClone);
        } else if (order.equalsIgnoreCase("desc")) {
            sortDescending(ui, module, assessmentName, studentListClone);
        }
    }

    /**
     * Returns a string with the student name and the marks associated with the student.
     *
     * @param studentName Name of the student.
     * @param marks       Marks of the student.
     * @return String with the name and marks of the student separated with a vertical line.
     */
    public String marksToString(String studentName, double marks) {
        return studentName + " | " + marks;
    }

    private void appendUnmarkedStudent(StringBuilder stringBuilder, int studentIndex, Student student) {
        stringBuilder.append("\n");
        stringBuilder.append(studentIndex);
        stringBuilder.append(". ");
        stringBuilder.append(student.getName());
        stringBuilder.append(" | ");
        stringBuilder.append("NIL");
    }

    private void appendMarkedStudent(String assessmentName, StringBuilder stringBuilder, int studentIndex,
                                     Student student) {
        stringBuilder.append("\n");
        stringBuilder.append(studentIndex);
        stringBuilder.append(". ");
        stringBuilder.append(marksToString(student.getName(), student.getMarks(assessmentName)));
    }

    private void sortAscending(Ui ui, Module module, String assessmentName, ArrayList<Student> students) {
        StringBuilder stringBuilder = new StringBuilder(String.format(MESSAGE_LIST_MARKS_HEADER, assessmentName));
        int listIndex = 0;
        List<Student> toRemove = new ArrayList<>();
        for (Student student : students) {
            if (!student.marksExist(assessmentName)) {
                listIndex += 1;
                appendUnmarkedStudent(stringBuilder, listIndex, student);
                toRemove.add(student);
            }
        }
        students.removeAll(toRemove);
        while (students.size() > 0) {
            for (Student student : students) {
                int currentMinimumScore = 10000;
                if (student.marksExist(assessmentName) && (student.getMarks(assessmentName) < currentMinimumScore)) {
                    listIndex += 1;
                    appendMarkedStudent(assessmentName, stringBuilder, listIndex, student);
                    students.remove(student);
                }
            }
        }
        ui.printMessage(stringBuilder.toString());
    }

    private void sortDescending(Ui ui, Module module, String assessmentName, ArrayList<Student> students) {
        StringBuilder stringBuilder = new StringBuilder(String.format(MESSAGE_LIST_MARKS_HEADER, assessmentName));
        int listIndex = 0;
        for (Student student : students) {
            if (!student.marksExist(assessmentName)) {
                listIndex += 1;
                appendUnmarkedStudent(stringBuilder, listIndex, student);
                students.remove(student);
            }
        }
        while (students.size() > 0) {
            for (Student student : students) {
                int currentMaximumScore = -1;
                if (student.marksExist(assessmentName) && (student.getMarks(assessmentName) > currentMaximumScore)) {
                    listIndex += 1;
                    appendMarkedStudent(assessmentName, stringBuilder, listIndex, student);
                    students.remove(student);
                }
            }
        }
        ui.printMessage(stringBuilder.toString());
    }

    @Override
    protected String getUsage() {
        return String.format(
        MESSAGE_FORMAT_SORT_SCORES_USAGE,
        COMMAND_SORT_BY_SCORES,
        KEY_MODULE_CODE,
        KEY_ASSESSMENT_NAME,
        KEY_SORT_ORDER
        );
    }
}
