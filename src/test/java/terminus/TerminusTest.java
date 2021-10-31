package terminus;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;

class TerminusTest {

    private static final String LS = System.lineSeparator();
    private static InputStream oldInput;

    @BeforeAll
    static void beforeAll() {
        oldInput = System.in;
    }

    @AfterAll
    static void afterAll() {
        System.setIn(oldInput);
    }

    @Test
    void run_exit_success() {
        String input = String.format("exit%s", LS);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Terminus.main(null);
    }
    
    @Test
    void run_invalidCommand_exit_success() {
        String input = String.format("exita%shelp%sexit%s", LS, LS, LS);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Terminus terminus = new Terminus(new Ui(in), MainCommandParser.getInstance());
        terminus.run();
    }
    
    @Test
    void safety_test() {
        Terminus terminus = new Terminus();
        terminus.initialize();
        terminus.exit();
    }

    @Test
    void handleUserInput_success() {
        Terminus terminus = new Terminus();
        terminus.initialize();
        assertTrue(terminus.handleUserInput("exit").isExit());
    }

    @Test
    void handleUserInput_exceptionThrown_returnNull() {
        Terminus terminus = new Terminus();
        terminus.initialize();
        assertNull(terminus.handleUserInput("exita"));
        assertNull(terminus.handleUserInput("module add"));
        assertNull(terminus.handleUserInput("module delete 10"));
    }

    @Test
    void handleCommandResult_success() {
        Terminus terminus = new Terminus();
        terminus.initialize();
        terminus.handleCommandResult(terminus.handleUserInput("module add \"test\""));
        terminus.handleCommandResult(terminus.handleUserInput("go test"));
        terminus.handleCommandResult(terminus.handleUserInput("note"));
        terminus.handleCommandResult(terminus.handleUserInput("back"));
        terminus.handleCommandResult(terminus.handleUserInput("schedule"));
        terminus.handleCommandResult(terminus.handleUserInput("back"));
        terminus.handleCommandResult(terminus.handleUserInput("question"));
        terminus.handleCommandResult(terminus.handleUserInput("back"));
        terminus.handleCommandResult(terminus.handleUserInput("back"));
        terminus.handleCommandResult(terminus.handleUserInput("module delete 1"));
    }
    
    @Test
    void handleStorage_success() {
        Terminus terminus = new Terminus();
        terminus.initialize();
        terminus.handleStorage(terminus.handleUserInput("module add \"test\""));
        terminus.handleStorage(terminus.handleUserInput("module delete 1"));
    }

}
