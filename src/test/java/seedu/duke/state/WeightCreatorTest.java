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


class WeightCreatorTest {
    WeightCreator weightCreator = new WeightCreator(new Ui());

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void setWeight_oneInvalidInput_throwsException()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = weightCreator.getClass().getDeclaredMethod("setWeight", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(weightCreator, "dg"));
    }

    @Test
    void setWeight_emptyInput_throwsException()
            throws NoSuchMethodException {
        Method method = weightCreator.getClass().getDeclaredMethod("setWeight", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(weightCreator, ""));
    }

    @Test
    void setWeight_spacesInput_throwsException() throws NoSuchMethodException {
        Method method = weightCreator.getClass().getDeclaredMethod("setWeight", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(weightCreator, "    "));
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(weightCreator, " " + ls));
    }

    @Test
    void checkWeight_weightInstance_validMessage()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = weightCreator.getClass().getDeclaredMethod("setWeight", String.class);
        Method checkMethod = weightCreator.getClass().getDeclaredMethod("checkWeight");
        method.setAccessible(true);
        method.invoke(weightCreator, "0");
        checkMethod.setAccessible(true);
        checkMethod.invoke(weightCreator);
        String expected = "___________________________________________________"
                + "_______________________________________________________" + ls
                + "Your weight cannot be of this value!" + ls
                + "Maybe you can try a number from 1 to 300." + ls
                + "__________________________________________________________________"
                + "________________________________________";
        Assertions.assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        String expected1 = expected + ls
                + "_____________________________________________________________"
                + "_____________________________________________" + ls
                + "Your weight is 4.0kg.";
        method.invoke(weightCreator, "4");
        checkMethod.invoke(weightCreator);
        Assertions.assertEquals(expected1, outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}