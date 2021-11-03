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
    void editAssessment_userInputFourEdits_assessmentEditedCorrectly() throws TaaException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taa_data.json");
        ClassList classList = new ClassList();
        String classInput = "add_class i/cs2113t-f12 n/software engineering class F12";
        Command command1 = Parser.parseUserInput(classInput);
        command1.parseArgument();
        command1.checkArgument();
        command1.execute(classList, ui, storage);

        String assessmentInput = "add_assessment c/cs2113t-f12 n/Midterms m/50 w/20";
        Command command2 = Parser.parseUserInput(assessmentInput);
        command2.parseArgument();
        command2.checkArgument();
        command2.execute(classList, ui, storage);

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
}
