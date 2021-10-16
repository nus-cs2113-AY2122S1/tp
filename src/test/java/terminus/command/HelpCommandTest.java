package terminus.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;
import terminus.parser.ModuleCommandParser;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class HelpCommandTest {

    private MainCommandParser mainCommandParser;
    private NoteCommandParser noteCommandParser;
    private LinkCommandParser linkCommandParser;
    private ModuleCommandParser moduleCommandParser;
    private Ui ui;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        mainCommandParser = MainCommandParser.getInstance();
        noteCommandParser = NoteCommandParser.getInstance();
        noteCommandParser.setModuleName(tempModule);
        linkCommandParser = LinkCommandParser.getInstance();
        linkCommandParser.setModuleName(tempModule);
        moduleCommandParser = ModuleCommandParser.getInstance();
        ui = new Ui();
        moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
    }

    @Test
    void execute_helpCommand_success() throws InvalidArgumentException, InvalidCommandException {
        CommandResult result = mainCommandParser.parseCommand("help").execute(ui, moduleManager);
        assertTrue(result.isOk());
        result = noteCommandParser.parseCommand("help").execute(ui, moduleManager);
        assertTrue(result.isOk());
        result = linkCommandParser.parseCommand("help").execute(ui, moduleManager);
        assertTrue(result.isOk());
        result = moduleCommandParser.parseCommand("help").execute(ui, moduleManager);
        assertTrue(result.isOk());
    }
}
