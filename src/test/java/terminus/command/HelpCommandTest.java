package terminus.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;
import terminus.parser.ModuleCommandParser;
import terminus.parser.NoteCommandParser;

public class HelpCommandTest {

    private MainCommandParser mainCommandParser;
    private NoteCommandParser noteCommandParser;
    private LinkCommandParser linkCommandParser;
    private ModuleCommandParser moduleCommandParser;
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
        moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_helpCommand_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        CommandResult result = mainCommandParser.parseCommand("help").execute(moduleManager);
        assertTrue(result.isOk());
        result = noteCommandParser.parseCommand("help").execute(moduleManager);
        assertTrue(result.isOk());
        result = linkCommandParser.parseCommand("help").execute(moduleManager);
        assertTrue(result.isOk());
        result = moduleCommandParser.parseCommand("help").execute(moduleManager);
        assertTrue(result.isOk());
    }
}
