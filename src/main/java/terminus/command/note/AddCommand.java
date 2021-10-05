package terminus.command.note;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.ContentManager;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class AddCommand extends Command {

    private String name;
    private String data;

    private static final String COMMAND_FORMAT = " \"{item name}\" \"{item content}\"";

    private static final int TOTAL_ARGUMENTS = 2;

    public AddCommand() {

    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD + COMMAND_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        // Perform required checks with regex
        if (arguments == null || arguments.isBlank()) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        ArrayList<String> argArray = findArguments(arguments);
        if (!isValidArguments(argArray)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        this.name = argArray.get(0);
        this.data = argArray.get(1);
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        ContentManager contentManager = module.getContentManager();
        contentManager.setContent(module.getNotes());
        contentManager.addNote(name, data);
        module.setNotes(contentManager.getContents());
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_NOTE));
        return new CommandResult(true, false);
    }

    private ArrayList<String> findArguments(String arg) {
        ArrayList<String> argsArray = new ArrayList<>();
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(arg);
        while (m.find()) {
            argsArray.add(m.group(1));
        }
        return argsArray;
    }

    private boolean isValidArguments(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != TOTAL_ARGUMENTS) {
            isValid = false;
        } else if (isArrayEmpty(argArray)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isArrayEmpty(ArrayList<String> argArray) {
        for (String s : argArray) {
            if (s.isBlank()) {
                return true;
            }
        }
        return false;
    }
}
