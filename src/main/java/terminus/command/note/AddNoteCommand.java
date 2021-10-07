package terminus.command.note;

import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.ui.Ui;

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

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        ContentManager contentManager = module.getContentManager();
        contentManager.setContent(module.getNotes());
        contentManager.addNote(name, data);
        module.setNotes(contentManager.getContents());
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_NOTE));
        return new CommandResult(true, false);
    }

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
