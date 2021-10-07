package happybit.command;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void testAddHabit() {
        AddCommand addCommand = new AddCommand();
        String H0 = "h0";
        String H1 = "h1";
        String H2 = "h2";

        assertEquals(0, addCommand.getHabitListSize());

        addCommand.addHabit(H0);
        assertEquals(1, addCommand.getHabitListSize());
        assertArrayEquals(new String[] {H0}, addCommand.getHabits().toArray());

        addCommand.addHabit(H1);
        assertEquals(2, addCommand.getHabitListSize());
        assertArrayEquals(new String[] {H0, H1}, addCommand.getHabits().toArray());

        addCommand.addHabit(H2);
        assertEquals(3, addCommand.getHabitListSize());
        assertArrayEquals(new String[] {H0, H1, H2}, addCommand.getHabits().toArray());
    }
}
