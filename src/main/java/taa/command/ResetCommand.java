package taa.command;

//@@author leyondlee
import taa.Ui;
import taa.teachingclass.ClassList;
import taa.exception.TaaException;
import taa.storage.Storage;

public class ResetCommand extends Command {
    private static final String MESSAGE_NO_DATA = "There is no data to reset.";

    private static final String MESSAGE_FORMAT_CONFIRMATION_PROMPT = "Are you sure that you want to remove all data?\n"
        + "WARNING: This action cannot be reversed.\n"
        + "Type [%s] to continue: ";

    private static final String CONFIRM_VALUE = "yes";

    public ResetCommand(String argument) {
        super(argument);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        if (classList.getSize() <= 0) {
            throw new TaaException(MESSAGE_NO_DATA);
        }

        ui.printBorder();
        ui.printMessage(String.format(MESSAGE_FORMAT_CONFIRMATION_PROMPT, CONFIRM_VALUE), false);

        String userInput = ui.getUserInput(PROMPT_WITHIN_COMMAND, true);
        String message;
        if (userInput != null && userInput.equals(CONFIRM_VALUE)) {
            classList.deleteAllClasses();
            storage.save(classList);

            message = MESSAGE_DATA_REMOVED;
        } else {
            message = MESSAGE_ABORT;
        }

        ui.printMessage(String.format("\n%s", message), false);
        ui.printBorder();
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_GENERIC_USAGE,
            COMMAND_RESET
        );
    }
}
