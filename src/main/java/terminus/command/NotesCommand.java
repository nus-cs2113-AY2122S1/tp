package terminus.command;


import terminus.parser.NoteCommandParser;

public class NotesCommand extends WorkspaceCommand {

    private String arguments;

    public NotesCommand() {
        super(NoteCommandParser.getInstance());
    }

    @Override
    public String getFormat() {
        return "notes";
    }

    @Override
    public String getHelpMessage() {
        return "Move to notes workspace.";
    }
}
