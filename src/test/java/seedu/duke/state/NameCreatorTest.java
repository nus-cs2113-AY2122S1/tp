package seedu.duke.state;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.Ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



class NameCreatorTest {
    NameCreator nameCreator = new NameCreator(new Ui());

    @Test
    void setName_emptyInput_throwsException()
            throws NoSuchMethodException {
        Method method = nameCreator.getClass().getDeclaredMethod("setName",String.class);
        method.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(nameCreator,""));
    }

    @Test
    void checkName_nameInstance_validMessage()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = nameCreator.getClass().getDeclaredMethod("setName",String.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(nameCreator,"bywere");
        Assertions.assertEquals(result, false);
    }
}