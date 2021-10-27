package seedu.duke.task.factory;

import java.time.LocalDateTime;
import java.util.Map;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.exception.StartDateAfterEndDateException;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Event;

//@@author SeanRobertDH
public class EventFactory extends TaskFactory {

    private static final TypeEnum taskType = TypeEnum.EVENT;

    LocalDateTime startDate;
    LocalDateTime endDate;

    public EventFactory(Map<String, String> flags) {
        super(taskType, EventFlag.REQUIRED_FLAGS, flags);
    }

    @Override
    void setAdditionalVariables() throws GetTaskFailedException {
        try {
            String start = flags.get(EventFlag.START_DATE);
            String end = flags.get(EventFlag.END_DATE);

            startDate = TaskParser.getDate(start);
            endDate = TaskParser.getDate(end);

            if (startDate.isAfter(endDate)) {
                throw new StartDateAfterEndDateException();
            }

        } catch (ParseDateFailedException pdfe) {
            throw new GetTaskFailedException(pdfe.getMessage());
        } catch (StartDateAfterEndDateException sdaede) {
            throw new GetTaskFailedException(sdaede.getMessage());
        }
    }

    @Override
    Task decideConstructor() {
        if (priorityEnum == null) {
            return getEventWithDefaultPriority(description, startDate, endDate, recurrenceEnum);
        } else {
            return getEventWithPriority(description, startDate, endDate, priorityEnum, recurrenceEnum);
        }
    }

    private static Event getEventWithDefaultPriority(String description,
            LocalDateTime start, LocalDateTime end, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Event(description, start, end);
        } else {
            return new Event(description, start, end, recurrence);
        }
    }

    private static Event getEventWithPriority(String description,
            LocalDateTime start, LocalDateTime end, PriorityEnum priority, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Event(description,
                start, end, priority);
        } else {
            return new Event(description, start, end, priority, recurrence);
        }
    }
}
