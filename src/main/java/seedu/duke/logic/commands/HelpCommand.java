package seedu.duke.logic.commands;

import static seedu.duke.logic.commands.CommandMessages.LS;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_ADD_HELP = "add -- Add food or exercise record to the current list.";
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page." + LS
            + "Below are the commands to get you started." + LS
            + "More details could be found on: " + LS
            + "https://tinyurl.com/fitbot-user-guide";
    private static final String MESSAGE_COMMON_NOTATIONS = "In the formats of the command, prefixes wrapped in "
            + "curly brackets {} means that they are optional.";
    private static final String EMPTY = "";
    public static final String MESSAGE_COMMAND_FORMAT =
            CommandMessages.QUOTATION + COMMAND_WORD + CommandMessages.QUOTATION;
    public static final String MESSAGE_DELETE_HELP = "delete -- Delete entry of food or exercise added from a list.";
    public static final String MESSAGE_EDIT_HELP = "edit -- Edit entry of food or exercise added from a list.";
    public static final String MESSAGE_VIEW_HELP = "view -- View all the food and/or exercises added.";

    private static String bmi = "bmi -- Calculate the Body-Mass-Index of user" + LS
            + "      Format: bmi {h/HEIGHT_IN_CM w/WEIGHT_IN_KG}" + LS
            + "        Prefix               Input " + LS
            + "          h/             Height of user in cm" + LS
            + "          w/             Weight of user in kg" + LS
            + "      If no prefixes are given, bmi will be calculated using the height and weight in the profile.";
    private static String help = LS
            + "help -- View help for commands" + LS
            + "      Format: help";
    private static String profile = LS
            + "profile -- View or modify profile details" + LS
            + "      Format: profile {n/NAME} {h/HEIGHT(CM)} {w/WEIGHT(KG)} {a/AGE} {g/CALORIEGOAL} {s/GENDER(M/F)}"
            + " {x/ACTIVITYFACTOR(1-5)}" + LS
            + "        Prefix               Input " + LS
            + "          n/             Name of user" + LS
            + "          h/             Height of user in cm" + LS
            + "          w/             Weight of user in kg" + LS
            + "          s/             Gender of user, m for male, f for female" + LS
            + "          a/             Age of user" + LS
            + "          g/             Net calorie goal of user." + LS
            + "                         Net calorie is based on calorie of food consumed - "
            + "calories burnt from exercise and bmr" + LS
            + "          x/             Activity factor of user, range 1 to 5" + LS
            + "      If no prefixes are given, user will be shown the current profile particulars.";
    private static String overview = LS
            + "overview -- View weekly and daily summary of calories" + LS
            + "      Format: overview";
    private static String bye = LS
            + "bye -- Exit the program." + LS
            + "      Format: bye";
    private static String add = "      Add Food Item" + LS
            + "      Format: add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}" + LS
            + "        Prefix               Input" + LS
            + "          f/             Description of the food" + LS
            + "          c/             Calories of the food" + LS
            + "          d/             Date of food in DD-MM-YYYY" + LS
            + "          t/             Time of food in HHMM" + LS
            + LS
            + "      Add Exercise Item" + LS
            + "      Format: add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}" + LS
            + "        Prefix               Input" + LS
            + "          e/             Description of exercise" + LS
            + "          c/             Calories burnt from exercise" + LS
            + "          d/             Date of exercise in DD-MM-YYYY" + LS
            + LS
            + "      Add Recurring Exercise to Upcoming Exercise List" + LS
            + "      Format: add r/ITEM c/CALORIES :/START_DATE -/END_DATE @/DAY_OF_THE_WEEK {,DAY_OF_THE_WEEK,...}"
            + LS
            + "      Format: delete e/LIST_NO d/DD-MM-YYYY" + LS
            + "        Prefix               Input" + LS
            + "          r/             Description of upcoming exercise" + LS
            + "          c/             Calories burnt from exercise" + LS
            + "          :/             Start date of exercise in DD-MM-YYYY" + LS
            + "          -/             End date of exercise in DD-MM-YYYY" + LS
            + "          @/             Workout days of the week" + LS
            + LS
            + "      Add Food Item in Food Bank" + LS
            + "      Format: add fbank/ITEM c/CALORIES" + LS
            + "        Prefix               Input" + LS
            + "          fbank/         Description of food" + LS
            + "          c/             Calories of the food" + LS
            + LS
            + "      Add Exercise Item in ExerciseBank" + LS
            + "      Format: add ebank/ITEM c/CALORIES" + LS
            + "        Prefix               Input" + LS
            + "          ebank/         Description of exercise" + LS
            + "          c/             Calories burnt from exercise";
    private static String view = LS
            + "      Viewing Food List" + LS
            + "      Format: view f/" + LS
            + LS
            + "      View Exercise List" + LS
            + "      Format: view e/" + LS
            + LS
            + "      View Upcoming Exercise List" + LS
            + "      Format: view u/" + LS
            + LS
            + "      View Food Bank" + LS
            + "      Format: view fbank/" + LS
            + LS
            + "      View Exercise Bank" + LS
            + "      Format: view ebank/";

    private static String delete = "      Deleting Food Item" + LS
            + "      Format: delete f/LIST_NO d/DD-MM-YYYY t/HHMM" + LS
            + "        Prefix               Input" + LS
            + "          f/             Index of food in Food List within the given date" + LS
            + "          d/             Date of food in DD-MM-YYYY" + LS
            + "          t/             Time of food in HHMM" + LS
            + LS
            + "      Delete Exercise Item" + LS
            + "      Format: delete e/LIST_NO d/DD-MM-YYYY" + LS
            + "        Prefix               Input" + LS
            + "          e/             Index of exercise in Exercise List within the given date" + LS
            + "          d/             Date of exercise in DD-MM-YYYY" + LS
            + "  " + LS
            + "      Delete Upcoming Exercise Items from Upcoming Exercise List" + LS
            + "      Format: delete u/LIST_NO {,LIST_NO,...}" + LS
            + "        Prefix               Input" + LS
            + "          u/             The index of the item within the Upcoming Exercise List" + LS
            + LS
            + "      Delete Food Item from Food Bank" + LS
            + "      Format: delete fbank/LIST_NO {,LIST_NO,...}" + LS
            + "        Prefix               Input" + LS
            + "          fbank/         The index of the item within the Food Bank" + LS
            + LS
            + "      Delete Exercise Item from Exercise Bank" + LS
            + "      Format: delete ebank/LIST_NO {,LIST_NO,...}" + LS
            + "        Prefix               Input" + LS
            + "          ebank/         The index of the item within the Exercise Bank";
    private static String edit = "      Edit Food Bank" + LS
            + "      Format: edit fbank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}" + LS
            + "        Prefix               Input" + LS
            + "          fbank/         The index of the item within the Food Bank" + LS
            + "          n/             New description of food name" + LS
            + "          c/             Calories of food" + LS
            + "   " + LS
            + "      Edit Exercise Bank" + LS
            + "      Format: edit ebank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}" + LS
            + "        Prefix               Input" + LS
            + "          ebank/         The index of the item within the Exercise Bank" + LS
            + "          n/             New description of exercise name" + LS
            + "          c/             Calories burnt from exercise" + LS
            + LS
            + "     Edit Upcoming Exercise List" + LS
            + "     Format: edit u/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}" + LS
            + "        Prefix               Input" + LS
            + "          u/             The index of the item within the Upcoming Exercise List" + LS
            + "          n/             New description of exercise name" + LS
            + "          c/             Calories burnt from exercise";

    private String buildHelpString2() {
        StringBuilder helpMessage = new StringBuilder(EMPTY);
        helpMessage.append(MESSAGE_HELP_INTRO).append(LS).append(LS)
                .append(MESSAGE_COMMON_NOTATIONS).append(LS).append(LS)
                .append(MESSAGE_ADD_HELP).append(LS)
                .append(add).append(LS).append(LS)
                .append(bmi).append(LS)
                .append(bye).append(LS).append(LS)
                .append(MESSAGE_DELETE_HELP).append(LS)
                .append(delete).append(LS).append(LS)
                .append(MESSAGE_EDIT_HELP).append(LS)
                .append(edit).append(LS)
                .append(help).append(LS)
                .append(profile).append(LS)
                .append(overview).append(LS).append(LS)
                .append(MESSAGE_VIEW_HELP).append(LS)
                .append(view).append(LS);

        return helpMessage.toString().trim();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(buildHelpString2());
    }
}
