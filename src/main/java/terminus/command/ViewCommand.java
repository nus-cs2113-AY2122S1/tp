package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.ui.Ui;

/**
 * ViewModuleCommand generic class which will manage the viewing of Content information specified by user command.
 *
 * @param <T> Content object type.
 */
public class ViewCommand<T extends Content> extends Command {

    private final Class<T> type;

    private int itemNumber;

    /**
     * Determines whether to print the list of all Content objects or just the specified one.
     */
    private boolean displayAll;

    /**
     * Creates a ViewModuleCommand object with referenced to the provided class type.
     *
     * @param type Content object type.
     */
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

    /**
     * Parses the arguments to the ViewModuleCommand object. The arguments are attributes to identify a Content object
     * in an ArrayList. The arguments can be empty which refers to viewing a list all Content object in an ArrayList
     * instead.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when a non-empty argument provided is non-numeric or less than 1.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
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

    /**
     * Executes the view command. Prints the relevant response to the Ui.
     *
     * @param ui            The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     * @throws InvalidArgumentException when argument provided is index out of bounds of the ArrayList.
     */
    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager) throws InvalidArgumentException {
        StringBuilder result = new StringBuilder();
        NusModule module = moduleManager.getModule(getModuleName());
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
