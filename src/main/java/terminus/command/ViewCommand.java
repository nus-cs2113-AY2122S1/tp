package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class ViewCommand<T> extends Command {

    private T type;

    private int itemNumber;
    private boolean displayAll;

    public ViewCommand(T type) {
        this.type = type;
        this.displayAll = false;
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_VIEW_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_VIEW;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (arguments == null || arguments.isBlank()) {
            displayAll = true;
            return;
        }
        try {
            itemNumber = Integer.parseInt(arguments);
            displayAll = false;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidArgumentException {
        ContentManager contentManager = module.getContentManager();
        contentManager.setContent(module.get(type));
        String result = "";
        if (displayAll) {
            result = contentManager.listAllContents();
        } else {
            result = contentManager.getContentData(itemNumber);
        }
        ui.printSection(result);
        return new CommandResult(true, false);
    }
}
