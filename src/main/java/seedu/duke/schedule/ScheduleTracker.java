package seedu.duke.schedule;

import seedu.duke.ClickfitMessages;
import seedu.duke.Storage;
import seedu.duke.Parser;
import seedu.duke.exceptions.schedule.DuplicateRescheduledWorkoutException;
import seedu.duke.exceptions.schedule.DuplicateScheduledWorkoutException;
import seedu.duke.exceptions.schedule.InvalidActivityFormatException;
import seedu.duke.exceptions.schedule.DeleteScheduleException;
import seedu.duke.exceptions.schedule.MissingScheduleDescriptionException;
import seedu.duke.exceptions.schedule.MissingScheduleSeparatorException;
import seedu.duke.exceptions.schedule.NoScheduleIndexException;
import seedu.duke.exceptions.schedule.ScheduleException;
import seedu.duke.exceptions.schedule.ScheduleNullArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@@author arvejw

/**
 * Manages the tracking of scheduled workouts and various other operations such as adding new scheduled workouts and
 * deleting them.
 */
public class ScheduleTracker {

    private ArrayList<ScheduledWorkout> scheduledWorkouts;
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;
    private static final int FIRST_INDEX_IN_LIST = 0;
    private static final int DAYS_IN_A_WEEK = 7;
    private static final String INPUT_ALL = "all";
    public static final Logger SCHEDULE_TRACKER_LOGGER = Logger.getLogger("ScheduleTrackerLogger");

    public ScheduleTracker() {
        scheduledWorkouts = new ArrayList<>();
        SCHEDULE_TRACKER_LOGGER.setLevel(Level.SEVERE);
    }

    /**
     * Returns the private attribute scheduledWorkouts.
     *
     * @return Array List of scheduled workouts.
     */
    public ArrayList<ScheduledWorkout> getScheduledWorkouts() {
        return scheduledWorkouts;
    }

