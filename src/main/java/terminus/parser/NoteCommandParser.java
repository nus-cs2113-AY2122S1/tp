package terminus.parser;

import terminus.command.DeleteCommand;
import terminus.command.ViewCommand;
import terminus.command.note.AddCommand;
import terminus.command.BackCommand;
import terminus.common.CommonFormat;
import terminus.content.Note;


public class NoteCommandParser extends CommandParser {

    public NoteCommandParser() {
        super(CommonFormat.COMMAND_NOTE);
    }

    public static NoteCommandParser getInstance() {
        NoteCommandParser parser = new NoteCommandParser();
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        parser.addCommand(CommonFormat.COMMAND_ADD, new AddCommand());
        parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewCommand<Class<Note>>(Note.class));
        parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteCommand<Class<Note>>(Note.class));
        return parser;
    }
}
