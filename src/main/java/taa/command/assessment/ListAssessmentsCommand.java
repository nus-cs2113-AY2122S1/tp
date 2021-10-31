package taa.command.assessment;

import taa.command.Command;
import taa.storage.Storage;
import taa.Ui;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.teachingclass.TeachingClass;
import taa.teachingclass.ClassList;

public class ListAssessmentsCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String[] LIST_ASSESSMENTS_ARGUMENT_KEYS = {KEY_CLASS_ID};

    private static final String MESSAGE_LIST_EMPTY = "There are no assessments in the class.";

    private static final String MESSAGE_FORMAT_LIST_ASSESSMENTS_USAGE = "%s %s/<CLASS_ID>";
    private static final String MESSAGE_FORMAT_OUTPUT = "Assessments for %s:\n%s";

    public ListAssessmentsCommand(String argument) {
        super(argument, LIST_ASSESSMENTS_ARGUMENT_KEYS);
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
     * Executes the list_assessment command and list all the assessments of a particular class.
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

        String message;
        AssessmentList assessmentList = teachingClass.getAssessmentList();
        if (assessmentList.getSize() == 0) {
            message = MESSAGE_LIST_EMPTY;
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, teachingClass, assessmentList);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_LIST_ASSESSMENTS_USAGE,
            COMMAND_LIST_ASSESSMENTS,
            KEY_CLASS_ID
        );
    }
}
