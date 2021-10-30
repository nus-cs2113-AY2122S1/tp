package terminus.command.content.note;

import java.io.IOException;
import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.storage.ModuleStorage;

/**
 * AddNoteCommand class which will manage the adding of new Notes from user command.
 */
public class AddNoteCommand extends Command {

    private String name;
    private String data;

    private static final int ADD_NOTE_ARGUMENTS = 2;

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_NOTE_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    /**
     * Parses the arguments to the AddNoteCommand object.
     * The arguments are attributes for a new Note object.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when arguments are empty or missing.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        TerminusLogger.info("Parsing add note arguments");
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            TerminusLogger.warning("Failed to parse arguments: arguments is empty");
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        // Regex to find arguments
        ArrayList<String> argArray = CommonUtils.findArguments(arguments);
        if (!isValidNoteArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        } else if (!CommonUtils.isValidFileName(argArray.get(0))) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_NOTE_NAME);
        }
        this.name = argArray.get(0);
        this.data = argArray.get(1);
        TerminusLogger.info(String.format("Parsed argument (name = %s, data = %s) to Add Note Command", name, data));
    }

    /**
     * Executes the add Note command.
     * Prints the relevant response to the Ui and a new Note will be added into the arraylist of Notes.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     * @throws IOException when the file to be saved is inaccessible (e.g. file is locked by OS).
     */
    public CommandResult execute(ModuleManager moduleManager) throws IOException, InvalidArgumentException {
        assert getModuleName() != null;
        TerminusLogger.info("Executing Add Note Command");
        NusModule module = moduleManager.getModule(getModuleName());
        ContentManager<Note> contentManager = module.getContentManager(Note.class);
        assert contentManager != null;
        if (contentManager.isDuplicateName(name)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_DUPLICATE_NAME);
        }
        Note newNote = new Note(name, data);
        contentManager.add(newNote);

        // Save to file
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.addNoteFromModule(getModuleName(), newNote);

        TerminusLogger.info(String.format("Note(\"%s\",\"%s\") has been added", name, data));
        String message = String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_NOTE, name);
        return new CommandResult(message);
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
            TerminusLogger.warning(String.format("Failed to find %d arguments: %d arguments found",
                    ADD_NOTE_ARGUMENTS, argArray.size()));
            isValid = false;
        } else if (CommonUtils.hasEmptyString(argArray)) {
            TerminusLogger.warning("Failed to parse arguments: some arguments found is empty");
            isValid = false;
        }  else if (argArray.get(1).length() > CommonFormat.MAX_FILE_SIZE) {
            TerminusLogger.warning("Failed to parse arguments: given note data is too long");
            isValid = false;
        }
        return isValid;
    }
}
