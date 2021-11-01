package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author Roycius
public class UiTest {
    @Test
    public void moduleInfo_invalidModuleCode_exceptionThrown() {
        Duke duke = new Duke();
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> ui.printModuleInfo("hello", false));
    }
}
