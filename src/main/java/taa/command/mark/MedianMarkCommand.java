package taa.command.mark;

import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.classmodel.ClassObject;
import taa.command.Command;
import taa.exception.TaaException;
import taa.classmodel.ClassList;
import taa.storage.Storage;
import taa.student.Student;
import taa.student.StudentList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MedianMarkCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String[] MEDIAN_MARKS_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_ASSESSMENT_NAME};

    private static final String MESSAGE_FORMAT_MEDIAN_MARKS_USAGE = "%s %s/<CLASS_ID> %s/<ASSESSMENT_NAME>";
    private static final String MESSAGE_FORMAT_MEDIAN_MARKS = "Median mark for %s is %,.2f";
    private static final String MESSAGE_FORMAT_MEDIAN_MARKS_WITH_UNMARKED = "Median mark for %s is %,.2f\n"
            + "Note that %d student(s) have yet to be marked!";

    public MedianMarkCommand(String argument) {
        super(argument, MEDIAN_MARKS_ARGUMENT_KEYS);
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

    /**
     * Executes the median_marks command and displays the average mark of an assessment to the user.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String classId = argumentMap.get(KEY_CLASS_ID);
        ClassObject classObject = classList.getClassWithId(classId);
        if (classObject == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        StudentList studentList = classObject.getStudentList();
        if (studentList.getSize() <= 0) {
            throw new TaaException(MESSAGE_NO_STUDENTS);
        }

        AssessmentList assessmentList = classObject.getAssessmentList();
        Assessment assessment = assessmentList.getAssessment(argumentMap.get(KEY_ASSESSMENT_NAME));
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }
        ArrayList<Student> students = classObject.getStudentList().getStudents();
        ArrayList<Student> studentsClone = new ArrayList<>();
        for (Student student : students) {
            Student clonedStudent = new Student(student.getId(), student.getName());
            clonedStudent.setMarks(assessment.getName(), student.getMarks(assessment.getName()));
            studentsClone.add(clonedStudent);
        }
        printMedianMarks(ui, classObject, assessment, studentsClone);
    }

    /**
     * Outputs the median marks of an assessment in a class.
     *
     * @param ui     The ui instance to handle interactions with the user.
     * @param classObject The class the assessment belongs to.
     */
    private void printMedianMarks(Ui ui, ClassObject classObject, Assessment assessment, ArrayList<Student> students) {
        StudentList studentList = classObject.getStudentList();
        String assessmentName = assessment.getName();
        double classSize = studentList.getSize();
        int printIterator = 1;
        double median = 0;
        double unmarkedStudents = 0;
        HashMap<Student, Double> unsortedResults = new HashMap<>();
        for (Student student : students) {
            unsortedResults.put(student, student.getMarks(assessmentName));
            if (student.getMarks(assessmentName) < 0) {
                unmarkedStudents += 1;
            }
        }
        int medianCutOff = (int) Math.round(((classSize - unmarkedStudents) / 2) + unmarkedStudents);
        List<Map.Entry<Student, Double>> list = new LinkedList<>(unsortedResults.entrySet());
        list.sort(Map.Entry.comparingByValue());
        HashMap<Student, Double> sortedResults = new LinkedHashMap<>();
        for (Map.Entry<Student, Double> item : list) {
            sortedResults.put(item.getKey(), item.getValue());
        }
        for (Map.Entry<Student, Double> entry : sortedResults.entrySet()) {
            if (printIterator == medianCutOff) {
                median = entry.getValue();
            }
            printIterator += 1;
        }
        if (unmarkedStudents <= 0) {
            ui.printMessage(String.format(MESSAGE_FORMAT_MEDIAN_MARKS, assessmentName, median));
        } else {
            ui.printMessage(String.format(MESSAGE_FORMAT_MEDIAN_MARKS_WITH_UNMARKED,
                    assessmentName, median, (int) unmarkedStudents));
        }
    }

    /**
     * Returns the usage message of the command.
     *
     * @return String which contains the usage message.
     */
    @Override
    protected String getUsage() {
        return String.format(
                MESSAGE_FORMAT_MEDIAN_MARKS_USAGE,
                COMMAND_MEDIAN_MARK,
            KEY_CLASS_ID,
                KEY_ASSESSMENT_NAME
        );
    }
}
