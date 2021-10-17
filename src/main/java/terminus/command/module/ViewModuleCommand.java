package terminus.command.module;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.ui.Ui;

public class ViewModuleCommand extends Command {

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return "view";
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return "View all modules available";
    }

    /**
     * Executes the command. Prints the required result to the Ui.
     *
     * @param ui            The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException  when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        String[] modules = moduleManager.getAllModules();
        String[] listOfModules = IntStream.range(0, modules.length)
                .mapToObj(i -> String.format("%d. %s", i + 1, modules[i]))
                .toArray(String[]::new);
        if (listOfModules.length == 0) {
            ui.printSection("You do not have any modules");
        } else {
            ui.printSection(listOfModules);
        }
        return new CommandResult(true);
    }
}
