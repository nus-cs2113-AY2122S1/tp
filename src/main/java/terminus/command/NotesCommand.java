package terminus.command;

import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class NotesCommand extends Command {

    private String arguments;

    @Override
    public String getFormat() {
        return "notes";
    }

    @Override
    public StringBuilder getHelpMessage() {
        return null;
    }

    @Override
    public void parseArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        NoteCommandParser notesMap = NoteCommandParser.getInstance();
        if (arguments != null && !arguments.strip().isEmpty()) {
            return notesMap.parseCommand(arguments).execute(ui, module);
        } else {
            return new CommandResult(true,false, notesMap,null);
        }
    }

}
