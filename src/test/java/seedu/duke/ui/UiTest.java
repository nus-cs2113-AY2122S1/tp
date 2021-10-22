package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UiTest {
    @Test
    public void moduleInfo_invalidModuleCode_exceptionThrown() {
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> ui.printModuleInfo("hello"));
    }
}
