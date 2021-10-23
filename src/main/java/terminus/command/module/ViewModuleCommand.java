package terminus.command.module;

import java.util.stream.IntStream;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;

public class ViewModuleCommand extends Command {

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_VIEW_MODULE_FORMAT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_MODULE_VIEW;
    }

    /**
     * Executes the command. Prints the required result to the Ui.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException  when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        String[] modules = moduleManager.getAllModules();
        String[] listOfModules = IntStream.range(0, modules.length)
                .mapToObj(i -> String.format(Messages.MESSAGE_RESPONSE_MODULE_FORMAT, i + 1, modules[i]))
                .toArray(String[]::new);
        
        if (listOfModules.length == 0) {
            return new CommandResult(Messages.MESSAGE_RESPONSE_NO_MODULES);
        } else {
            return new CommandResult(listOfModules);
        }
    }
}
