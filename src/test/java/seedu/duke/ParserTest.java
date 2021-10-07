package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.member.Member;
import seedu.duke.training.TrainingSchedule;

class ParserTest {

    @Test
    void getMemberDetails() {
        final String string = "add /m Tan Teck Hwee /s A0123456A /g F /p 98765432";
        Member member = Parser.getMemberDetails(string);
        assertEquals("Name: Tan Teck Hwee | Student Number: A0123456A | Gender: F | Phone Number: 98765432",
                member.toString());
    }

    @Test
    void getTrainingDetails() {
        final String input = "add /t /n October Friday Weekly Training 1 /a 1 Oct 2021 /v MPSH 1";
        TrainingSchedule training = Parser.getTrainingDescription(input);
        assertEquals("Training Name: October Friday Weekly Training 1 | Venue: MPSH 1 | Time: 1 Oct 2021",
                training.toString());
    }
}