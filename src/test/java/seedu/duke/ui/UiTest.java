package seedu.duke.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.parser.exceptions.MissingParamException;


class UiTest {
    @Test
    void checkEmptyUserInput_emptyStringInput_throwsException() {
        Ui ui = new Ui();
        Assertions.assertThrows(MissingParamException.class, () -> ui.checkEmptyUserInput(""));
    }

    @Test
    void checkEmptyUserInput_oneStringInput_doesNotThrowException() {
        Ui ui = new Ui();
        Assertions.assertDoesNotThrow(() -> ui.checkEmptyUserInput("1"));
        Assertions.assertDoesNotThrow(() -> ui.checkEmptyUserInput("this"));
        Assertions.assertDoesNotThrow(() -> ui.checkEmptyUserInput(" "));
    }
}