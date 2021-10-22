package taa.assessment;

import org.junit.jupiter.api.Test;
import taa.Parser;
import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.module.ModuleList;
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
        ModuleList moduleList = new ModuleList();
        String moduleInput = "add_module c/cs2113t n/software engineering";
        String assessmentInput = "add_assessment c/cs2113t n/Midterms m/50 w/20";
        Command command1 = Parser.parseUserInput(moduleInput);
        command1.execute(moduleList, ui, storage);
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.execute(moduleList, ui, storage);
        assertEquals("Midterms", moduleList.getModuleWithCode("cs2113t")
                .getAssessmentList().getAssessment("Midterms").getName());
        assertEquals(50, moduleList.getModuleWithCode("cs2113t")
                .getAssessmentList().getAssessment("Midterms").getMaximumMarks());
        assertEquals(20, moduleList.getModuleWithCode("cs2113t")
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
        ModuleList moduleList = new ModuleList();
        String moduleInput = "add_module c/cs2113t n/software engineering";
        String assessmentInput = "add_assessment c/cs2113t n/Midterms m/50 w/20";
        String deleteAssessmentInput = "delete_assessment c/cs2113t n/Midterms";
        Command command1 = Parser.parseUserInput(moduleInput);
        command1.execute(moduleList, ui, storage);
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.execute(moduleList, ui, storage);
        Command command3 = Parser.parseUserInput(deleteAssessmentInput);
        command3.execute(moduleList, ui, storage);
        assertEquals(
            new AssessmentList().getSize(),
            moduleList.getModuleWithCode("cs2113t").getAssessmentList().getSize()
        );
    }
}
