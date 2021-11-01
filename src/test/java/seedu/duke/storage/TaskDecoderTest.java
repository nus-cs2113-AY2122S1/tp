package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskDecoderTest {

    @Test
    void decodeTaskFromString_stringValidFormat_expectOneTask() throws DukeException {
        // Setting up
        String encodedTask = "t | Do homework | X | CS2113T tp V1.0 | 19-02-2022 2359 | John_Doe Jane_Doe";
        Task decodedTask = TaskDecoder.decodeTaskFromString(encodedTask);

        String expectedTitle = "Do homework";
        assertEquals(expectedTitle, decodedTask.getTitle());
        Member expectedMember = new Member("John Doe");
        assertEquals(expectedMember.getName(), decodedTask.memberList.get(0).getName());
    }
}
