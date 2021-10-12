package terminus.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.common.CommonFormat;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;

public class ExitCommandTest {
    
    private MainCommandParser commandParser;
    private NusModule nusModule;
    private Ui ui;

    @BeforeEach
    void setUp() {
        this.commandParser = MainCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void execute_success() throws InvalidArgumentException, InvalidCommandException {
        Command exitCommand = commandParser.parseCommand(CommonFormat.COMMAND_EXIT);
        CommandResult mainResult = exitCommand.execute(ui, nusModule);
        assertTrue(mainResult.isOk() && mainResult.isExit());

        Command noteExitCommand = commandParser
            .parseCommand(CommonFormat.COMMAND_NOTE + " " + CommonFormat.COMMAND_EXIT);
        CommandResult noteResult = noteExitCommand.execute(ui, nusModule);
        assertTrue(noteResult.isOk() && noteResult.isExit());

        Command scheduleExitCommand = commandParser
            .parseCommand(CommonFormat.COMMAND_SCHEDULE + " " + CommonFormat.COMMAND_EXIT);
        CommandResult scheduleExitResult = scheduleExitCommand.execute(ui, nusModule);
        assertTrue(scheduleExitResult.isOk() && scheduleExitResult.isExit());
    }
}
