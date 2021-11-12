package seedu.duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateTracker {
    protected static ArrayList<String> dates = new ArrayList<>();

    //@@author VishalJeyaram
    /**
     * Checks if a date for any parameter has already been accounted for.
     * If is has not been accounted for, the date will be added to the list of dates.
     *
     * @param newDate Date to be checked
     */
    public static void checkIfDateExists(String newDate) {
        for (String date : dates) {
            if (date.equals(newDate)) {
                return;
            }
        }
        dates.add(newDate);
        sortDate();
    }

    public static void sortDate() {
        Collections.sort(dates);
    }

    //@@author pragyan01
    /**
     * This method sorts an array list by date.
     *
     * @param list any array list provided.
     *
     * @author pragyan01
     */
    public static void sortDate(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            /**
             * This method compares two date strings.
             *
             * @return sorted array list by date.
             */
            public int compare(String o1, String o2) {
                LocalDate o1Date = LocalDate.parse(Parser.getDateNoDateTracker(o1),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate o2Date = LocalDate.parse(Parser.getDateNoDateTracker(o2),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return o1Date.compareTo(o2Date);
            }
        });
    }

    //@@author VishalJeyaram
    /**
     * Deletes a date from the list of dates if no meal, workout, weight or fluid occurs on that date.
     *
     * @param inputArguments User's input
     * @param fluid Fluid object
     * @param meal Meal object
     * @param workoutTracker WorkoutTracker object
     * @param weightTracker WeightTracker object
     */
    public static void deleteDateFromList(String inputArguments, Fluid fluid, Meal meal,
                                          WorkoutTracker workoutTracker,
                                          WeightTracker weightTracker) {
        String date = Parser.getDate(inputArguments);
        for (String m : meal.meals) {
            if (m.contains(date)) {
                return;
            }
        }
        for (String f : fluid.fluidArray) {
            if (f.contains(date)) {
                return;
            }
        }
        for (String w : workoutTracker.workouts) {
            if (w.contains(date)) {
                return;
            }
        }
        for (String w : weightTracker.weightsArray) {
            if (w.contains(date)) {
                return;
            }
        }
        dates.remove(date);
    }

    //@@author arvejw
    /**
     * Sorts ArrayList of String by ascending order of date time.
     *
     * @param list ArrayList to be sorted.
     */
    public static void sortDateAndTime(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                LocalDateTime o1DateTime = LocalDateTime.of(
                        LocalDate.parse(Parser.getDateNoDateTracker(o1),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalTime.parse(Parser.getTime(o1),
                                DateTimeFormatter.ofPattern("HH:mm")));
                LocalDateTime o2DateTime = LocalDateTime.of(
                        LocalDate.parse(Parser.getDateNoDateTracker(o2),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalTime.parse(Parser.getTime(o2),
                                DateTimeFormatter.ofPattern("HH:mm")));
                return o1DateTime.compareTo(o2DateTime);
            }
        });
    }
}

