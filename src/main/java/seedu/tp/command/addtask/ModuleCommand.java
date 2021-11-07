package seedu.tp.command.addtask;

import seedu.tp.command.Command;
import seedu.tp.command.CommandResult;
import seedu.tp.exception.ModuleStringFormatIncorrectException;
import seedu.tp.exception.NoSuchModuleException;
import seedu.tp.nusmods.NusModsParser;
import seedu.tp.task.taskmanager.TaskManager;

import java.util.Arrays;
import java.util.Map;

//@@author SuibianP
/**
 * Command to add module lessons.
 */
public class ModuleCommand extends Command {

    private static final String USAGE = "-> Adding a module: module {<module code>:{<class number>,}...;}...";

    public ModuleCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = "";
        try {
            for (String piece : getMainArgument().split(";")) {
                String[] pair = piece.split(":");
                if (pair.length != 2) {
                    throw new ModuleStringFormatIncorrectException();
                }
                String moduleCode = pair[0];
                for (String classNo : pair[1].split(",")) {
                    taskManager.addTasks(Arrays.asList(new NusModsParser().getLessons(moduleCode, classNo)));
                    message += "Added " + moduleCode + ' ' + classNo + System.lineSeparator();
                }
            }
        } catch (ModuleStringFormatIncorrectException msfie) {
            message = msfie.getMessage();
        } catch (NoSuchModuleException nsme) {
            message = nsme.getMessage();
        }
        return new CommandResult(message, false);
    }
}
