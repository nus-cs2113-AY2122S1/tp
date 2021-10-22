package medbot;

import medbot.command.Command;
import medbot.command.ExitCommand;
import medbot.command.HelpInfoCommand;
import medbot.command.SwitchCommand;
import medbot.command.personcommand.patientcommand.AddPatientCommand;
import medbot.command.personcommand.patientcommand.DeletePatientCommand;
import medbot.command.personcommand.patientcommand.EditPatientCommand;
import medbot.command.personcommand.patientcommand.FindPatientCommand;
import medbot.command.personcommand.patientcommand.ListPatientCommand;
import medbot.command.personcommand.patientcommand.ViewPatientCommand;
import medbot.exceptions.MedBotParserException;
import medbot.parser.Parser;
import medbot.parser.ParserUtils;
import medbot.person.Patient;
import medbot.person.Person;
import medbot.utilities.ViewType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


class ParserTest {
    private static final String END_LINE = System.lineSeparator();

    @Test
    public void testParseEmailAddress() throws Exception {
        String[][] testEmails = {
                {"johntan@example.com", "johntan@example.com"},
                {"johntan@example.com.sg", "johntan@example.com.sg"},
                {"johntan2@example.com", "johntan2@example.com"},
                {"john_tan@example.com", "john_tan@example.com"},
                {"john.tan@example.com", "john.tan@example.com"},
                {" johntan@example.com ", "johntan@example.com"},
                {"   ", "Email address not specified." + END_LINE},
                {"johntan_@example.com", "Incorrect email address format." + END_LINE},
                {"_johntan@example.com", "Incorrect email address format." + END_LINE},
                {"johntan@example", "Incorrect email address format." + END_LINE}
        };

        Method method = ParserUtils.class.getDeclaredMethod("parseEmailAddress", String.class);
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
                {"8123456", "Phone number has too few digits." + END_LINE},
                {"812345678", "Phone number has too many digits." + END_LINE},
                {"   ", "Phone number not specified." + END_LINE},
                {"8123456A", "Phone number contains unexpected characters." + END_LINE},
        };

