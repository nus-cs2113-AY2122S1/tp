package medbot;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    public void testParseEmailAddress() throws Exception {
        String[][] testEmails = {
                {"johntan@example.com", "johntan@example.com"},
                {"johntan@example.com.sg", "johntan@example.com.sg"},
                {"johntan2@example.com", "johntan2@example.com"},
                {"john_tan@example.com", "john_tan@example.com"},
                {"john.tan@example.com", "john.tan@example.com"},
                {" johntan@example.com ", "johntan@example.com"},
                {"   ", "Email address not specified"},
                {"johntan_@example.com", "Incorrect email address format"},
                {"_johntan@example.com", "Incorrect email address format"},
                {"johntan@example", "Incorrect email address format"}
        };

        Method method = Parser.class.getDeclaredMethod("parseEmailAddress", String.class);
        method.setAccessible(true);

        for (String[] testEmail : testEmails) {
            try {
                assertEquals(testEmail[1], method.invoke(method, testEmail[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testEmail[1], e.getTargetException().getMessage());
            }
        }
    }

    @Test
    public void testParsePhoneNumber() throws Exception {
        String[][] testPhoneNumbers = {
                {"81234567", "81234567"},
                {"8123 4567", "81234567"},
                {"8123_4567", "81234567"},
                {"8123456", "Phone number has too few digits"},
                {"812345678", "Phone number has too many digits"},
                {"   ", "Phone number not specified"},
                {"8123456A", "Phone number contains unexpected characters"},
        };

        Method method = Parser.class.getDeclaredMethod("parsePhoneNumber", String.class);
        method.setAccessible(true);


        for (String[] testPhoneNumber : testPhoneNumbers) {
            try {
                assertEquals(testPhoneNumber[1], method.invoke(method, testPhoneNumber[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testPhoneNumber[1], e.getTargetException().getMessage());
            }
        }
    }

    @Test
    public void testParseName() throws Exception {
        Method method = Parser.class.getDeclaredMethod("parseName", String.class);
        method.setAccessible(true);
        String[][] testNames = {
                {"John Tan", "John Tan"},
                {"john tan", "John Tan"},
                {"john-tan", "John-Tan"},
                {"JOHN TAN", "John Tan"},
                {"   ", "Name not specified"}
        };

        for (String[] testName : testNames) {
            try {
                assertEquals(testName[1], method.invoke(method, testName[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testName[1], e.getTargetException().getMessage());
            }
        }
    }

    @Test
    public void testParseIcNumber() throws Exception {
        Method method = Parser.class.getDeclaredMethod("parseIcNumber", String.class);
        method.setAccessible(true);
        String[][] testIcNumbers = {
                {"S1234567A", "S1234567A"},
                {"F1234567A", "F1234567A"},
                {"Z1234567A", "Incorrect IC number format"},
                {"F1234567", "Incorrect IC number format"},
                {"F1234A", "Incorrect IC number format"},
                {"   ", "IC number not specified"},
                {"1234567A", "Incorrect IC number format"}
        };
        for (String[] testIcNumber : testIcNumbers) {
            try {
                assertEquals(testIcNumber[1], method.invoke(method, testIcNumber[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testIcNumber[1], e.getTargetException().getMessage());
            }
        }
    }

    @Test
    void testParseResidentialAddress() throws Exception {
        Method method = Parser.class.getDeclaredMethod("parseResidentialAddress", String.class);
        method.setAccessible(true);
        String[][] testResidentialAddresses = {
                {"12 Lower Kent Ridge", "12 Lower Kent Ridge"},
                {"12 lower kent ridge", "12 Lower Kent Ridge"},
                {"12 LOWER KENT RIDGE", "12 Lower Kent Ridge"},
                {"   ", "Address not specified"}
        };
        for (String[] testResidentialAddress : testResidentialAddresses) {
            try {
                assertEquals(testResidentialAddress[1], method.invoke(method, testResidentialAddress[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testResidentialAddress[1], e.getTargetException().getMessage());
            }
        }
    }
}