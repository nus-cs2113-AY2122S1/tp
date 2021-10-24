package terminus.command.content.note;

import java.io.IOException;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.storage.ModuleStorage;

public class ReloadNoteCommand extends Command {

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_RELOAD;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_RELOAD;
    }

    /**
     * Executes Reload Note command.
     * Replace existing note data in the specified module with the ones in the file directory.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success including additional options.
     * @throws InvalidCommandException when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     * @throws IOException when the file to be read is null.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException, IOException {
        StringBuilder result = new StringBuilder();
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.loadNotesFromModule(moduleManager, getModuleName());
        result.append(String.format(Messages.MESSAGE_RESPONSE_RELOAD_NOTE, getModuleName()));
        return new CommandResult(result.toString());
    }
}
