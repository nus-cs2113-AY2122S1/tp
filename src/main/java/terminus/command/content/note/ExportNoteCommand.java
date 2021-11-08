package terminus.command.content.note;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.storage.StorageActionEnum;
import terminus.storage.StorageTypeEnum;

public class ExportNoteCommand extends Command {

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_EXPORT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_EXPORT;
    }

    /**
     * Executes the command. Prints the required result to the Ui.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager) throws InvalidArgumentException {
        TerminusLogger.info("Executing Export Note Command");
        assert getModuleName() != null;
        ContentManager noteContentManager = moduleManager.getModule(getModuleName()).getContentManager(Note.class);
        if (noteContentManager.getTotalContents() < 1) {
            throw new InvalidArgumentException(Messages.ERROR_EXPORT_MISSING_NOTE);
        }

        String message = Messages.RESPONSE_EXPORT;
        return new CommandResult(getModuleName(), StorageActionEnum.EXPORT, StorageTypeEnum.PDF, message);
    }
}
