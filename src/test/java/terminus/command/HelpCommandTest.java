package terminus.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class HelpCommandTest {

    private MainCommandParser mainCommandParser;
    private NoteCommandParser noteCommandParser;
    private LinkCommandParser linkCommandParser;
    private Ui ui;
    private NusModule nusModule;

    @BeforeEach
    void setUp() {
        mainCommandParser = MainCommandParser.getInstance();
        noteCommandParser = NoteCommandParser.getInstance();
        linkCommandParser = LinkCommandParser.getInstance();
        ui = new Ui();
        nusModule = new NusModule();
    }

    @Test
    void execute_helpCommand_success() throws InvalidArgumentException, InvalidCommandException {
        CommandResult result = mainCommandParser.parseCommand("help").execute(ui, nusModule);
        assertTrue(result.isOk());
        result = noteCommandParser.parseCommand("help").execute(ui, nusModule);
        assertTrue(result.isOk());
        result = linkCommandParser.parseCommand("help").execute(ui, nusModule);
        assertTrue(result.isOk());
    }
}
