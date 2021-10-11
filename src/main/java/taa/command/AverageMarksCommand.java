package taa.command;

import taa.assessment.Assessment;
import taa.exception.TaaException;
import taa.Ui;
import taa.assessment.AssessmentList;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

/**
 * Class that deals with the outputting of the average marks.
 */
public class AverageMarksCommand extends Command{
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String[] ADD_ASSESSMENT_ARGUMENT_KEYS = {
            KEY_MODULE_CODE,
            KEY_ASSESSMENT_NAME
    };
    private static final String MESSAGE_FORMAT_AVERAGE_MARKS_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE> %s/<ASSESSMENT_NAME>";
    private static final String MESSAGE_AVERAGE_MARKS_FORMAT = "Average marks for %s is %,.4f";
    private static final String MESSAGE_AVERAGE_MARKS_WITH_UNMARKED_FORMAT = "Average marks for %s is %,.4f\n" +
            "Note that %d student(s) have yet to be marked!";

    public AverageMarksCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    /**
     * Outputs the average marks of an assessment in a module.
     *
     * @param moduleList List of modules.
     * @param ui The ui instance to handle interactions with the user.
     * @throws TaaException When average marks command is invalid.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui) throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
        if (!checkArgumentMap()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }
        if (module.getNumberOfStudents() <= 0) {
            //TODO no students in assessment message
            throw new TaaException("No students taking this assessment!");
        }

        AssessmentList list = module.getAssessmentList();
        Assessment assessment = list.getAssessment(argumentMap.get(KEY_ASSESSMENT_NAME));
        if (assessment == null) {
            //TODO no such assessment message
            throw new TaaException("Assessment does not exist!");
        }

        averageMarks(ui, module);
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_AVERAGE_MARKS_USAGE,
                COMMAND_AVERAGE_MARKS,
                KEY_MODULE_CODE,
                KEY_ASSESSMENT_NAME
        );
    }

    private void averageMarks(Ui ui, Module module) {
        String assessmentName = argumentMap.get("a");
        double classSize = module.getNumberOfStudents();
        double totalMarks = 0;
        int unmarkedStudents = 0;
        for (Student student : module.getStudents()) {
            if (student.marksExist(assessmentName)) {
                totalMarks += student.getMarks(assessmentName);
            } else {
                unmarkedStudents += 1;
            }
        }
        double averageMarks = totalMarks / classSize;
        if (unmarkedStudents <= 0) {
            ui.printMessage(String.format(MESSAGE_AVERAGE_MARKS_FORMAT, assessmentName, averageMarks));
        } else {
            ui.printMessage(String.format(MESSAGE_AVERAGE_MARKS_WITH_UNMARKED_FORMAT,
                    assessmentName, averageMarks, unmarkedStudents));
        }
    }
}
