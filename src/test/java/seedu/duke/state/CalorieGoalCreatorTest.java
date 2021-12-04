package seedu.duke.state;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class CalorieGoalCreatorTest {
    CalorieGoalCreator calorieGoalCreator = new CalorieGoalCreator(new Ui());

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void setCalorieGoal_oneInvalidInput_throwsException()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = calorieGoalCreator.getClass().getDeclaredMethod("setCalorieGoal", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(calorieGoalCreator, "dg"));
    }

    @Test
    void setCalorieGoal_emptyInput_throwsException()
            throws NoSuchMethodException {
        Method method = calorieGoalCreator.getClass().getDeclaredMethod("setCalorieGoal", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(calorieGoalCreator, ""));
    }

    @Test
    void setCalorieGoal_spacesInput_throwsException() throws NoSuchMethodException {
        Method method = calorieGoalCreator.getClass().getDeclaredMethod("setCalorieGoal", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(calorieGoalCreator, "    "));
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(calorieGoalCreator, " " + ls));
    }

    @Test
    void checkCalorieGoal_calorieGoalInstance_validMessage()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = calorieGoalCreator.getClass().getDeclaredMethod("setCalorieGoal", String.class);
        Method checkMethod = calorieGoalCreator.getClass().getDeclaredMethod("checkCalorieGoal");
        method.setAccessible(true);
        method.invoke(calorieGoalCreator, "0");
        checkMethod.setAccessible(true);
        checkMethod.invoke(calorieGoalCreator);
        String expected = "____________________________________________________________"
                + "______________________________________________" + ls
                + "You calorie goal is 0 cal.";
        Assertions.assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        String expected1 = expected + ls
                + "________________________________________________________"
                + "__________________________________________________" + ls
                + "You calorie goal is 4 cal.";
        method.invoke(calorieGoalCreator, "4");
        checkMethod.invoke(calorieGoalCreator);
        Assertions.assertEquals(expected1, outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}