    /**
     * Loads the schedule data from the data file.
     */
    public void loadScheduleData() {
        File dataFile = new File(Storage.SCHEDULE_FILE_PATH);
        if (dataFile.length() == 0) {
            return;
        }
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.out.println(ClickfitMessages.SCHEDULE_DATA_NOT_FOUND);
            return;
        }
        String currentLine = Parser.EMPTY_STRING;
        boolean isDataLoadCorrectly = true;
        while (fileScanner.hasNext()) {
            currentLine = fileScanner.nextLine();
            if (currentLine.isEmpty()) {
                continue;
            }
            try {
                addScheduledWorkout(currentLine, true, false);
            } catch (Exception e) {
                isDataLoadCorrectly = false;
            }
        }
        cleanUpScheduleList();
        if (!isDataLoadCorrectly) {
            System.out.println(ClickfitMessages.INCORRECT_LOADING_SCHEDULE_DATA);
        }
    }

    /**
     * Generates the parameters to be used to construct a ScheduledWorkout object.
     * Parameters will be returned as a String[] of size 3. String[0] contains the workout description,
     * String[1] contains the workout date, String[2] contains the workout time.
     *
     * @param inputArguments Arguments input by the user that come after the command word.
     * @return String[] The generated parameters in a String array of size 3.
     * @throws ScheduleException If there are issues generating schedule description.
     */
    public String[] generateScheduledWorkoutParameters(String inputArguments) throws ScheduleException {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting generation of parameters for scheduled workout.");
        String workoutDescription = Parser.getScheduleDescription(inputArguments);
        String workoutDate = Parser.getDateNoDateTracker(inputArguments);
        String workoutTime = Parser.getTime(inputArguments);
        String[] generatedParameters = {workoutDescription, workoutDate, workoutTime};
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully generated parameters for scheduled workout.");
        return generatedParameters;
    }

    /**
     * Adds a scheduled workout to the list of scheduled workouts.
     *
     * @param inputArguments      Arguments input by the user that come after the command word.
     * @param isSquelchAddMessage Flag that determines whether to squelch the message printed to the user
     *                            during successful adding of a scheduled workout. <code>true</code> to squelch,
     *                            <code>false</code> to continue printing the message.
     * @param isCleanUp           Flag that determines whether to clean up the schedule list after successfully
     *                            adding a scheduled workout.
     * @throws ScheduleException If there are issues adding a scheduled workout.
     */
    public void addScheduledWorkout(String inputArguments, boolean isSquelchAddMessage, boolean isCleanUp)
            throws ScheduleException {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting to try and add scheduled workout.");
        nullArgumentCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        missingDescriptionCheck(inputArguments);
        scheduledWorkoutSeparatorCheck(inputArguments);
        // index 0: description | index 1: date | index 2: time
        String[] generatedParameters = generateScheduledWorkoutParameters(inputArguments);
        assert generatedParameters.length == 3 : "Exactly 3 parameters should be generated";
        String workoutDescription = generatedParameters[0];
        String workoutDate = generatedParameters[1];
        String workoutTime = generatedParameters[2];
        Map<String, ArrayList<Integer>> activityMap;
        try {
            activityMap = Parser.getActivities(inputArguments);
        } catch (NumberFormatException nfe) {
            throw new InvalidActivityFormatException();
        }
        boolean isRecurringWorkout = Parser.isRecurringWorkout(inputArguments);
        ScheduledWorkout workoutToAdd = new ScheduledWorkout(
                workoutDescription, workoutDate, workoutTime, activityMap, isRecurringWorkout);
        duplicateScheduledWorkoutCheck(workoutToAdd);
        scheduledWorkouts.add(workoutToAdd);
        if (!isSquelchAddMessage) {
            System.out.println(ClickfitMessages.getAddScheduleSuccessMessage(workoutToAdd));
            if (workoutToAdd.hasActivities()) {
                System.out.println(workoutToAdd.getActivitiesAsStringToPrint(false));
            }
        }
        if (isCleanUp) {
            cleanUpScheduleList();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully added workout to schedule.");
    }

    /**
     * Checks whether the scheduled workout index is within range.
     * This check is done under the assumption that ones-indexing is used.
     *
     * @param workoutNumber Index of the scheduled workout.
     * @return boolean <code>true</code> if within range, <code>false</code> otherwise.
     */
    public boolean isScheduledWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = scheduledWorkouts.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    /**
     * Deletes a scheduled workout from the list of scheduled workouts.
     *
     * @param inputArguments Arguments input by the user that come after the command word.
     * @throws ScheduleException If there are issues deleting a scheduled workout
     */
    public void deleteScheduledWorkout(String inputArguments) throws ScheduleException {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting to try and delete scheduled workout.");
        if (inputArguments == null) {
            throw new NoScheduleIndexException();
        }
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        if (isScheduledWorkoutListEmpty()) {
            System.out.println(ClickfitMessages.EMPTY_SCHEDULE_LIST_MESSAGE);
            return;
        }
        assert scheduledWorkouts.size() > 0 : "List should be non empty at this point";
        int workoutNumber = Parser.parseStringToInteger(inputArguments);
        int workoutIndex = workoutNumber - 1; // 0-indexing
        if (isScheduledWorkoutNumberWithinRange(workoutNumber)) {
            ScheduledWorkout workoutToDelete = scheduledWorkouts.get(workoutIndex);
            System.out.println(ClickfitMessages.getDeleteScheduleSuccessMessage(workoutToDelete));
            scheduledWorkouts.remove(workoutIndex);
            SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully deleted scheduled workout.");
        } else {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "Failed to delete scheduled workout.");
            throw new DeleteScheduleException();
        }
    }

    /**
     * Prints out the list of scheduled workouts.
     * Either a filtered list based off a certain date or the full schedule list can be printed.
     *
     * @param inputArguments Date to use as a filter in the format dd/mm/yyyy.
     *                       If the input is <code>all</code> the full list of all scheduled workouts is printed.
     */
    public void listScheduledWorkouts(String inputArguments) {
        cleanUpScheduleList();
        if (inputArguments.equals(INPUT_ALL)) {
            listAllScheduledWorkouts();
        } else {
            listScheduledWorkoutsOnDate(inputArguments);
        }
    }

    /**
     * Prints out the full list of all scheduled workouts.
     */
    public void listAllScheduledWorkouts() {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting to try and list scheduled workouts.");
        if (isScheduledWorkoutListEmpty()) {
            System.out.println(ClickfitMessages.EMPTY_SCHEDULE_LIST_MESSAGE);
            return;
        }
        System.out.println(ClickfitMessages.FULL_SCHEDULE_LIST_MESSAGE);
        int currentIndex = 1;
        int scheduleCount = 0;
        for (ScheduledWorkout workout : scheduledWorkouts) {
            System.out.println(currentIndex + ". " + workout.getWorkoutDescription() + workout.isRecurringStatus());
            System.out.println("Date: " + workout.getWorkoutDate());
            System.out.println("Time: " + workout.getWorkoutTime());
            System.out.println(workout.getActivitiesAsStringToPrint(true));
            currentIndex++;
            scheduleCount++;
        }
        System.out.println(ClickfitMessages.getTotalScheduledWorkoutMessage(scheduleCount));
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully listed workouts.");
    }

    /**
     * Prints out a filtered schedule list based off a certain date.
     *
     * @param inputArguments Date to use as a filter in the format dd/mm/yyyy.
     */
    public void listScheduledWorkoutsOnDate(String inputArguments) {
        ArrayList<ScheduledWorkout> filteredScheduleList = (ArrayList<ScheduledWorkout>) scheduledWorkouts.stream()
                .filter((t) -> t.getWorkoutDate().equals(inputArguments)).collect(Collectors.toList());
        if (filteredScheduleList.isEmpty()) {
            if (inputArguments.equals(Parser.getSystemDate())) {
                System.out.println(ClickfitMessages.EMPTY_SCHEDULE_LIST_TODAY_MESSAGE);
            } else {
                System.out.println(ClickfitMessages.getEmptyScheduleOnDateMessage(inputArguments));
            }
        } else {
            if (inputArguments.equals(Parser.getSystemDate())) {
                System.out.println(ClickfitMessages.WORKOUT_SCHEDULE_TODAY_MESSAGE);
            } else {
                System.out.println(ClickfitMessages.getWorkoutScheduleOnDateMessage(inputArguments));
            }
            int currentIndex = 1;
            int workoutCount = 0;
            for (ScheduledWorkout workout : filteredScheduleList) {
                System.out.println(currentIndex + ". "
                        + workout.getWorkoutDescription() + workout.isRecurringStatus());
                System.out.println("Date: " + workout.getWorkoutDate());
                System.out.println("Time: " + workout.getWorkoutTime());
                System.out.println(workout.getActivitiesAsStringToPrint(true));
                currentIndex++;
                workoutCount++;
            }
            System.out.println(ClickfitMessages.getScheduledWorkoutCountMessage(workoutCount));
        }
    }

    /**
     * Cleans up the schedule list from any overdue workouts.
     * If workout is non-recurring and the date is passed it is deleted. Otherwise if it is recurring, the workout
     * is rescheduled by the required days in multiples of 7.
     */
    public void cleanUpScheduleList() {
        if (isScheduledWorkoutListEmpty()) {
            return;
        }
        sortScheduleList();
        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        boolean isAnyWorkoutUpdatedOrDeleted = false;
        boolean isAnyWorkoutOverdue = true;
        ScheduledWorkout firstWorkoutEntry;
        while (isAnyWorkoutOverdue) {
            if (isScheduledWorkoutListEmpty()) {
                break;
            }
            firstWorkoutEntry = scheduledWorkouts.get(FIRST_INDEX_IN_LIST);
            if (firstWorkoutEntry.getWorkoutDateAsLocalDate().isBefore(currentDate)) {
                updateOrDeleteScheduledWorkout(firstWorkoutEntry, currentDate);
                isAnyWorkoutUpdatedOrDeleted = true;
            } else {
                isAnyWorkoutOverdue = false;
            }
        }
        if (isAnyWorkoutUpdatedOrDeleted) {
            System.out.println(ClickfitMessages.DELETE_OR_UPDATE_SCHEDULE_MESSAGE);
        }
    }

    /**
     * If workout is non recurring and the date is passed it is deleted.
     * Otherwise if it is recurring, the workout's date is updated by the required days in multiples of 7.
     *
     * @param scheduledWorkout Scheduled workout to be updated or deleted.
     * @param currentDate      Current date.
     */
    public void updateOrDeleteScheduledWorkout(ScheduledWorkout scheduledWorkout, LocalDate currentDate) {
        if (scheduledWorkout.isRecurring()) {
            try {
                rescheduleRecurringWorkout(scheduledWorkout, currentDate);
            } catch (ScheduleException e) {
                scheduledWorkouts.remove(scheduledWorkout);
                System.out.println(e.getMessage());
            }
        } else {
            scheduledWorkouts.remove(scheduledWorkout);
        }
        sortScheduleList();
    }

    /**
     * Reschedules recurring workout by the required days in multiples of 7.
     *
     * @param scheduledWorkout Scheduled workout to be rescheduled.
     * @param currentDate      Current date.
     * @throws ScheduleException If workout to be rescheduled already exists in the list.
     */
    public void rescheduleRecurringWorkout(ScheduledWorkout scheduledWorkout, LocalDate currentDate)
            throws ScheduleException {
        long daysUntilCurrentDate = ChronoUnit.DAYS.between(scheduledWorkout.getWorkoutDateAsLocalDate(), currentDate);
        long daysToAdd = (long) (Math.ceil((double) daysUntilCurrentDate / DAYS_IN_A_WEEK) * DAYS_IN_A_WEEK);
        ScheduledWorkout copyOfScheduledWorkout = new ScheduledWorkout(scheduledWorkout);
        copyOfScheduledWorkout.incrementWorkoutDate(daysToAdd);
        try {
            duplicateScheduledWorkoutCheck(copyOfScheduledWorkout);
        } catch (ScheduleException e) {
            throw new DuplicateRescheduledWorkoutException();
        }
        scheduledWorkout.incrementWorkoutDate(daysToAdd);
    }

    /**
     * Sorts the list of scheduled workouts in ascending order of date time.
     */
    public void sortScheduleList() {
        scheduledWorkouts.sort(Comparator.comparing(ScheduledWorkout::getWorkoutDateTime));
    }

    /**
     * Checks whether input argument is null.
     *
     * @param inputArguments Arguments input by the user that come after the command word.
     * @throws ScheduleException If input argument is null.
     */
    public void nullArgumentCheck(String inputArguments) throws ScheduleException {
        if (inputArguments == null) {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "User input argument is null.");
            throw new ScheduleNullArgumentException();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "User input argument(s) is not null.");
    }

    /**
     * Checks whether the compulsory separators are present in the user input.
     * Namely the the date separator <code>/d</code> and time separator <code>/t</code>.
     *
     * @param inputArguments Arguments input by the user that come after the command word.
     * @throws ScheduleException If missing separator detected.
     */
    public void scheduledWorkoutSeparatorCheck(String inputArguments) throws ScheduleException {
        boolean areSeparatorsCorrect = Parser.containsDateSeparator(inputArguments)
                && Parser.containsTimeSeparator(inputArguments);
        if (!areSeparatorsCorrect) {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "Separators in user input are missing or invalid.");
            throw new MissingScheduleSeparatorException();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Separators in user input are correct.");
    }

    /**
     * Checks whether the list of scheduled workouts is empty.
     *
     * @return <code>true</code> if schedule list is empty, <code>false</code> otherwise.
     */
    public boolean isScheduledWorkoutListEmpty() {
        return scheduledWorkouts.isEmpty();
    }

    /**
     * Checks whether the description of the scheduled workout is missing in the user input.
     *
     * @param inputArguments Arguments input by the user that come after the command word.
     * @throws ScheduleException If unable to find description or find separators.
     */
    public void missingDescriptionCheck(String inputArguments) throws ScheduleException {
        int indexOfFirstDateSeparator = inputArguments.indexOf(Parser.DATE_SEPARATOR.trim());
        String subStringBeforeDateSeparator = "";
        if (indexOfFirstDateSeparator != -1) {
            subStringBeforeDateSeparator = inputArguments.substring(0, indexOfFirstDateSeparator).trim();
        } else {
            scheduledWorkoutSeparatorCheck(inputArguments);
        }
        if (subStringBeforeDateSeparator.isEmpty()) {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "Description is missing in user input arguments.");
            throw new MissingScheduleDescriptionException();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Description is present in user input arguments.");
    }

    /**
     * Checks whether a duplicate scheduled workout already exists in the list.
     *
     * @param scheduledWorkoutToAdd The <code>ScheduledWorkout</code> object to be potentially added.
     * @throws ScheduleException If duplicate workout detected.
     */
    public void duplicateScheduledWorkoutCheck(ScheduledWorkout scheduledWorkoutToAdd) throws ScheduleException {
        String scheduledWorkoutAsString = scheduledWorkoutToAdd.getScheduledWorkoutAsDataString();
        for (ScheduledWorkout s : scheduledWorkouts) {
            if (scheduledWorkoutAsString.equals(s.getScheduledWorkoutAsDataString())) {
                throw new DuplicateScheduledWorkoutException();
            }
        }
    }
}
