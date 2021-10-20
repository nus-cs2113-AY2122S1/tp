package seedu.duke;

import seedu.duke.attendance.Attendance;
import seedu.duke.member.Member;
import seedu.duke.training.TrainingSchedule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Keyword {
    MEMBER_ENTRY, TRAINING_SCHEDULE_ENTRY, LIST_MEMBER_KEYWORD,
    LIST_TRAINING_KEYWORD, NO_KEYWORD, EXIT_KEYWORD, ADD_MEMBER_KEYWORD,
    ADD_TRAINING_KEYWORD, DELETE_MEMBER_KEYWORD, DELETE_TRAINING_KEYWORD, FIND_TRAINING_KEYWORD, FIND_MEMBER_KEYWORD,
    EDIT_TRAINING_KEYWORD, ADD_ATTENDANCE_KEYWORD, LIST_ATTENDANCE_KEYWORD, HELP_KEYWORD
}

