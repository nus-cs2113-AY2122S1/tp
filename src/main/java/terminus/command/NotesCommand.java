package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.parser.NoteCommandParser;

public class NotesCommand extends WorkspaceCommand {

    public NotesCommand() {
        super(NoteCommandParser.getInstance());
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_NOTE;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_NOTE;
    }

}
