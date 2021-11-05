package taa.assessment;

import org.junit.jupiter.api.Test;
import taa.Parser;
import taa.Ui;
import taa.teachingclass.ClassList;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssessmentListTest {

    @Test
    void addAssessment_newAssessmentArguments_assessmentAddedCorrectly() {
        AssessmentList assessments = new AssessmentList();
        assessments.addAssessment(new Assessment("Midterms", 50, 20));
        assertEquals("Midterms", assessments.getAssessment("Midterms").getName());
        assertEquals(50, assessments.getAssessment("Midterms").getMaximumMarks());
        assertEquals(20, assessments.getAssessment("Midterms").getWeightage());
    }

    @Test
    void addAssessment_userInput_assessmentAddedCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t_f12 n/software engineering (class-f12)";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2113t_f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        assertEquals("Midterms", classList.getClassWithId("cs2113t_f12")
                .getAssessmentList().getAssessment("Midterms").getName());
        assertEquals(50, classList.getClassWithId("cs2113t_f12")
                .getAssessmentList().getAssessment("Midterms").getMaximumMarks());
        assertEquals(20, classList.getClassWithId("cs2113t_f12")
                .getAssessmentList().getAssessment("Midterms").getWeightage());
    }

    @Test
    void deleteAssessment_deleteAssessmentArguments_assessmentDeletedCorrectly() throws TaaException {
        AssessmentList assessments = new AssessmentList();
        assessments.addAssessment(new Assessment("Midterms", 50, 20));
        Assessment assessment = assessments.deleteAssessment("Midterms");

        assertEquals("Midterms", assessment.getName());
        assertEquals(50, assessment.getMaximumMarks());
        assertEquals(20, assessment.getWeightage());
    }

    @Test
    void deleteAssessment_userInput_emptyAssessmentList() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t_f12 n/software engineering (F_12)";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2113t_f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String deleteAssessmentInput = "delete_assessment c/cs2113t_f12 a/Midterms";
        Command command3 = Parser.parseUserInput(deleteAssessmentInput);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);

        assertEquals(
                new AssessmentList().getSize(),
                classList.getClassWithId("cs2113t_f12").getAssessmentList().getSize()
        );
    }

    @Test
    void addAssessment_userInputRepeatedAssessmentName_nameExistsErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        try {
            String addAssessment2 = "add_assessment c/cs2113t-f12 n/midterms m/50 w/20";
            Command command7 = Parser.parseUserInput(addAssessment2);
            command7.parseArgument();
            command7.checkArgument();
            command7.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid assessment name. Assessment already exists.", e.getMessage());
        }
    }

    @Test
    void addAssessment_userInputNegativeWeightage_WeightageErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        try {
            String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/-0.001";
            Command command2 = Parser.parseUserInput(addAssessment1);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid weightage. Weightage must be between 0.00 (inclusive) and 100.00 (inclusive)", e.getMessage());
        }
    }

    @Test
    void addAssessment_userInputNegativeMaximumMarks_MaximumMarksErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/10 w/20";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        try {
            String addAssessment2 = "add_assessment c/cs2113t-f12 n/Finals m/50 w/80.001";
            Command command7 = Parser.parseUserInput(addAssessment2);
            command7.parseArgument();
            command7.checkArgument();
            command7.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid weightage. Total new weightage exceeds 100%.", e.getMessage());
        }
    }

    @Test
    void addAssessment_userInputOverflowWeightage_weightageErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        try {
            String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/-1 w/10";
            Command command2 = Parser.parseUserInput(addAssessment1);
            command2.parseArgument();
            command2.checkArgument();
            command2.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid maximum marks. Maximum marks must be larger than 0 (inclusive)", e.getMessage());
        }
    }

    @Test
    void editAssessment_userInputFourEdits_assessmentEditedCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String addAssessment3 = "add_assessment c/cs2113t-f12 n/PE m/50 w/20";
        Command command8 = Parser.parseUserInput(addAssessment3);
        command8.parseArgument();
        command8.checkArgument();
        command8.execute(classList, ui, storage);

        String editAssessment1 = "edit_assessment c/cs2113t-f12 a/midterms n/finals";
        Command command3 = Parser.parseUserInput(editAssessment1);
        command3.parseArgument();
        command3.checkArgument();
        command3.execute(classList, ui, storage);
        Assessment assessment = classList.getClassWithId("cs2113t-f12").getAssessmentList()
            .getAssessment("finals");
        assertEquals("finals", assessment.getName());

        String editAssessment2 = "edit_assessment c/cs2113t-f12 a/finals m/100";
        Command command4 = Parser.parseUserInput(editAssessment2);
        command4.parseArgument();
        command4.checkArgument();
        command4.execute(classList, ui, storage);
        assertEquals(100, assessment.getMaximumMarks());

        String editAssessment3 = "edit_assessment c/cs2113t-f12 a/finals w/50";
        Command command5 = Parser.parseUserInput(editAssessment3);
        command5.parseArgument();
        command5.checkArgument();
        command5.execute(classList, ui, storage);
        assertEquals(50, assessment.getWeightage());

        String editAssessment4 = "edit_assessment c/cs2113t-f12 a/finals n/midterms m/50 w/20";
        Command command6 = Parser.parseUserInput(editAssessment4);
        command6.parseArgument();
        command6.checkArgument();
        command6.execute(classList, ui, storage);

        assertEquals("midterms", assessment.getName());
        assertEquals(50, assessment.getMaximumMarks());
        assertEquals(20, assessment.getWeightage());
    }

    @Test
    void editAssessment_userInputNegativeWeightage_weightageErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        try {
            String editAssessment1 = "edit_assessment c/cs2113t-f12 a/midterms w/-0.001";
            Command command3 = Parser.parseUserInput(editAssessment1);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid new weightage. Weightage must be between 0.00 (inclusive) and 100.00 (inclusive)", e.getMessage());
        }
    }

    @Test
    void editAssessment_userInputOverflowWeightage_weightageErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String addAssessment3 = "add_assessment c/cs2113t-f12 n/PE m/50 w/20";
        Command command8 = Parser.parseUserInput(addAssessment3);
        command8.parseArgument();
        command8.checkArgument();
        command8.execute(classList, ui, storage);

        try {
            String editAssessment1 = "edit_assessment c/cs2113t-f12 a/midterms w/80.001";
            Command command3 = Parser.parseUserInput(editAssessment1);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid new weightage. Total new weightage exceeds 100%.", e.getMessage());
        }
    }

    @Test
    void editAssessment_userInputNegativeMaximumMarks_MaximumMarksErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        try {
            String editAssessment1 = "edit_assessment c/cs2113t-f12 a/midterms m/-1";
            Command command3 = Parser.parseUserInput(editAssessment1);
            command3.parseArgument();
            command3.checkArgument();
            command3.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid new maximum marks. Maximum marks must be larger than 0 (inclusive)", e.getMessage());
        }
    }

    @Test
    void editAssessment_userInputRepeatedAssessmentName_nameExistsErrorThrown() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        String addAssessment3 = "add_assessment c/cs2113t-f12 n/PE m/50 w/20";
        Command command8 = Parser.parseUserInput(addAssessment3);
        command8.parseArgument();
        command8.checkArgument();
        command8.execute(classList, ui, storage);

        try {
            String editAssessment5 = "edit_assessment c/cs2113t-f12 a/PE n/FINALS";
            Command command9 = Parser.parseUserInput(editAssessment5);
            command9.parseArgument();
            command9.checkArgument();
            command9.execute(classList, ui, storage);
        } catch (Exception e) {
            assertEquals("Invalid new name. An assessment with the same name already exists.",
                    e.getMessage());
        }
    }

    @Test
    void addAssessment_userInputMaximumMarksAndWeightageCornerCase_validWeightage() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String addAssessment1 = "add_assessment c/cs2113t-f12 n/Midterms m/0 w/0";
        Command command2 = Parser.parseUserInput(addAssessment1);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

        Assessment assessment = classList.getClassWithId("cs2113t-f12").getAssessmentList()
                .getAssessment("midterms");
        assertEquals(0, assessment.getMaximumMarks());
        assertEquals(0, assessment.getWeightage());
    }
}
