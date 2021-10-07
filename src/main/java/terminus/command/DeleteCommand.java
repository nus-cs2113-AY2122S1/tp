package terminus.command;

import java.util.Locale;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class DeleteCommand<T> extends Command {

    private T type;

    private int itemNumber;

    public DeleteCommand(T type) {
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
        try {
            itemNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidArgumentException {
        ContentManager contentManager = module.getContentManager();
        contentManager.setContent(module.get(type));
        String deletedContentName = contentManager.deleteContent(itemNumber);
        module.set(type, contentManager.getContents());
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_DELETE,
                CommonFormat.getClassName(type).toLowerCase(), deletedContentName));
        return new CommandResult(true);
    }
}

