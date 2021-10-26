package seedu.duke.command.addtask;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.ModuleStringFormatIncorrectException;
import seedu.duke.nusmods.NusModsParser;
import seedu.duke.task.TaskManager;

import java.util.Arrays;
import java.util.Map;

public class ModuleCommand extends Command {
    private static final CommandEnum COMMAND = CommandEnum.MODULE;

    public ModuleCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
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
                    TaskManager.addTasks(Arrays.asList(new NusModsParser().getLessons(moduleCode, classNo)));
                    message += "Added " + moduleCode + ' ' + classNo + System.lineSeparator();
                }
            }
        } catch (ModuleStringFormatIncorrectException msfie) {
            message = msfie.getMessage();
        }
        return new CommandResult(message, true, false);
    }
}
