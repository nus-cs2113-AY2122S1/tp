package seedu.duke.command;

import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.command.flags.TodoFlag;
import seedu.duke.parser.DateParser;
import seedu.duke.parser.HelpCommandParser;

//@@author SeanRobertDH
public enum CommandEnum {
    BYE("bye"),
    HELP("help"),
    LIST("list"),
    SORT("sort"),
    DELETE("delete <index>"),
    TODO("todo <description> [%s %s]"),
    DEADLINE("deadline <description> <%s %s>"),
    EVENT("event <description> <%s %s> <%s %s>"),
    REMINDER("reminder"),
    INVALID("");

    private static final String OPTIONAL_ARGUMENT_FORMAT = "[%s]";
    private static final String ARGUMENT_SPLIT = "|";

    private final String usageRegex;

    CommandEnum(String usage) {
        this.usageRegex = usage;
    }

    protected String getUsage() {
        String usage = usageRegex;
        switch (this) {
        case BYE:
            //Fallthrough
        case HELP:
            //Fallthrough
        case LIST:
            //Fallthrough
        case REMINDER:

        case DELETE:
            return usage;
        case TODO:
            usage = String.format(usage, TodoFlag.DO_ON_DATE, DateParser.getDefaultDateFormat());
            break;
        case DEADLINE:
            usage = String.format(usage, DeadlineFlag.DUE_DATE, DateParser.getDefaultDateFormat());
            break;
        case EVENT:
            usage = String.format(usage,
                EventFlag.START_DATE, DateParser.getDefaultDateFormat(),
                EventFlag.END_DATE, DateParser.getDefaultDateFormat());
            break;
        default:
            return usage;
        }
        return usage + ' ' + HelpCommandParser.getOptionalTaskArguments(OPTIONAL_ARGUMENT_FORMAT, ARGUMENT_SPLIT);
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static CommandEnum getCommand(String command) {
        if (command == null) {
            return CommandEnum.INVALID;
        }
        for (CommandEnum commandEnum : values()) {
            if (command.equalsIgnoreCase(commandEnum.name())) {
                return commandEnum;
            }
        }
        return CommandEnum.INVALID;
    }
}
