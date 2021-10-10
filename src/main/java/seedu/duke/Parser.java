package seedu.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static int parseStringToInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

    public static int getCalories(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" ");
        int length = userInput.length;
        int calories = 0;
        for (int i = 1; i < length ; i++) {
            if (userInput[i].equals("/c")) {
                calories = parseStringToInteger(userInput[i+1]);
                break;
            }
        }
        if (calories < 0) {
            throw new DukeException("Negative calories");
        }
        return calories;
    }

    public static String getDescription(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" /c ");
        String description = userInput[0];
        return description;
    }

    public static String getDate(String inputArguments) throws DukeException, DateTimeParseException {
        String[] userInput = inputArguments.split(" ");
        int length = userInput.length;
        String date = "";
        for (int i = 1; i < length ; i++) {
            if (userInput[i].equals("/d")) {
                date = userInput[i+1];
                break;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return formatter.format(localDate);
    }

    public static String getTime(String inputArguments) throws DukeException, DateTimeParseException {
        String[] userInput = inputArguments.split(" ");
        int length = userInput.length;
        String time = "";
        for (int i = 1; i < length ; i++) {
            if (userInput[i].equals("/t")) {
                time = userInput[i+1];
                break;
            }
        }
        LocalTime localTime = LocalTime.parse(time);
        String properTime = localTime.format(DateTimeFormatter.ofPattern("h:mma"));
        return properTime;
    }

    public static int getWeight(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" /d ");
        int weight = parseStringToInteger(userInput[0]);
        if (weight < 0) {
            throw new DukeException("Negative weight");
        }
        return weight;
    }

    public static String getScheduleDescription(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" /d ");
        String description = userInput[0];
        return description;
    }




}
