package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.DeleteCommand;
import terminus.command.ViewCommand;
import terminus.command.note.AddNoteCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.Note;
import terminus.module.ModuleManager;
import terminus.module.NusModule;


public class NoteCommandParser extends InModuleCommandParser {

    public NoteCommandParser() {
        super(CommonFormat.COMMAND_NOTE);
    }

    public static NoteCommandParser getInstance() {
        NoteCommandParser parser = new NoteCommandParser();
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        parser.addCommand(CommonFormat.COMMAND_ADD, new AddNoteCommand());
        parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewCommand(Note.class));
        parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteCommand(Note.class));
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager module) {
        return String.format(Messages.NOTE_BANNER,
                module.getModule(getModuleName()).getContentManager(Note.class).getContents().size());
    }
}
