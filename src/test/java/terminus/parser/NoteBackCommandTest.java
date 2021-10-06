package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sun.tools.javac.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class NoteBackCommandTest {

    private NoteCommandParser commandParser;
    private NusModule nusModule;
    private Ui ui;

    @BeforeEach
    void setUp() {
        this.commandParser = NoteCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void execute_deleteCommand_success() throws InvalidCommandException, InvalidArgumentException {
        Command backCommand = commandParser.parseCommand("back");
        CommandResult backResult = backCommand.execute(ui, nusModule);
        assertTrue(backResult.isOk());
        assertTrue(backResult.getAdditionalData() instanceof MainCommandParser);
    }
}
