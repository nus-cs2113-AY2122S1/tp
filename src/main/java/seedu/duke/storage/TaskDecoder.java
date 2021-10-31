package seedu.duke.storage;

import seedu.duke.exceptions.DukeException;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.MemberParser;
import seedu.duke.parser.Parser;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskDecoder {

    // Index 0 is reserved for indicator for event/task
    private static final int INDEX_OF_TILE = 1;
    private static final int INDEX_OF_STATUS = 2;
    private static final int INDEX_OF_DESCRIPTION = 3;
    private static final int INDEX_OF_DEADLINE = 4;
    private static final int INDEX_OF_MEMBERS = 5;

    protected static Task decodeTaskFromString(String encodedTask) throws DukeException {
        String[] taskDetails = encodedTask.trim().split(Task.TASK_DATA_ARGS_DELIMITER);
        String taskTitle = taskDetails[INDEX_OF_TILE];
        String taskStatus = taskDetails[INDEX_OF_STATUS];
        String taskDescription = taskDetails[INDEX_OF_DESCRIPTION];
        LocalDateTime taskDeadline = Parser.convertDateTime(taskDetails[INDEX_OF_DEADLINE]);
        ArrayList<Member> membersList = decodeAssignedMembers(taskDetails[INDEX_OF_MEMBERS]);

        Task task = new Task(taskTitle, taskDescription, taskDeadline, membersList);
        if (taskStatus.equals("X")) {
            task.markAsDone();
        }
        return task;
    }

    private static ArrayList<Member> decodeAssignedMembers(String encodedMembers) {
        String[] formattedMemberNamesInString = MemberParser.parseMemberCommand(encodedMembers);
        ArrayList<Member> membersList = new ArrayList<>();
        for (String name : formattedMemberNamesInString) {
            membersList.add(new Member(name));
        }

        return membersList;
    }
}
