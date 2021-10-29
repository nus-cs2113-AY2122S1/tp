package terminus.command.content.note;

import java.io.IOException;
import terminus.command.CommandResult;
import terminus.command.content.DeleteCommand;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.storage.ModuleStorage;
import terminus.storage.StorageActionEnum;
import terminus.storage.StorageTypeEnum;

public class DeleteNoteCommand extends DeleteCommand<Note> {

    public DeleteNoteCommand(Class<Note> type) {
        super(type);
    }

    @Override
    public CommandResult execute(ModuleManager moduleManager) throws InvalidArgumentException {
        CommandResult result = super.execute(moduleManager);
        CommandResult newResult = new CommandResult(getModuleName(), StorageActionEnum.DELETE, StorageTypeEnum.TEXT,
                result.getMessage());
        newResult.setDeletedItemName(super.deletedContentName);
        return newResult;
    }
}
