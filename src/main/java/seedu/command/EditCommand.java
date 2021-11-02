package seedu.command;

import seedu.exceptions.EditException;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableUserItem;
import seedu.ui.TextUi;

import java.util.ArrayList;

public class EditCommand extends Command {

    public static final String commandSyntax = "edit";
    public static final String commandAction = "Edit a personal task in the timetable";

    private static final String CHOICE_PROMPT = "Choose your Option: ";
    private static final String TITLE_PROMPT = "New Title: ";

    private static final int ONE = 1;

    Timetable tt;
    ArrayList<TimetableUserItem> events;

    public EditCommand(Timetable timetable) {
        this.tt = timetable;
        this.events = timetable.getEvents();
    }

    public void execute() throws EditException {
        ArrayList<TimetableUserItem> timetableUserItems = tt.getEvents();
        if (timetableUserItems.isEmpty()) {
            throw new EditException("There aren't any personal tasks added to timetable yet");
        }
        TextUi.printEvents(timetableUserItems);
        String reply = TextUi.getCommand(CHOICE_PROMPT);
        int index;
        try {
            index = Integer.parseInt(reply) - ONE;
        } catch (NumberFormatException e) {
            throw new EditException("Invalid selection");
        }

        if (index > events.size() || index < 0) {
            throw new EditException("Selection out of bounds");
        }

        String answer = TextUi.getCommand(TITLE_PROMPT);
        if (answer.isEmpty() || answer.isBlank()) {
            throw new EditException("Empty input");
        }

        TimetableUserItem event = events.get(index);
        tt.editEventFromSchedule(event, answer);

        TextUi.printEditMessage();
    }
}
