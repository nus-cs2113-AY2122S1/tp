package seedu.duke.storage;

import seedu.duke.Duke;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
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
    private static final int INDEX_OF_PARENT_EVENT_INDEX = 6;
    private static final int INDEX_OF_PARENT_EVENT_TITLE = 7;

    protected static Task decodeTaskFromString(String encodedTask) throws DukeException {
        String[] taskDetails = encodedTask.trim().split(Task.TASK_DATA_ARGS_DELIMITER);
        String taskTitle = taskDetails[INDEX_OF_TILE];
        String taskStatus = taskDetails[INDEX_OF_STATUS];
        String taskDescription = taskDetails[INDEX_OF_DESCRIPTION];
        LocalDateTime taskDeadline = Parser.convertDateTimeForLoading(taskDetails[INDEX_OF_DEADLINE]);
        ArrayList<Member> membersList = decodeAssignedMembers(taskDetails[INDEX_OF_MEMBERS]);

        String titleOfParentEvent = taskDetails[INDEX_OF_PARENT_EVENT_TITLE];
        int indexOfParentEvent = Integer.parseInt(taskDetails[INDEX_OF_PARENT_EVENT_INDEX]);
        boolean isParentEventVerified = verifyParentEvent(titleOfParentEvent, indexOfParentEvent);

        if (!isParentEventVerified) {
            throw new DukeException("Associated event for the task is not verified");
        }

        Event parentEvent = Duke.eventCatalog.get(indexOfParentEvent);
        Task task = new Task(taskTitle, taskDescription, taskDeadline, membersList, parentEvent);
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

    /**
     * This method verifies whether the saved title of the parent event is the same as the one currently loaded
     * in the main event catalog.
     *
     * @param titleOfParentEvent The title of the saved parent event in slamData.txt
     * @param indexOfParentEvent The title of the actual event the task is supposed to be associated with in the main
     *                           event catalog
     * @return True if they are the same. False otherwise.
     */
    private static boolean verifyParentEvent(String titleOfParentEvent, int indexOfParentEvent) {
        Event parentEvent = Duke.eventCatalog.get(indexOfParentEvent);
        String actualTitleOfParentEvent = parentEvent.getTitle();
        return titleOfParentEvent.equals(actualTitleOfParentEvent);
    }
}
