package seedu.tp.task.factory;

import java.time.LocalDateTime;
import java.util.Map;
import seedu.tp.exception.GetTaskFailedException;
import seedu.tp.exception.ParseDateFailedException;
import seedu.tp.exception.StartDateAfterEndDateException;
import seedu.tp.command.flags.EventFlag;
import seedu.tp.task.Task;
import seedu.tp.task.TypeEnum;
import seedu.tp.task.type.Event;

//@@author SeanRobertDH
/**
 * Factory class used to create {@link seedu.tp.task.type.Event}.
 */
public class EventFactory extends TaskFactory {

    private static final TypeEnum TASK_TYPE = TypeEnum.EVENT;

    LocalDateTime startDate;
    LocalDateTime endDate;

    /**
     * Constructor for {@link seedu.tp.task.factory.EventFactory}.
     *
     * @param flags the <code>Map&lt;String, String&gt;</code> of flags to their values.
     */
    public EventFactory(Map<String, String> flags) {
        super(TASK_TYPE, EventFlag.REQUIRED_FLAGS, flags);
    }

    @Override
    void setAdditionalVariables() throws GetTaskFailedException {
        try {
            String start = flags.get(EventFlag.START_DATE);
            String end = flags.get(EventFlag.END_DATE);

            startDate = getDate(start);
            endDate = getDate(end);

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
    Task createTask() {
        return new Event(description, startDate, endDate);
    }

    /**
     * Returns the {@link seedu.tp.task.type.Event} created.
     *
     * @return created {@link seedu.tp.task.type.Event}.
     * @throws GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.tp.task.Task} fails.
     */
    @Override
    public Event getTask() throws GetTaskFailedException {
        return (Event) super.getTask();
    }
}
