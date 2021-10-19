package medbot;

import medbot.person.Patient;
import medbot.person.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    private static final String ENDLINE = System.lineSeparator();

    @Test
    public void testParseEmailAddress() throws Exception {
        String[][] testEmails = {
                {"johntan@example.com", "johntan@example.com"},
                {"johntan@example.com.sg", "johntan@example.com.sg"},
                {"johntan2@example.com", "johntan2@example.com"},
                {"john_tan@example.com", "john_tan@example.com"},
                {"john.tan@example.com", "john.tan@example.com"},
                {" johntan@example.com ", "johntan@example.com"},
                {"   ", "Email address not specified" + ENDLINE},
                {"johntan_@example.com", "Incorrect email address format" + ENDLINE},
                {"_johntan@example.com", "Incorrect email address format" + ENDLINE},
                {"johntan@example", "Incorrect email address format" + ENDLINE}
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
                {"8123456", "Phone number has too few digits" + ENDLINE},
                {"812345678", "Phone number has too many digits" + ENDLINE},
                {"   ", "Phone number not specified" + ENDLINE},
                {"8123456A", "Phone number contains unexpected characters" + ENDLINE},
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
                {"   ", "Name not specified" + ENDLINE}
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
                {"Z1234567A", "Incorrect IC number format" + ENDLINE},
                {"F1234567", "Incorrect IC number format" + ENDLINE},
                {"F1234A", "Incorrect IC number format" + ENDLINE},
                {"   ", "IC number not specified" + ENDLINE},
                {"1234567A", "Incorrect IC number format" + ENDLINE}
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
                {"   ", "Address not specified" + ENDLINE}
        };
        for (String[] testResidentialAddress : testResidentialAddresses) {
            try {
                assertEquals(testResidentialAddress[1], method.invoke(method, testResidentialAddress[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testResidentialAddress[1], e.getTargetException().getMessage());
            }
        }
    }

    @Test
    void testPreprocessMultiAttributeInput() throws Exception {
        Method method = Parser.class.getDeclaredMethod("preprocessMultiAttributeInput", String.class);
        method.setAccessible(true);
        String[][] testInputStrings = {
                {"add n/John Tan i/S8712345G e/john@gmail.com p/8123 4567 a/123 bishan st 24 #05-19",
                "add |n/John Tan |i/S8712345G |e/john@gmail.com |p/8123 4567 |a/123 bishan st 24 #05-19"},
                {"add n/joe ong e/joe@gmail.com a/123 Bishan st 24 #05-19 ",
                "add |n/joe ong |e/joe@gmail.com |a/123 Bishan st 24 #05-19 "},
                {"add   n/Tim lee   e/tim_lee@gmail.com.sg   a/123 queenstown ave 6 #05-19 ",
                "add   |n/Tim lee   |e/tim_lee@gmail.com.sg   |a/123 queenstown ave 6 #05-19 "},
                {"add i/S8712345G ",
                "add |i/S8712345G "}
        };
        for (String[] testInputString : testInputStrings) {
            assertEquals(testInputString[1], method.invoke(method, testInputString[0]));
        }
    }

    @Test
    void testUpdateMultiplePersonalInformation() throws Exception {
        Method method = Parser.class.getDeclaredMethod("updateMultiplePersonalInformation", Person.class,
                String[].class);
        method.setAccessible(true);
        Patient patient = new Patient();
        String[] attributeStrings = {"n/John tan  ", "i/S8712345G  ", "e/john_tan@gmail.com ", "p/8123 4567",
                                     "a/123 bishan st 24 #05-19  "};
        method.invoke(method, patient, attributeStrings);
        assertEquals(patient.getName(), "John Tan");
        assertEquals(patient.getIcNumber(), "S8712345G");
        assertEquals(patient.getEmailAddress(), "john_tan@gmail.com");
        assertEquals(patient.getPhoneNumber(), "81234567");
        assertEquals(patient.getResidentialAddress(), "123 Bishan St 24 #05-19");
    }
}