        Method method = ParserUtils.class.getDeclaredMethod("parsePhoneNumber", String.class);
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
        Method method = ParserUtils.class.getDeclaredMethod("parseName", String.class);
        method.setAccessible(true);
        String[][] testNames = {
                {"John Tan", "John Tan"},
                {"john tan", "John Tan"},
                {"john-tan", "John-Tan"},
                {"JOHN TAN", "John Tan"},
                {"   ", "Name not specified." + END_LINE}
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
        Method method = ParserUtils.class.getDeclaredMethod("parseIcNumber", String.class);
        method.setAccessible(true);
        String[][] testIcNumbers = {
                {"S1234567A", "S1234567A"},
                {"F1234567A", "F1234567A"},
                {"Z1234567A", "Incorrect IC number format." + END_LINE},
                {"F1234567", "Incorrect IC number format." + END_LINE},
                {"F1234A", "Incorrect IC number format." + END_LINE},
                {"   ", "IC number not specified." + END_LINE},
                {"1234567A", "Incorrect IC number format." + END_LINE}
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
        Method method = ParserUtils.class.getDeclaredMethod("parseResidentialAddress", String.class);
        method.setAccessible(true);
        String[][] testResidentialAddresses = {
                {"12 Lower Kent Ridge", "12 Lower Kent Ridge"},
                {"12 lower kent ridge", "12 Lower Kent Ridge"},
                {"12 LOWER KENT RIDGE", "12 Lower Kent Ridge"},
                {"   ", "Address not specified." + END_LINE}
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
        Method method = ParserUtils.class.getDeclaredMethod("preprocessMultiAttributeInput", String.class);
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
        Method method = ParserUtils.class.getDeclaredMethod("updateMultiplePersonalInformation", Person.class,
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

    @Test
    void testPreprocessInput() throws Exception {
        Method method = ParserUtils.class.getDeclaredMethod("preprocessInput", String.class);
        method.setAccessible(true);
        String[][] testInputStrings = {
                {"  add n/John Tan   ", "add n/John Tan"},
                {"add n/John Tan", "add n/John Tan"},
                {"add n/Tim| lee ", "add n/Tim lee"},
                {"add i/S8712345G||", "add i/S8712345G"}
        };
        for (String[] testInputString : testInputStrings) {
            assertEquals(testInputString[1], method.invoke(method, testInputString[0]));
        }
    }

    @Test
    void testParseId() throws Exception {
        Method method = ParserUtils.class.getDeclaredMethod("parseId", String.class);
        method.setAccessible(true);
        HashMap<String, Integer> testCases = new HashMap<>();
        testCases.put("5", 5);
        testCases.put("  5   ", 5);
        testCases.put("35", 35);
        for (String key : testCases.keySet()) {
            assertEquals(testCases.get(key), method.invoke(method, key));
        }

        String[][] testInputExceptions = {
                {"     ", "ID not specified or not a number." + END_LINE},
                {"hi", "ID not specified or not a number." + END_LINE},
                {"13hi ", "ID not specified or not a number." + END_LINE},
                {"hi13", "ID not specified or not a number." + END_LINE}
        };
        for (String[] testInputException : testInputExceptions) {
            try {
                assertEquals(testInputException[1], method.invoke(method, testInputException[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testInputException[1], e.getTargetException().getMessage());
            }

        }
    }

    @Test
    void testGetParameters() throws Exception {
        Method method = ParserUtils.class.getDeclaredMethod("getParameters", String.class);
        method.setAccessible(true);
        String[] resultParameters = {"n/John Tan ", "i/S8712345G ", "e/john_tan@gmail.com ", "p/8123 4567"};
        String[] inputCommands = {
            "add n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567",
            "edit n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567",
            "add         n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567",
            "edit          n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567"
        };

        for (String inputCommand : inputCommands) {
            assertArrayEquals(resultParameters, (Object[]) method.invoke(method, inputCommand));
        }

        String[][] testInputExceptions = {
                {"add    ", "No parameters given" + END_LINE},
                {"edit    ", "No parameters given" + END_LINE},
                {"add John  john@email", "No parameters given" + END_LINE},
                {"edit John john@email", "No parameters given" + END_LINE}
        };
        for (String[] testInputException : testInputExceptions) {
            try {
                assertEquals(testInputException[1], method.invoke(method, testInputException[0]));
            } catch (InvocationTargetException e) {
                assertEquals(testInputException[1], e.getTargetException().getMessage());
            }

        }
    }


    @Test
    void testParseCommand() {
        //This test only check the type, not the value of the command
        Parser.setViewType(ViewType.PATIENT_INFO);
        assertSame(Parser.getViewType(), ViewType.PATIENT_INFO);
        HashMap<String, Command> testCases = new HashMap<>();
        testCases.put("exit", new ExitCommand());
        testCases.put("help ", new HelpInfoCommand());
        testCases.put("switch", new SwitchCommand(null));
        testCases.put("add n/John Tan ", new AddPatientCommand(null));
        testCases.put("edit 1 n/John Tan ", new EditPatientCommand(1, null));
        testCases.put("delete 1", new DeletePatientCommand(1));
        testCases.put("find n/name", new FindPatientCommand(new String[]{"name"}));
        testCases.put("view 1", new ViewPatientCommand(1));
        testCases.put("list", new ListPatientCommand());
        testCases.put(" hello", null);

        for (String testCase : testCases.keySet()) {
            try {
                Command command = Parser.parseCommand(testCase);
                assertTrue(testCases.get(testCase).getClass().isAssignableFrom(command.getClass()));
            } catch (MedBotParserException e) {
                assertEquals("Unable to parse command." + END_LINE, e.getMessage());
            }
        }
    }

}