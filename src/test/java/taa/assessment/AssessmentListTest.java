package taa.assessment;

import org.junit.jupiter.api.Test;
import taa.Parser;
import taa.Ui;
import taa.classmodel.ClassList;
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
        String moduleInput = "add_module c/cs2113t n/software engineering";
        String assessmentInput = "add_assessment c/cs2113t n/Midterms m/50 w/20";
        Command command1 = Parser.parseUserInput(moduleInput);
        command1.execute(classList, ui, storage);
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.execute(classList, ui, storage);
        assertEquals("Midterms", classList.getClassWithId("cs2113t")
                .getAssessmentList().getAssessment("Midterms").getName());
        assertEquals(50, classList.getClassWithId("cs2113t")
                .getAssessmentList().getAssessment("Midterms").getMaximumMarks());
        assertEquals(20, classList.getClassWithId("cs2113t")
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
        String moduleInput = "add_module c/cs2113t n/software engineering";
        String assessmentInput = "add_assessment c/cs2113t n/Midterms m/50 w/20";
        String deleteAssessmentInput = "delete_assessment c/cs2113t n/Midterms";
        Command command1 = Parser.parseUserInput(moduleInput);
        command1.execute(classList, ui, storage);
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.execute(classList, ui, storage);
        Command command3 = Parser.parseUserInput(deleteAssessmentInput);
        command3.execute(classList, ui, storage);
        assertEquals(
                new AssessmentList().getSize(),
                classList.getClassWithId("cs2113t").getAssessmentList().getSize()
        );
    }

    @Test
    void editAssessment_userInputFourEdits_assessmentEditedCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String moduleInput = "add_module c/cs2113t n/software engineering";
        String assessmentInput = "add_assessment c/cs2113t n/Midterms m/50 w/20";
        String editAssessment1 = "edit_assessment c/cs2113t n/midterms nn/finals";
        Command command1 = Parser.parseUserInput(moduleInput);
        command1.execute(classList, ui, storage);
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.execute(classList, ui, storage);
        Assessment assessment = classList.getClassWithId("cs2113t").getAssessmentList()
                .getAssessment("midterms");
        Command command3 = Parser.parseUserInput(editAssessment1);
        command3.execute(classList, ui, storage);
        assertEquals("finals", assessment.getName());
        String editAssessment2 = "edit_assessment c/cs2113t n/finals m/100";
        Command command4 = Parser.parseUserInput(editAssessment2);
        command4.execute(classList, ui, storage);
        assertEquals(100, assessment.getMaximumMarks());
        String editAssessment3 = "edit_assessment c/cs2113t n/finals w/50";
        Command command5 = Parser.parseUserInput(editAssessment3);
        command5.execute(classList, ui, storage);
        assertEquals(50, assessment.getWeightage());
        String editAssessment4 = "edit_assessment c/cs2113t n/finals nn/midterms m/50 w/20";
        Command command6 = Parser.parseUserInput(editAssessment4);
        command6.execute(classList, ui, storage);
        assertEquals("midterms", assessment.getName());
        assertEquals(50, assessment.getMaximumMarks());
        assertEquals(20, assessment.getWeightage());
    }
}
