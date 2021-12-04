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

class HeightCreatorTest {
    HeightCreator heightCreator = new HeightCreator(new Ui());

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void setHeight_oneInvalidInput_throwsException()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = heightCreator.getClass().getDeclaredMethod("setHeight", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(heightCreator, "dg"));
    }

    @Test
    void setHeight_emptyInput_throwsException()
            throws NoSuchMethodException {
        Method method = heightCreator.getClass().getDeclaredMethod("setHeight", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(heightCreator, ""));
    }

    @Test
    void setHeight_spacesInput_throwsException() throws NoSuchMethodException {
        Method method = heightCreator.getClass().getDeclaredMethod("setHeight", String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(heightCreator, "    "));
        Assertions.assertThrows(InvocationTargetException.class,
            () -> method.invoke(heightCreator, " " + ls));
    }

    @Test
    void checkHeight_heightInstance_validMessage()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = heightCreator.getClass().getDeclaredMethod("setHeight", String.class);
        Method checkMethod = heightCreator.getClass().getDeclaredMethod("checkHeight");
        method.setAccessible(true);
        method.invoke(heightCreator, "0");
        checkMethod.setAccessible(true);
        checkMethod.invoke(heightCreator);
        String expected = "________________________________________________________"
                + "__________________________________________________" + ls
                + "Your height cannot be of this value!" + ls
                + "Maybe you can try a number from 1 to 300." + ls
                + "________________________________________________"
                + "__________________________________________________________";
        Assertions.assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        String expected1 = expected + ls
                + "___________________________________________________________"
                + "_______________________________________________" + ls
                + "Your height is 4.0cm.";
        method.invoke(heightCreator, "4");
        checkMethod.invoke(heightCreator);
        Assertions.assertEquals(expected1, outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}