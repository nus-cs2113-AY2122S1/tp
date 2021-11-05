package taa.command.mark;

//@@author jon-the-melon
import taa.teachingclass.TeachingClass;
import taa.teachingclass.ClassList;
import taa.command.Command;
import taa.storage.Storage;
import taa.assessment.Assessment;
import taa.exception.TaaException;
import taa.Ui;
import taa.assessment.AssessmentList;
import taa.student.Student;
import taa.student.StudentList;

/**
 * Class that deals with the outputting of the average marks.
 */
public class AverageMarksCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String[] AVERAGE_MARKS_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_ASSESSMENT_NAME};

    private static final String MESSAGE_FORMAT_AVERAGE_MARKS_USAGE = "%s %s/<CLASS_ID> %s/<ASSESSMENT_NAME>";
    private static final String MESSAGE_FORMAT_AVERAGE_MARKS = "Average marks for %s is %,.2f";
    private static final String MESSAGE_FORMAT_AVERAGE_MARKS_WITH_UNMARKED = "Average marks for %s is %,.2f\n"
        + "Note that %d student(s) have yet to be marked!";

    public AverageMarksCommand(String argument) {
        super(argument, AVERAGE_MARKS_ARGUMENT_KEYS);
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
     * Executes the average_marks command and displays the average mark of an assessment to the user.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String classId = argumentMap.get(KEY_CLASS_ID);
        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        StudentList studentList = teachingClass.getStudentList();
        if (studentList.getSize() <= 0) {
            throw new TaaException(MESSAGE_NO_STUDENTS);
        }

        AssessmentList assessmentList = teachingClass.getAssessmentList();
        Assessment assessment = assessmentList.getAssessment(argumentMap.get(KEY_ASSESSMENT_NAME));
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }

        printAverageMarks(ui, teachingClass, assessment);
    }

    /**
     * Outputs the average marks of an assessment in a class.
     *
     * @param ui     The ui instance to handle interactions with the user.
     * @param teachingClass The class the assessment belongs to.
     */
    private void printAverageMarks(Ui ui, TeachingClass teachingClass, Assessment assessment) {
        StudentList studentList = teachingClass.getStudentList();
        String assessmentName = assessment.getName();

        double classSize = studentList.getSize();
        double totalMarks = 0;
        int unmarkedStudents = 0;
        for (Student student : studentList.getStudents()) {
            if (student.marksExist(assessmentName)) {
                totalMarks += student.getMarks(assessmentName);
            } else {
                unmarkedStudents += 1;
            }
        }

        double averageMarks = totalMarks / (classSize - unmarkedStudents);
        if (unmarkedStudents <= 0) {
            ui.printMessage(String.format(MESSAGE_FORMAT_AVERAGE_MARKS, assessmentName, averageMarks));
        } else {
            ui.printMessage(String.format(MESSAGE_FORMAT_AVERAGE_MARKS_WITH_UNMARKED,
                assessmentName, averageMarks, unmarkedStudents));
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
            MESSAGE_FORMAT_AVERAGE_MARKS_USAGE,
            COMMAND_AVERAGE_MARKS,
            KEY_CLASS_ID,
            KEY_ASSESSMENT_NAME
        );
    }
}
