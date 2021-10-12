package terminus.command;

import java.util.Locale;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class DeleteCommand<T extends Content> extends Command {
    private Class<T> type;
    private int itemNumber;

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

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (arguments == null || arguments.isBlank()) {
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

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidArgumentException {
        ContentManager<T> contentManager = module.getContentManager(type);
        assert contentManager != null;
        TerminusLogger.info("Executing Delete Command");
        String deletedContentName =  contentManager.deleteContent(itemNumber);
        assert deletedContentName != null && !deletedContentName.isBlank();

        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_DELETE,
                CommonFormat.getClassName(type).toLowerCase(), deletedContentName));
        return new CommandResult(true);
    }
}

