package taa.command.student;

//@@author hozhenhong99
import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.command.Command;
import taa.exception.TaaException;
import taa.teachingclass.TeachingClass;
import taa.teachingclass.ClassList;
import taa.storage.Storage;
import taa.student.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class SortByScoresCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_SORT_ORDER = "o";
    private static final String[] SORT_SCORES_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_SORT_ORDER
    };
    private static final String MESSAGE_FORMAT_SORT_SCORES_USAGE = "%s %s/<CLASS_ID> "
            + "%s/<SORT_ORDER>";
    private static final String MESSAGE_LIST_MARKS_HEADER = "Here is the sorted list of students and their scores";
    public static final String MESSAGE_INVALID_SORT_ORDER = "Please input a valid sort order.";

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

    /**
     * Executes the sort_by_scores command and lists the students and their scores.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        assert argumentMap.containsKey(KEY_CLASS_ID);
        String classId = argumentMap.get(KEY_CLASS_ID);
        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        assert argumentMap.containsKey(KEY_SORT_ORDER);
        String order = argumentMap.get(KEY_SORT_ORDER);
        if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
            throw new TaaException(MESSAGE_INVALID_SORT_ORDER);
        }
        ArrayList<Student> students = teachingClass.getStudentList().getStudents();
        AssessmentList assessmentList = teachingClass.getAssessmentList();
        HashMap<Student, Double> unsortedScores = new HashMap<>();
        ArrayList<Student> sortedStudents = new ArrayList<>();
        for (Student student : students) {
            double score = 0.0;
            for (Assessment assessment : assessmentList.getAssessments()) {
                if (!student.marksExist(assessment.getName())) {
                    continue;
                }
                score += student.getMarks(assessment.getName()) / assessment.getMaximumMarks()
                        * assessment.getWeightage();
            }
            unsortedScores.put(student, score);
            int i = 0;
            while (i < sortedStudents.size() && score >= unsortedScores.get(sortedStudents.get(i))) {
                i += 1;
            }
            sortedStudents.add(i, student);
        }

        if (order.equalsIgnoreCase("asc")) {
            printAscending(ui, sortedStudents, unsortedScores);
        } else if (order.equalsIgnoreCase("desc")) {
            printDescending(ui, sortedStudents, unsortedScores);
        }
    }

    /**
     * Returns a string with the student and the marks associated with the student.
     *
     * @param student Student
     * @param score Score of the student.
     * @return String with the name and score of the student separated with a vertical line.
     */
    public String marksToString(Student student, double score) {
        return student + " | " + String.format("%.6f", score);
    }

    /**
     * Appends the student and the score to the string builder.
     *
     * @param stringBuilder The string builder
     * @param student The student to be appended
     * @param score The score of the student to be appended
     */
    private void appendStudentScore(StringBuilder stringBuilder, Student student, double score) {
        stringBuilder.append("\n");
        stringBuilder.append(marksToString(student, score));
    }

    /**
     * Prints out the list of the students and their scores in ascending order.
     *
     * @param ui The ui instance to handle interactions with the user.
     * @param students The ArrayList of students.
     * @param unsortedScores The hashmap of students and their scores.
     */
    private void printAscending(Ui ui, ArrayList<Student> students, HashMap<Student, Double> unsortedScores) {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_LIST_MARKS_HEADER);
        for (int i = 0; i < students.size(); i += 1) {
            appendStudentScore(stringBuilder, students.get(i), unsortedScores.get(students.get(i)));
        }
        assert ui != null : "ui should exist.";
        ui.printMessage(stringBuilder.toString());
    }

    /**
     * Prints out the list of the students and their scores in descending order.
     *
     * @param ui The ui instance to handle interactions with the user.
     * @param students The ArrayList of students.
     * @param unsortedScores The hashmap of students and their scores.
     */
    private void printDescending(Ui ui, ArrayList<Student> students, HashMap<Student, Double> unsortedScores) {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_LIST_MARKS_HEADER);
        for (int i = students.size() - 1; i >= 0; i -= 1) {
            appendStudentScore(stringBuilder, students.get(i), unsortedScores.get(students.get(i)));
        }
        assert ui != null : "ui should exist.";
        ui.printMessage(stringBuilder.toString());
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_SORT_SCORES_USAGE,
            COMMAND_SORT_BY_SCORES,
            KEY_CLASS_ID,
            KEY_SORT_ORDER
        );
    }
}
