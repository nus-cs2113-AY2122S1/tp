package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Question;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.MainCommandParser;
import terminus.parser.QuestionCommandParser;
import terminus.ui.Ui;

public class QuestionCommandTest {

    private MainCommandParser commandParser;
    private Ui ui;

    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        commandParser = MainCommandParser.getInstance();
        moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        ui = new Ui();
    }

    @Test
    void execute_success() throws InvalidArgumentException, InvalidCommandException, IOException, DocumentException {
        Command mainCommand = commandParser.parseCommand("go " + tempModule + " question");
        CommandResult changeResult = mainCommand.execute(ui, moduleManager);
        assertTrue(changeResult.isOk());
        assertTrue(changeResult.getAdditionalData() instanceof QuestionCommandParser);
        mainCommand = commandParser.parseCommand("go " + tempModule + " question add \"username\" \"password\"");
        changeResult = mainCommand.execute(ui, moduleManager);
        assertTrue(changeResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Question.class).getTotalContents());
        mainCommand = commandParser.parseCommand("go " + tempModule + " question view");
        changeResult = mainCommand.execute(ui, moduleManager);
        assertTrue(changeResult.isOk());
    }

    @Test
    void execute_throwsException() {
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("go " + tempModule + " question -1").execute(ui, moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " question view 100").execute(ui, moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " question delete -1").execute(ui, moduleManager));

    }
}
