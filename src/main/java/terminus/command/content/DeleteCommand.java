package terminus.command.content;

import java.io.IOException;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

/**
 * DeleteCommand generic class which will manage the deletion of Content specified by user command.
 *
 * @param <T> Content object type.
 */
public class DeleteCommand<T extends Content> extends Command {

    private final Class<T> type;
    private int itemNumber;

    protected String deletedContentName;

    /**
     * Creates a DeleteCommand object with referenced to the provided class type.
     *
     * @param type Content object type.
     */
    public DeleteCommand(Class<T> type) {
        this.type = type;
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_DELETE_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_DELETE;
    }

    /**
     * Parses the arguments to the DeleteCommand object. The arguments are attributes to identify a Content object in an
     * ArrayList.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when argument provided is empty, non-numeric or less than 1.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        TerminusLogger.info("Parsing delete arguments");
        try {
            itemNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            TerminusLogger.warning(String.format("Failed to parse delete itemNumber : %s", arguments));
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
        if (itemNumber <= 0) {
            TerminusLogger.warning(String.format("Invalid itemNumber : %d", itemNumber));
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
    }

    /**
     * Executes the delete command. Prints the relevant response to the Ui and the specified Content object will be
     * removed from the arraylist.
     *
     * @param moduleManager The ModuleManager that contains the NusModules.
     * @return CommandResult to indicate the success and additional information about the execution.
     * @throws InvalidArgumentException when argument provided is index out of bounds of the ArrayList.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager) throws InvalidArgumentException {
        assert getModuleName() != null;
        NusModule module = moduleManager.getModule(getModuleName());
        ContentManager<T> contentManager = module.getContentManager(type);
        assert contentManager != null;

        TerminusLogger.info("Executing Delete Command");
        this.deletedContentName = contentManager.deleteContent(itemNumber);
        assert deletedContentName != null && !deletedContentName.isBlank();
        TerminusLogger.info(
                String.format("%s(%s) has been deleted", CommonUtils.getClassName(type), deletedContentName));

        String message = (String.format(Messages.MESSAGE_RESPONSE_DELETE,
                CommonUtils.getClassName(type).toLowerCase(), deletedContentName));
        return new CommandResult(message);
    }
}

