package terminus.command.content.note;

import java.io.IOException;
import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.storage.ModuleStorage;

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
     * @throws InvalidCommandException  when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     * @throws IOException              when the file to be saved is inaccessible (e.g. file is locked by OS).
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException, IOException {
        TerminusLogger.info("Executing Export Note Command");
        assert getModuleName() != null;
        ContentManager<Note> noteManager = moduleManager.getModule(getModuleName()).getContentManager(Note.class);

        ArrayList<Note> notes = noteManager.getContents();
        ModuleStorage storage = ModuleStorage.getInstance();
        storage.exportModuleNotes(getModuleName(), notes);
        TerminusLogger.info("Exported Notes Successfully");
        return new CommandResult(Messages.SUCCESSFUL_EXPORT);
    }
}
