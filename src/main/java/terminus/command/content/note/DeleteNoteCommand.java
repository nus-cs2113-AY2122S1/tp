package terminus.command.content.note;

import java.io.IOException;
import terminus.command.CommandResult;
import terminus.command.content.DeleteCommand;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.storage.ModuleStorage;

public class DeleteNoteCommand extends DeleteCommand<Note> {

    public DeleteNoteCommand(Class<Note> type) {
        super(type);
    }

    @Override
    public CommandResult execute(ModuleManager moduleManager) throws InvalidArgumentException, IOException {
        CommandResult result = super.execute(moduleManager);
        // Update file accordingly
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.removeNoteFromModule(getModuleName(), super.deletedContentName);
        return result;
    }
}
