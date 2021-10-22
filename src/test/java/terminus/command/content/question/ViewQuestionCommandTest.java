package terminus.command.content.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Note;
import terminus.content.Question;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.NoteCommandParser;
import terminus.parser.QuestionCommandParser;
import terminus.ui.Ui;

public class ViewQuestionCommandTest {

    private QuestionCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;

    private String tempModule = "test";

    Class<Question> type = Question.class;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
        this.ui = new Ui();
    }

    @Test
    void execute_viewAll_success()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command viewCommand = commandParser.parseCommand("view");
        CommandResult viewResult = viewCommand.execute(ui, moduleManager);
        assertTrue(viewResult.isOk());
    }

    @Test
    void execute_viewOne_success()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command viewCommand = commandParser.parseCommand("view 1");
        CommandResult viewResult = viewCommand.execute(ui, moduleManager);
        assertTrue(viewResult.isOk());
    }

    @Test
    void execute_viewOne_exceptionThrown()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("view a"));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("view -1").execute(ui, moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("view 6").execute(ui, moduleManager));
    }
}
