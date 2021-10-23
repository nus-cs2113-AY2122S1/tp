package terminus.command.content.question;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.ModuleWorkspaceCommandParser;
import terminus.parser.QuestionCommandParser;
import terminus.ui.Ui;

public class BackQuestionCommandTest {

    private QuestionCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
        this.moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        this.ui = new Ui();
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException, IOException, DocumentException {
        Command backCommand = commandParser.parseCommand("back");
        CommandResult backResult = backCommand.execute(ui, moduleManager);
        assertTrue(backResult.isOk());
        assertTrue(backResult.getAdditionalData() instanceof ModuleWorkspaceCommandParser);
    }
}
