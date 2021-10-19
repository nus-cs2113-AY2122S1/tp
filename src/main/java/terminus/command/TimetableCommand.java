package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.ui.Ui;

import static terminus.common.CommonUtils.isStringNullOrEmpty;
import static terminus.common.CommonUtils.isValidDay;

public class TimetableCommand extends Command {
    private String day;

    public TimetableCommand() {

    }

    public String getFormat() {
        return CommonFormat.COMMAND_TIMETABLE_FORMAT;
    }

    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_TIMETABLE;
    }

    public void parseArguments(String arguments) throws InvalidArgumentException {
        day = arguments;
        if (!isStringNullOrEmpty(day) && !isValidDay(day)) {
            TerminusLogger.warning(String.format("Invalid Day: %s", day));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DAY, day));
        }
    }

    public CommandResult execute(Ui ui, ModuleManager moduleManager) {
        String[] modules = moduleManager.getAllModules();
        StringBuilder result = new StringBuilder();
        for (String moduleName : modules) {
            NusModule module = moduleManager.getModule(moduleName);
            ContentManager<Link> contentManager = module.getContentManager(Link.class);
            String schedules =  contentManager.listAllContents();
            result.append(schedules);
        }

        if (result.toString().isBlank()) {
            result.append(Messages.EMPTY_CONTENT_LIST_MESSAGE);
        }
        ui.printSection(result.toString());
        return new CommandResult(true, false);
    }
}
