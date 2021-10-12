package terminus.command.zoomlink;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.exception.InvalidLinkException;
import terminus.exception.InvalidTimeFormatException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidDayException;
import terminus.module.NusModule;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.ui.Ui;

import java.time.LocalTime;
import java.util.ArrayList;

import static terminus.common.CommonFormat.isValidDay;
import static terminus.common.CommonFormat.isValidUrl;

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
    public void parseArguments(String arguments)
            throws InvalidArgumentException, InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        // Perform required checks with regex
        if (arguments == null || arguments.isBlank()) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        ArrayList<String> argArray = CommonFormat.findArguments(arguments);
        assert argArray.size() > 0;

        if (!isValidScheduleArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        String userStartTime = argArray.get(2);

        this.description = argArray.get(0);
        this.day = argArray.get(1);
        this.startTime = CommonFormat.localTimeConverter(userStartTime);
        this.link = argArray.get(3);

        if (!isValidDay(this.day)) {
            TerminusLogger.warning(String.format("Invalid Day: %s", this.day));
            throw new InvalidDayException(String.format(Messages.ERROR_MESSAGE_INVALID_DAY, this.day));
        }
        if (!isValidUrl(this.link)) {
            TerminusLogger.warning(String.format("Invalid Link: %s", this.link));
            throw new InvalidLinkException(
                    String.format(Messages.ERROR_MESSAGE_INVALID_LINK, this.link));
        }
        TerminusLogger.info(String.format("Parsed arguments (description = %s, day = %s, startTime = %s, link = %s)"
                + " to Add Link Command", description, day, startTime, link));
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException, InvalidArgumentException {
        ContentManager contentManager = module.getContentManager(Link.class);
        assert contentManager != null;

        contentManager.add(new Link(description, day, startTime, link));
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_SCHEDULE, description));
        return new CommandResult(true, false);
    }

    private boolean isValidScheduleArguments(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != ADD_SCHEDULE_ARGUMENTS) {
            TerminusLogger.warning(String.format("Failed to find %d arguments, %d arguments found",
                    ADD_SCHEDULE_ARGUMENTS, argArray.size()));
            isValid = false;
        } else if (CommonFormat.isArrayEmpty(argArray)) {
            TerminusLogger.warning("Failed to parse arguments, some arguments found is empty");
            isValid = false;
        }
        return isValid;
    }
}
