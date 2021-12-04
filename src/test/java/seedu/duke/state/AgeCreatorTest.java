package seedu.duke.state;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import  org.junit.jupiter.api.Assertions;

class AgeCreatorTest {
    AgeCreator ageCreator = new AgeCreator(new Ui());

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void setAge_oneInvalidInput_throwsException()
            throws NoSuchMethodException {
        Method method = ageCreator.getClass().getDeclaredMethod("setAge",String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(ageCreator,"adg"));
    }

    @Test
    void setAge_emptyInput_throwsException()
            throws NoSuchMethodException {
        Method method = ageCreator.getClass().getDeclaredMethod("setAge",String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(ageCreator,""));
    }

    @Test
    void setAge_spacesInput_throwsException() throws NoSuchMethodException {
        Method method = ageCreator.getClass().getDeclaredMethod("setAge",String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(ageCreator,"    "));
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(ageCreator," " + ls));
    }

    @Test
    void checkAge_ageInstance_validMessage()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ageCreator.getClass().getDeclaredMethod("setAge",String.class);
        Method checkMethod = ageCreator.getClass().getDeclaredMethod("checkAge");
        method.setAccessible(true);
        method.invoke(ageCreator,"1");
        checkMethod.setAccessible(true);
        checkMethod.invoke(ageCreator);
        String expected = "_________________________________________________________________"
                + "_________________________________________" + ls
                + "Your age cannot be this value!" + ls
                + "Maybe you can try a whole number from 10 to 150." + ls
                + "___________________________________________________________"
                + "_______________________________________________";
        Assertions.assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        String expected1 = expected + ls
                + "_________________________________________________"
                + "_________________________________________________________" + ls
                + "You are 20 years old.";
        method.invoke(ageCreator,"20");
        checkMethod.invoke(ageCreator);
        Assertions.assertEquals(expected1, outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}