package medbot;


import medbot.command.Command;
import medbot.command.ExitCommand;
import medbot.command.HelpCommand;
import medbot.command.SwitchCommand;
import medbot.command.appointmentcommand.AddAppointmentCommand;
import medbot.command.appointmentcommand.DeleteAppointmentCommand;
import medbot.command.appointmentcommand.EditAppointmentCommand;
import medbot.command.appointmentcommand.ListAppointmentCommand;
import medbot.command.personcommand.patientcommand.AddPatientCommand;
import medbot.command.personcommand.patientcommand.DeletePatientCommand;
import medbot.command.personcommand.patientcommand.EditPatientCommand;
import medbot.command.personcommand.patientcommand.FindPatientCommand;
import medbot.command.personcommand.patientcommand.ListPatientCommand;
import medbot.command.personcommand.patientcommand.ViewPatientCommand;
import medbot.command.personcommand.patientcommand.ArchivePatientCommand;
import medbot.command.personcommand.patientcommand.UnarchivePatientCommand;
import medbot.command.personcommand.staffcommand.AddStaffCommand;
import medbot.command.personcommand.staffcommand.DeleteStaffCommand;
import medbot.command.personcommand.staffcommand.EditStaffCommand;
import medbot.command.personcommand.staffcommand.FindStaffCommand;
import medbot.command.personcommand.staffcommand.ListStaffCommand;
import medbot.command.personcommand.staffcommand.ViewStaffCommand;
import medbot.command.personcommand.staffcommand.ArchiveStaffCommand;
import medbot.command.personcommand.staffcommand.UnarchiveStaffCommand;

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

import static org.junit.jupiter.api.Assertions.*;


class ParserTest {
    private static final String END_LINE = System.lineSeparator();

    @Test
    public void testParseEmailAddress_correctFormat() {
        try {
            assertEquals("johntan@example.com",
                    ParserUtils.parseEmailAddress("johntan@example.com"));
            assertEquals("johntan@example.com.sg",
                    ParserUtils.parseEmailAddress("johntan@example.com.sg"));
            assertEquals("john.tan_2@example.com",
                    ParserUtils.parseEmailAddress("john.tan_2@example.com"));
            assertEquals("johntan@example.com",
                    ParserUtils.parseEmailAddress(" johntan@example.com "));
        } catch (MedBotParserException me) {
            fail();
        }
    }

