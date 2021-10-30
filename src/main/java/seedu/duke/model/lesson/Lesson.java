package seedu.duke.model.lesson;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.DukeException;
import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.TimeUtil;
import seedu.duke.ui.Ui;

import static seedu.duke.commons.util.LinkUtil.formatLink;

//@@author Roycius
public class Lesson {
    private static final String LINUX_LAUNCH_COMMAND = "xdg-open ";
    private static final String MAC_LAUNCH_COMMAND = "open ";
    private static final String WINDOWS_LAUNCH_COMMAND = "rundll32 url.dll,FileProtocolHandler ";

    private final String title;
    private final String dayOfTheWeek;
    private final String startTime;
    private final String endTime;
    private final String meetingUrl;

    public Lesson(String title, String dayOfTheWeek, String startTime, String endTime, String meetingUrl) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingUrl = meetingUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getMeetingUrl() {
        return meetingUrl;
    }

    //@@author richwill28
    public void launchUrl() throws IOException {
        Runtime rt = Runtime.getRuntime();
        String os = System.getProperty("os.name").toLowerCase();

        boolean isLinux = os.contains("nix") || os.contains("nux");
        boolean isMac = os.contains("mac");
        boolean isWindows = os.contains("win");

        String url = formatLink(meetingUrl);
        if (isLinux) {
            rt.exec(LINUX_LAUNCH_COMMAND + url);
        } else if (isMac) {
            rt.exec(MAC_LAUNCH_COMMAND + url);
        } else if (isWindows) {
            rt.exec(WINDOWS_LAUNCH_COMMAND + url);
        }
    }

    //@@author Roycius
    /**
     * Serializes the lesson data into the correct format for storage file.
     *
     * @return serialized lesson data
     */
    public String serialize() {
        return title + " | " + dayOfTheWeek + " | " + startTime + " | " + endTime + " | " + meetingUrl;
    }

    /**
     * Deserializes the lesson data from the storage file.
     *
     * @param line a line of string representing the serialized data
     * @return deserialized lesson data
     */
    public static Lesson deserialize(Ui ui, String line) {
        try {
            String[] params = line.split("\\s*[|]\\s*");

            String title = params[0];
            String dayOfTheWeek = DayOfTheWeek.toProper(params[1]);

            String startTime = LocalTime.parse(TimeUtil.parseTwelveHourTime(params[2]))
                    .format(DateTimeFormatter.ofPattern("hh:mm a"));

            String endTime = LocalTime.parse(TimeUtil.parseTwelveHourTime(params[3]))
                    .format(DateTimeFormatter.ofPattern("hh:mm a"));

            String meetingUrl = params[4];
            return new Lesson(title, dayOfTheWeek, startTime, endTime, meetingUrl);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | DukeException e) {
            // Ignoring the particular line
            ui.printMessage(Messages.ERROR_DESERIALIZING_LESSON);
            return null;
        }
    }

    @Override
    public String toString() {
        return title + System.lineSeparator()
                + Ui.PADDING + "   " + dayOfTheWeek + ", " + startTime + " - " + endTime + System.lineSeparator()
                + Ui.PADDING + "   " + "Meeting URL: " + meetingUrl;
    }
}
