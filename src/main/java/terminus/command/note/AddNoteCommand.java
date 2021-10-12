package terminus.command.note;

import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.ui.Ui;
import terminus.common.TerminusLogger;

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

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        TerminusLogger.info("Parsing add note arguments");
        if (arguments == null || arguments.isBlank()) {
            TerminusLogger.warning("Failed to parse arguments: arguments is empty");
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        // Regex to find arguments
        ArrayList<String> argArray = CommonFormat.findArguments(arguments);
        if (!isValidNoteArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        this.name = argArray.get(0);
        this.data = argArray.get(1);
        TerminusLogger.info(String.format("Parsed argument (name = %s, data = %s) to Add Note Command", name, data));
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        TerminusLogger.info("Executing Add Note Command");
        ContentManager contentManager = module.getContentManager(Note.class);
        assert contentManager != null;

        contentManager.add(new Note(name, data));
        TerminusLogger.info(String.format("Note(\"%s\",\"%s\") has been added", name, data));
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_NOTE, name));
        return new CommandResult(true, false);
    }

    private boolean isValidNoteArguments(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != ADD_NOTE_ARGUMENTS) {
            TerminusLogger.warning(String.format("Failed to find %d arguments: %d arguments found",
                    ADD_NOTE_ARGUMENTS, argArray.size()));
            isValid = false;
        } else if (CommonFormat.isArrayEmpty(argArray)) {
            TerminusLogger.warning("Failed to parse arguments: some arguments found is empty");
            isValid = false;
        }
        return isValid;
    }
}
