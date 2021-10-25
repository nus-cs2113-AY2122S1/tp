package terminus.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.common.CommonFormat;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.MainCommandParser;

public class ExitCommandTest {

    private MainCommandParser commandParser;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.commandParser = MainCommandParser.getInstance();
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command exitCommand = commandParser.parseCommand(CommonFormat.COMMAND_EXIT);
        CommandResult mainResult = exitCommand.execute(moduleManager);
        assertTrue(mainResult.isOk() && mainResult.isExit());

        Command noteExitCommand = commandParser
            .parseCommand("go " + tempModule + " " + CommonFormat.COMMAND_NOTE + " " + CommonFormat.COMMAND_EXIT);
        CommandResult noteResult = noteExitCommand.execute(moduleManager);
        assertTrue(noteResult.isOk() && noteResult.isExit());

        Command scheduleExitCommand = commandParser
                .parseCommand(
                        "go " + tempModule + " " + CommonFormat.COMMAND_SCHEDULE + " " + CommonFormat.COMMAND_EXIT);
        CommandResult scheduleExitResult = scheduleExitCommand.execute(moduleManager);
        assertTrue(scheduleExitResult.isOk() && scheduleExitResult.isExit());

        Command goCommandExitCommand = commandParser.parseCommand("go " + tempModule + " " + CommonFormat.COMMAND_EXIT);
        CommandResult goCommandExitCommandResult = goCommandExitCommand.execute(moduleManager);
        assertTrue(goCommandExitCommandResult.isOk() && goCommandExitCommandResult.isExit());

        Command moduleCommandExitCommand =
                commandParser.parseCommand("module " + CommonFormat.COMMAND_EXIT);
        CommandResult moduleCommandExitCommandResult = moduleCommandExitCommand.execute(moduleManager);
        assertTrue(moduleCommandExitCommandResult.isOk() && moduleCommandExitCommandResult.isExit());
    }
}
