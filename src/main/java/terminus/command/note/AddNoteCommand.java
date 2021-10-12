package terminus.command.note;

import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.ui.Ui;

/**
 * AddNoteCommand class which will manage the adding of new Notes from user command.
 */
public class AddNoteCommand extends Command {

    private String name;
    private String data;

    private static final int ADD_NOTE_ARGUMENTS = 2;

    public AddNoteCommand() {

    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_NOTE_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    /**
     * Parses the arguments into an AddNoteCommand object.
     * The arguments' name and data are attributes for a new Note object.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException Exception for when argument parsing fails.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        // Perform required checks with regex
        if (arguments == null || arguments.isBlank()) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        ArrayList<String> argArray = CommonFormat.findArguments(arguments);
        if (!isValidNoteArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        this.name = argArray.get(0);
        this.data = argArray.get(1);
    }

    /**
     * Executes the add Note command.
     * Prints the relevant response to the Ui and a new Note will be added into the arraylist of Notes.
     *
     * @param ui The Ui object to send messages to the users.
     * @param module The NusModule contain the ContentManager of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     */
    @Override
    public CommandResult execute(Ui ui, NusModule module) {
        ContentManager contentManager = module.getContentManager(Note.class);
        contentManager.add(new Note(name, data));
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_NOTE, name));
        return new CommandResult(true, false);
    }

    /**
     * Checks if arguments are non-empty and valid.
     *
     * @param argArray The command arguments in an array list.
     * @return True if the appropriate number of arguments are present, false otherwise.
     */
    private boolean isValidNoteArguments(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != ADD_NOTE_ARGUMENTS) {
            isValid = false;
        } else if (CommonFormat.isArrayEmpty(argArray)) {
            isValid = false;
        }
        return isValid;
    }
}
