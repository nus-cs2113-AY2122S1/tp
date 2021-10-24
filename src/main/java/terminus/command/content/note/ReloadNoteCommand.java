package terminus.command.content.note;

import java.io.IOException;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.storage.ModuleStorage;

public class ReloadNoteCommand extends Command {

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_RELOAD;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_RELOAD;
    }

    @Override
    public CommandResult execute(ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException, IOException {
        StringBuilder result = new StringBuilder();
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.loadNotesFromModule(moduleManager, getModuleName());
        result.append(Messages.MESSAGE_RESPONSE_RELOAD_NOTE);
        return new CommandResult(result.toString());
    }
}
