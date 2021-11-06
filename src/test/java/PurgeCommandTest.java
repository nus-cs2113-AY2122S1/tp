import command.Data;
import command.PurgeCommand;
import inventory.Medicine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author alvintan01

public class PurgeCommandTest {
    private final InputStream sysIn = System.in;

    @BeforeEach
    public void getData() {
        Data.generateTestData();
    }

    @AfterEach
    public void tearDown() {
        System.setIn(sysIn);
        Medicine.getInstance().clear();
    }

    @Test
    public void purgeCommand_inputN_expectDataNotCleared() {
        ByteArrayInputStream in = new ByteArrayInputStream("N\n".getBytes());
        System.setIn(in); // Send 'N' to scanner
        new PurgeCommand().execute();
        assertEquals(Medicine.getInstance().size(), 18);
    }

    @Test
    public void purgeCommand_inputY_expectDataCleared() {
        ByteArrayInputStream in = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(in); // Send 'Y' to scanner
        new PurgeCommand().execute();
        assertEquals(Medicine.getInstance().size(), 0);
    }
}
