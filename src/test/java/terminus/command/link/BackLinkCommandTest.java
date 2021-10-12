package terminus.command.link;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;


public class BackLinkCommandTest {

    private LinkCommandParser linkCommandParser;
    private NusModule nusModule;
    private Ui ui;

    @BeforeEach
    void setUp() {
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException {
        Command backCommand = linkCommandParser.parseCommand("back");
        CommandResult backResult = backCommand.execute(ui, nusModule);
        assertTrue(backResult.isOk());
        assertTrue(backResult.getAdditionalData() instanceof MainCommandParser);
    }

}