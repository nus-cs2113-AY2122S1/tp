package seedu.duke.commands.calendar;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.constants.CommandConstants;
import seedu.duke.exceptions.calendar.DuplicateTaskException;
import seedu.duke.exceptions.calendar.InvalidDateException;
import seedu.duke.exceptions.calendar.LectureIncorrectDateException;
import seedu.duke.exceptions.calendar.ModuleNotFoundException;
import seedu.duke.module.ModuleList;
import seedu.duke.schedule.lecture.Lecture;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageLecture;
import seedu.duke.ui.Ui;

import static seedu.duke.constants.Messages.DELIMITER_DATE;

//@@author swatimahadevan

/**
 * Represents the class to execute adding of module lecture.
 */
public class AddLectureCommand extends Command {
    public static final String MESSAGE_DUPLICATE_LECTURE = "A lecture for this module has already been added!";
    public static final String MESSAGE_ADD_LECTURE = "Added lecture!";
    public static final String MESSAGE_NOT_FOUND_MODULE = "Could not find module, "
            + "add module first before adding lecture!";
    private ArrayList<String> argumentsLecture;

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public AddLectureCommand() {
        syntax = "calendar lecture m/ [MODULE_CODE] s/ [DD-MM-YYYY(START_DATE)] e/ [DD-MM-YYYY(END_DATE)]";
    }

    /**
     * Checks if a lecture for the same module already exists.
     *
     * @param lecture The Lecture object.
     * @throws DuplicateTaskException If user tries to add a duplicate task/lecture.
     */
    public static void checkIfDuplicate(Lecture lecture) throws DuplicateTaskException {
        for (int i = 0; i < Storage.lectureList.getLectureList().size(); i++) {
            if (lecture.getModuleName().equals(Storage.lectureList.getLectureList().get(i).getModuleName())) {
                throw new DuplicateTaskException(MESSAGE_DUPLICATE_LECTURE);
            }
        }
    }

    /**
     * Class constructor.
     *
     * @param argumentsLecture The arguments for lecture command.
     */
    public AddLectureCommand(ArrayList<String> argumentsLecture) {
        this.argumentsLecture = argumentsLecture;
    }

    /**
     * Throws exception if date not valid.
     *
     * @param lectureDateStringFormat The date from user in string format.
     * @throws InvalidDateException If user provides invalid date.
     */
    public static void checkIfDateValid(String lectureDateStringFormat) throws InvalidDateException {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(CommandConstants.DATE_CONSTANT);
        try {
            LocalDate inputDate = LocalDate.parse(lectureDateStringFormat, localDateFormatter);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Throws exception if start date not before end date for lecture.
     *
     * @param dateStartYear Start date year.
     * @param dateEndYear End date year.
     * @param dateStartMonth Start date month.
     * @param dateEndMonth End date month.
     * @param dateStartDay Start date day.
     * @param dateEndDay End date day.
     * @throws LectureIncorrectDateException if start date not before end date for lecture.
     */
    private static void checkIfStartBeforeEndDate(int dateStartYear, int dateEndYear,
                                                  int dateStartMonth,
                                                  int dateEndMonth,
                                                  int dateStartDay, int dateEndDay)
            throws LectureIncorrectDateException {
        if (dateStartYear > dateEndYear) {
            throw new LectureIncorrectDateException();
        } else if (dateEndYear == dateStartYear && dateStartMonth > dateEndMonth) {
            throw new LectureIncorrectDateException();
        } else if (dateEndMonth == dateStartMonth && dateStartDay > dateEndDay) {
            throw new LectureIncorrectDateException();
        }
    }

    /**
     * To execute adding of lecture.
     *
     * @param ui      The component of CLICK that deals with the interaction with the user.
     * @param storage The component of CLICK that deals with loading tasks from the file and saving tasks in the file.
     * @throws IOException in case of writing to save file error.
     * @throws InvalidDateException in case date is incorrect.
     * @throws ModuleNotFoundException in case module has not been added before.
     * @throws DuplicateTaskException in case user is trying to add duplicate task.
     */
    @Override

    public void execute(Ui ui, Storage storage) throws IOException,
            ModuleNotFoundException, InvalidDateException, DuplicateTaskException, LectureIncorrectDateException {
        ModuleList moduleList = storage.storageModule.readModulesFromFile();
        String module = argumentsLecture.get(0).trim();
        boolean isModuleInList = false;
        for (int i = 0; i < moduleList.getNumberOfModules(); i++) {
            if (module.equalsIgnoreCase(moduleList.getModuleByIndex(i).getCode().trim())) {
                isModuleInList = true;
            }
        }
        if (isModuleInList) {
            String dateStartStringFormat = argumentsLecture.get(1).trim();
            String dateEndStringFormat = argumentsLecture.get(2).trim();
            Lecture lecture = new Lecture(module, dateStartStringFormat, dateEndStringFormat);
            checkIfDateValid(dateStartStringFormat);
            checkIfDateValid(dateEndStringFormat);
            checkIfDuplicate(lecture);
            String[] dateStartArgs = dateStartStringFormat.split(DELIMITER_DATE);
            String[] dateEndArgs = dateEndStringFormat.split(DELIMITER_DATE);
            int dateStartYear = Integer.parseInt(dateStartArgs[2]);
            int dateEndYear = Integer.parseInt(dateEndArgs[2]);
            int dateStartMonth = Integer.parseInt(dateStartArgs[1]);
            int dateEndMonth = Integer.parseInt(dateEndArgs[1]);
            int dateStartDay = Integer.parseInt(dateStartArgs[0]);
            int dateEndDay = Integer.parseInt(dateEndArgs[0]);
            checkIfStartBeforeEndDate(dateStartYear,
                    dateEndYear, dateStartMonth, dateEndMonth, dateStartDay, dateEndDay);
            ui.printLine();
            storage.lectureList.addLecture(lecture);
            ui.printMessage(MESSAGE_ADD_LECTURE);
        } else {
            ui.printLine();
            ui.printMessage(MESSAGE_NOT_FOUND_MODULE);
        }
        ui.printLine();
        StorageLecture.writeLectureList(storage.lectureList);
    }

}
