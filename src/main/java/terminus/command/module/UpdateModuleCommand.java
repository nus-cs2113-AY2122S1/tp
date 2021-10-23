package terminus.command.module;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class UpdateModuleCommand extends Command {

    public static final String REGEX_GROUP_NEWNAME = "newName";
    public static final String REGEX_GROUP_INDEX = "index";
    private int index;
    private String newName;

    @Override
    public String getFormat() {
        return CommonFormat.UPDATE_COMMAND_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.UPDATE_COMMAND_MESSAGE;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        Pattern p = Pattern.compile(CommonFormat.UPDATE_MODULE_REGEX_FORMAT);
        Matcher m = p.matcher(arguments);
        if (!m.matches()) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        try {
            index = Integer.parseInt(m.group(REGEX_GROUP_INDEX));
        } catch (NumberFormatException e) {
            TerminusLogger.warning(String.format("Failed to parse index : %s", arguments));
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }

        newName = m.group(REGEX_GROUP_NEWNAME);
        if (!newName.matches(CommonFormat.SPACE_NEGATED_DELIMITER)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MODULE_WHITESPACE);
        }
    }

    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException, IOException {
        String[] listOfModule = moduleManager.getAllModules();
        if (!CommonUtils.isValidIndex(index, listOfModule)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        if (moduleManager.getModule(newName) != null) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MODULE_EXIST);
        }
        assert index > 0;
        NusModule current = moduleManager.getModule(listOfModule[index - 1]);
        moduleManager.removeModule(listOfModule[index - 1]);

        moduleManager.setModule(newName, current);
        ui.printSection(String.format("Updated %s to %s successfully", listOfModule[index - 1], newName));
        return new CommandResult(true);
    }
}
