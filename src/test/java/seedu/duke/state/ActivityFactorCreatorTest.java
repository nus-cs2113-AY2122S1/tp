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

class ActivityFactorCreatorTest {
    ActivityFactorCreator activityFactorCreator = new ActivityFactorCreator(new Ui());

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void setActivityFactor_oneInvalidInput_throwsException()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = activityFactorCreator.getClass().getDeclaredMethod("setActivityFactor", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(activityFactorCreator, "dg"));
    }

    @Test
    void setActivityFactor_emptyInput_throwsException()
            throws NoSuchMethodException {
        Method method = activityFactorCreator.getClass().getDeclaredMethod("setActivityFactor", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(activityFactorCreator, ""));
    }

    @Test
    void setActivityFactor_spacesInput_throwsException() throws NoSuchMethodException {
        Method method = activityFactorCreator.getClass().getDeclaredMethod("setActivityFactor", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(activityFactorCreator, "    "));
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(activityFactorCreator, " " + ls));
    }

    @Test
    void checkActivityFactor_activityFactorInstance_validMessage()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = activityFactorCreator.getClass().getDeclaredMethod("setActivityFactor", String.class);
        Method checkMethod = activityFactorCreator.getClass().getDeclaredMethod("checkActivityFactor");
        method.setAccessible(true);
        method.invoke(activityFactorCreator, "0");
        checkMethod.setAccessible(true);
        checkMethod.invoke(activityFactorCreator);
        String expected = "_________________________________________________________"
                + "_________________________________________________" + ls
                + "Your activity factor cannot be of this value!" + ls
                + "Maybe you can try a whole number from 1 to 5." + ls
                + "__________________________________________________________________"
                + "________________________________________";
        Assertions.assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        String expected1 = expected + ls
                + "__________________________________________________________"
                + "________________________________________________" + ls
                + "Your activity factor is 4.";
        method.invoke(activityFactorCreator, "4");
        checkMethod.invoke(activityFactorCreator);
        Assertions.assertEquals(expected1, outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}