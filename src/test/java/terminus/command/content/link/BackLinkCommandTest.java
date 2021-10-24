package terminus.command.content.link;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.parser.ModuleWorkspaceCommandParser;


public class BackLinkCommandTest {

    private LinkCommandParser linkCommandParser;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.linkCommandParser.setModuleName(tempModule);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        Command backCommand = linkCommandParser.parseCommand("back");
        CommandResult backResult = backCommand.execute(moduleManager);
        assertTrue(backResult.isOk());
        assertTrue(backResult.getNewCommandParser() instanceof ModuleWorkspaceCommandParser);
    }

}