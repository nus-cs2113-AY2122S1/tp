package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    void readInput_eof_Success() {
        InputStream backup = System.in;
        System.setIn(InputStream.nullInputStream());
        Duke duke = new Duke();
        duke.readInput();
        System.setIn(backup);
    }
}
