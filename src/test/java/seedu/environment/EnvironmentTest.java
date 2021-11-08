package seedu.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import environment.Player;
import org.junit.jupiter.api.Test;

public class EnvironmentTest {
    @Test
    public void nameTest() {
        String testPlayerName = "Jim";
        Player firstPlayer = new Player(testPlayerName);
        assertEquals(firstPlayer.getName(), testPlayerName);
    }

}
