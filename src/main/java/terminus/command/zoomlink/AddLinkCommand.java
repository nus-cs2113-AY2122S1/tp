package terminus.command.zoomlink;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.ContentManager;
import terminus.exception.InvalidTimeFormatException;
import terminus.module.NusModule;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.ui.Ui;

import java.time.LocalTime;
import java.util.ArrayList;

public class AddLinkCommand extends Command {

    private String description;
    private String day;
    private LocalTime startTime;
    private String link;

    private static final int ADD_SCHEDULE_ARGUMENTS = 4;

    public AddLinkCommand() {

    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_SCHEDULE_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException, InvalidTimeFormatException {
        // Perform required checks with regex
        if (arguments == null || arguments.isBlank()) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        ArrayList<String> argArray = CommonFormat.findArguments(arguments);
        if (!isValidScheduleArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        String userStartTime = argArray.get(2);

        this.description = argArray.get(0);
        this.day = argArray.get(1);
        this.startTime = CommonFormat.convertToLocalTime(userStartTime);
        this.link = argArray.get(3);
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException, InvalidArgumentException {
        ContentManager contentManager = module.getContentManager();
        contentManager.setContent(module.getLinks());
        contentManager.addLink(description, day, startTime, link);
        module.setLinks(contentManager.getContents());
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_SCHEDULE, description));
        return new CommandResult(true, false);
    }

    private boolean isValidScheduleArguments(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != ADD_SCHEDULE_ARGUMENTS) {
            isValid = false;
        } else if (CommonFormat.isArrayEmpty(argArray)) {
            isValid = false;
        }
        return isValid;
    }
}
