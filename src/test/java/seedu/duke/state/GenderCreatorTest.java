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

class GenderCreatorTest {
    GenderCreator genderCreator = new GenderCreator(new Ui());

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void setGender_emptyInput_throwsException()
            throws NoSuchMethodException {
        Method method = genderCreator.getClass().getDeclaredMethod("setGender",String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(genderCreator,""));
    }

    @Test
    void checkGender_genderInstance_validMessage()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = genderCreator.getClass().getDeclaredMethod("setGender",String.class);
        Method checkMethod = genderCreator.getClass().getDeclaredMethod("checkGender");
        method.setAccessible(true);
        method.invoke(genderCreator,"0");
        checkMethod.setAccessible(true);
        checkMethod.invoke(genderCreator);
        String expected = "____________________________________________________________________"
                + "______________________________________" + ls
                + "Please type in M or F only!";
        Assertions.assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        String expected1 = expected + ls
                + "_____________________________________________________________________"
                + "_____________________________________" + ls
                + "You are a male.";
        method.invoke(genderCreator,"m");
        checkMethod.invoke(genderCreator);
        Assertions.assertEquals(expected1, outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}