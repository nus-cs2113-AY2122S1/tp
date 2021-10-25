package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.content.ViewCommand;
import terminus.command.content.note.AddNoteCommand;
import terminus.command.content.note.DeleteNoteCommand;
import terminus.command.content.note.ExportNoteCommand;
import terminus.command.content.note.ReloadNoteCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.Note;
import terminus.module.ModuleManager;


public class NoteCommandParser extends InnerModuleCommandParser {

    private static NoteCommandParser parser;

    private NoteCommandParser() {
        super(CommonFormat.COMMAND_NOTE);
    }

    public static NoteCommandParser getInstance() {
        if (parser == null) {
            parser = new NoteCommandParser();
            parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
            parser.addCommand(CommonFormat.COMMAND_ADD, new AddNoteCommand());
            parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewCommand(Note.class));
            parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteNoteCommand(Note.class));
            parser.addCommand(CommonFormat.COMMAND_EXPORT, new ExportNoteCommand());
            parser.addCommand(CommonFormat.COMMAND_RELOAD, new ReloadNoteCommand());
        }
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return String.format(Messages.NOTE_BANNER,
                moduleManager.getModule(getModuleName()).getContentManager(Note.class).getContents().size());
    }
}
