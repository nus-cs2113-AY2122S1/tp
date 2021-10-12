package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class ViewCommand<T extends Content> extends Command {

    private Class<T> type;

    private int itemNumber;
    private boolean displayAll;

    public ViewCommand(Class<T> type) {
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
        TerminusLogger.info("Parsing view arguments");
        try {
            itemNumber = Integer.parseInt(arguments);
            displayAll = false;
        } catch (NumberFormatException e) {
            TerminusLogger.warning(String.format("Failed to parse view itemNumber : %s", arguments));
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
        if (itemNumber <= 0) {
            TerminusLogger.warning(String.format("Invalid itemNumber : %d", itemNumber));
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidArgumentException {
        StringBuilder result = new StringBuilder();
        ContentManager<T> contentManager = module.getContentManager(type);
        if (displayAll) {
            String fullList = contentManager.listAllContents();
            assert fullList != null;
            if (fullList.isBlank()) {
                result.append(Messages.EMPTY_CONTENT_LIST_MESSAGE);
            } else {
                result.append(Messages.CONTENT_MESSAGE_HEADER);
                result.append(contentManager.listAllContents());
                result.append("\nRerun the same command with an index behind to view the content.");
            }
        } else {
            result.append(contentManager.getContentData(itemNumber));
        }
        ui.printSection(result.toString());
        return new CommandResult(true, false);
    }
}
