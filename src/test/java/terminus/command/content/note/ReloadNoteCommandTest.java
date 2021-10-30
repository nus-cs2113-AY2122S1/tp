package terminus.command.content.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;
import terminus.parser.NoteCommandParser;
import terminus.storage.StorageActionEnum;
import terminus.storage.StorageManager;
import terminus.storage.StorageTypeEnum;

public class ReloadNoteCommandTest {

    Class<Note> type = Note.class;
    private NoteCommandParser commandParser;
    private ModuleManager moduleManager;
    private String tempModule = "test";

    private StorageManager storageManager;

    @BeforeEach
    void setUp() throws IOException {
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.commandParser = NoteCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
        this.storageManager = new StorageManager(TestFilePath.RESOURCE_FOLDER, TestFilePath.SAVE_FILE);
    }


    @Test
    void execute_success() throws InvalidArgumentException, InvalidCommandException, InvalidFileException {
        ContentManager noteContentManager = moduleManager.getModule(tempModule).getContentManager(type);
        assertEquals(0, noteContentManager.getTotalContents());
        for (int i = 1; i <= 3; i++) {
            String fileName = "test" + String.valueOf(i);
            String data = "test" + String.valueOf(i);
            Command addCommand = commandParser.parseCommand("add \"" + fileName + "\" \"" + data + "\"");
            CommandResult result = addCommand.execute(moduleManager);
            String affectedModule = result.getModule();
            StorageActionEnum storageAction = result.getStorageAction();
            StorageTypeEnum storageType = result.getStorageType();
            String deletedItemName = result.getDeletedItemName();
            storageManager.execute(moduleManager, affectedModule, deletedItemName, storageAction, storageType);
        }
        assertEquals(3, noteContentManager.getTotalContents());
        storageManager.execute(moduleManager, tempModule, "test1",
                StorageActionEnum.DELETE, StorageTypeEnum.TEXT);
        assertEquals(3, noteContentManager.getTotalContents());
        Command reloadCommand = commandParser.parseCommand("reload");
        CommandResult result = reloadCommand.execute(moduleManager);
        String affectedModule = result.getModule();
        StorageActionEnum storageAction = result.getStorageAction();
        StorageTypeEnum storageType = result.getStorageType();
        String deletedItemName = result.getDeletedItemName();
        storageManager.execute(moduleManager, affectedModule, deletedItemName, storageAction, storageType);
        assertEquals(2, noteContentManager.getTotalContents());
        storageManager.execute(moduleManager, null, tempModule,
                StorageActionEnum.DELETE, StorageTypeEnum.FOLDER);
    }


}