    @Test
    public void testParseEmailAddress_exceptionThrown() {
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseEmailAddress("   "),
                "Email address not specified.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseEmailAddress("johntan_@example.com"),
                "Incorrect email address format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseEmailAddress("_johntan@example.com"),
                "Incorrect email address format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseEmailAddress("johntan@example"),
                "Incorrect email address format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseEmailAddress("johntanexample,com"),
                "Incorrect email address format.");
    }


    @Test
    public void testParsePhoneNumber_correctFormat() {
        try {
            assertEquals("81234567",
                    ParserUtils.parsePhoneNumber("81234567"));
            assertEquals("81234567",
                    ParserUtils.parsePhoneNumber("  81234567"));
            assertEquals("81234567",
                    ParserUtils.parsePhoneNumber("8123   4567"));
            assertEquals("81234567",
                    ParserUtils.parsePhoneNumber("8123_4567"));
        } catch (MedBotParserException me) {
            fail();
        }
    }

    @Test
    public void testParsePhoneNumber_exceptionThrown() {
        assertThrows(MedBotParserException.class, () -> ParserUtils.parsePhoneNumber("8123456"),
                "Phone number has too few digits.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parsePhoneNumber("812345678"),
                "Phone number has too many digits.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parsePhoneNumber("   "),
                "Phone number not specified.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parsePhoneNumber("8123456A"),
                "Phone number contains unexpected characters.");
    }


    @Test
    public void testParseName_validFormat() {
        try {
            assertEquals("John Tan", ParserUtils.parseName("John Tan"));
            assertEquals("John Tan", ParserUtils.parseName("john tan"));
            assertEquals("John Tan", ParserUtils.parseName("JOHN TAN"));
            assertEquals("John-Tan", ParserUtils.parseName("john-tan"));
            assertEquals("John+tan", ParserUtils.parseName("john+tan"));
        } catch (MedBotParserException me) {
            fail();
        }
    }

    @Test
    public void testParseName_exceptionThrown() {
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseName("  "), "Name not specified.");
    }

    @Test
    public void testParseIcNumber_validFormat() {
        try {
            assertEquals("S1234567A", ParserUtils.parseIcNumber("S1234567A"));
            assertEquals("F1234567A", ParserUtils.parseIcNumber("f1234567a"));
        } catch (MedBotParserException me) {
            fail();
        }
    }

    @Test
    public void testParseIcNumber_exceptionThrown() {
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseIcNumber("Z1234567A"),
                "Incorrect IC number format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseIcNumber("F1234567"),
                "Incorrect IC number format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseIcNumber("1234567A"),
                "Incorrect IC number format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseIcNumber("S1234 567A"),
                "Incorrect IC number format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseIcNumber("1234567A"),
                "Incorrect IC number format.");
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseIcNumber("  "),
                "IC number not specified.");
    }

    @Test
    void testParseResidentialAddress_correctFormat() {
        try {
            assertEquals("12 Lower Kent Ridge", ParserUtils.parseResidentialAddress("12 Lower Kent Ridge"));
            assertEquals("12 Lower Kent Ridge", ParserUtils.parseResidentialAddress("12 lower kent ridge"));
            assertEquals("12 Lower Kent Ridge", ParserUtils.parseResidentialAddress("12 LOWER KENT RIDGE"));
        } catch (MedBotParserException me) {
            fail();
        }
    }

    @Test
    public void testParseResidentialAddress_exceptionThrown() {
        assertThrows(MedBotParserException.class, () -> ParserUtils.parseName("  "), "Address not specified.");
    }

    @Test
    void testPreprocessMultiAttributeInput() throws Exception {
        Method method = ParserUtils.class.getDeclaredMethod("preprocessMultiAttributeInput", String.class);
        method.setAccessible(true);

        String testInput0 = "add n/John Tan i/S8712345G e/john@gmail.com p/8123 4567 a/123 bishan st 24 #05-19";
        String testOutput0 = "add |n/John Tan |i/S8712345G |e/john@gmail.com |p/8123 4567 |a/123 bishan st 24 #05-19";
        assertEquals(testOutput0, method.invoke(method, testInput0));

        String testInput1 = "add n/joe ong e/joe@gmail.com a/123 Bishan st 24 #05-19 ";
        String testOutput1 = "add |n/joe ong |e/joe@gmail.com |a/123 Bishan st 24 #05-19 ";
        assertEquals(testOutput1, method.invoke(method, testInput1));

        String testInput2 = "add   n/Tim lee   e/tim_lee@gmail.com.sg   a/123 queenstown ave 6 #05-19 ";
        String testOutput2 = "add   |n/Tim lee   |e/tim_lee@gmail.com.sg   |a/123 queenstown ave 6 #05-19 ";
        assertEquals(testOutput2, method.invoke(method, testInput2));

        String testInput3 = "addn/Tim leee/tim_lee@gmail.com.sga/123 queenstown ave 6 #05-19 ";
        String testOutput3 = "add|n/Tim lee|e/tim_lee@gmail.com.sg|a/123 queenstown ave 6 #05-19 ";
        assertEquals(testOutput3, method.invoke(method, testInput3));
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
        assertEquals("add n/John Tan", method.invoke(method, "  add n/John Tan   "));
        assertEquals("add n/John Tan", method.invoke(method, "add n/John Tan"));
        assertEquals("add n/Tim lee", method.invoke(method, "add n/Tim| lee "));
        assertEquals("add i/S8712345G", method.invoke(method, "add i/S8712345G||"));
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
                {"     ", "ID not specified or not a number."},
                {"hi", "ID not specified or not a number."},
                {"13hi ", "ID not specified or not a number."},
                {"hi13", "ID not specified or not a number."}
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
        String[] inputCommands = {"add n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567",
                                  "edit n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567",
                                  "add         n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567",
                                  "edit          n/John Tan i/S8712345G e/john_tan@gmail.com p/8123 4567"};

        for (String inputCommand : inputCommands) {
            assertArrayEquals(resultParameters, (Object[]) method.invoke(method, inputCommand));
        }

        String[][] testInputExceptions = {
                {"add    ", "No parameters given"},
                {"edit    ", "No parameters given"},
                {"add John  john@email", "No parameters given"},
                {"edit John john@email", "No parameters given"}
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
    void testParseCommandForPatient() {
        //This test only check the type, not the value of the command
        Parser.setViewType(ViewType.PATIENT_INFO);
        assertSame(Parser.getViewType(), ViewType.PATIENT_INFO);
        HashMap<String, Command> testCases = new HashMap<>();
        testCases.put("exit", new ExitCommand());
        testCases.put("help ", new HelpCommand(ViewType.PATIENT_INFO));
        testCases.put("switch 2", new SwitchCommand(null));
        testCases.put("add n/John Tan ", new AddPatientCommand(null));
        testCases.put("edit 1 n/John Tan ", new EditPatientCommand(1, null));
        testCases.put("delete 1", new DeletePatientCommand(1));
        testCases.put("find n/name", new FindPatientCommand(new String[]{"name"}));
        testCases.put("view 1", new ViewPatientCommand(1));
        testCases.put("list", new ListPatientCommand(false));
        testCases.put("archive 1", new ArchivePatientCommand(1));
        testCases.put("unarchive 1", new UnarchivePatientCommand(1));
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

    @Test
    void testParseCommandForStaff() {
        //This test only check the type, not the value of the command
        Parser.setViewType(ViewType.MEDICAL_STAFF_INFO);
        assertSame(Parser.getViewType(), ViewType.MEDICAL_STAFF_INFO);
        HashMap<String, Command> testCases = new HashMap<>();
        testCases.put("exit", new ExitCommand());
        testCases.put("help ", new HelpCommand(ViewType.MEDICAL_STAFF_INFO));
        testCases.put("switch 3", new SwitchCommand(null));
        testCases.put("add n/John Tan ", new AddStaffCommand(null));
        testCases.put("edit 1 n/John Tan ", new EditStaffCommand(1, null));
        testCases.put("delete 1", new DeleteStaffCommand(1));
        testCases.put("find n/name", new FindStaffCommand(new String[]{"name"}));
        testCases.put("view 1", new ViewStaffCommand(1));
        testCases.put("list", new ListStaffCommand(false));
        testCases.put("archive 1", new ArchiveStaffCommand(1));
        testCases.put("unarchive 1", new UnarchiveStaffCommand(1));
        testCases.put(" hello", null);

        for (String testCase : testCases.keySet()) {
            try {
                Command command = Parser.parseCommand(testCase);
                assertTrue(testCases.get(testCase).getClass().isAssignableFrom(command.getClass()));
            } catch (MedBotParserException e) {
                assertEquals("Unable to parse command." + END_LINE, e.getMessage());
            }
        }
        //Change View type back to Patient
        Parser.setViewType(ViewType.PATIENT_INFO);
    }

    @Test
    void testParseCommandForAppointment() {
        //This test only check the type, not the value of the command
        Parser.setViewType(ViewType.SCHEDULER);
        assertSame(Parser.getViewType(), ViewType.SCHEDULER);
        HashMap<String, Command> testCases = new HashMap<>();
        testCases.put("exit", new ExitCommand());
        testCases.put("help ", new HelpCommand(ViewType.SCHEDULER));
        testCases.put("switch 1", new SwitchCommand(null));
        testCases.put("add p/1 s/1 d/181021 1800 ", new AddAppointmentCommand(null));
        testCases.put("add 1 p/1 s/1 d/181021 1800 ", new AddAppointmentCommand(null));
        testCases.put("edit 1 p/1 s/1 d/181021 1800 ", new EditAppointmentCommand(1, null));
        testCases.put("delete 1", new DeleteAppointmentCommand(1));
        testCases.put("list", new ListAppointmentCommand());
        testCases.put(" hello", null);

        for (String testCase : testCases.keySet()) {
            try {
                Command command = Parser.parseCommand(testCase);
                assertTrue(testCases.get(testCase).getClass().isAssignableFrom(command.getClass()));
            } catch (MedBotParserException e) {
                assertEquals("Unable to parse command." + END_LINE, e.getMessage());
            }
        }
        //Change View type back to Patient
        Parser.setViewType(ViewType.PATIENT_INFO);
    }

}