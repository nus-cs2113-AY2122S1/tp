package seedu.foodorama;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class that handles all User-interface outputs that prints to the terminal.
 *
 * @author renzocanare, Dniv-ra, Rakesh12000, jhsee5
 */
public class Ui {
    private static final String LINE_DIVIDER = "____________________________________________________________";
    private static final String START_LOGO =
            "######################################################"
                    + "##########################################################" + System.lineSeparator()
                    + "#  ________  ______    ______   _______            ___"
                    + "___           _______    ______   __       __   ______   #" + System.lineSeparator()
                    + "# /        |/      \\  /      \\ /       \\          / "
                    + "     \\         /       \\  /      \\ /  \\     /  | /      \\  #" + System.lineSeparator()
                    + "# $$$$$$$$//$$$$$$  |/$$$$$$  |$$$$$$$  |        /$$$$$$  "
                    + "|        $$$$$$$  |/$$$$$$  |$$  \\   /$$ |/$$$$$$  | #" + System.lineSeparator()
                    + "# $$ |__   $$ |  $$ |$$ |  $$ |$$ |  $$ | ______ $$$  \\$"
                    + "$ | ______ $$ |__$$ |$$ |__$$ |$$$  \\ /$$$ |$$ |__$$ | #" + System.lineSeparator()
                    + "# $$    |  $$ |  $$ |$$ |  $$ |$$ |  $$ |/      |$$$$  $$"
                    + " |/      |$$    $$< $$    $$ |$$$$  /$$$$ |$$    $$ | #" + System.lineSeparator()
                    + "# $$$$$/   $$ |  $$ |$$ |  $$ |$$ |  $$ |$$$$$$/ $$ $$ $"
                    + "$ |$$$$$$/ $$$$$$$  |$$$$$$$$ |$$ $$ $$/$$ |$$$$$$$$ | #" + System.lineSeparator()
                    + "# $$ |     $$ \\__$$ |$$ \\__$$ |$$ |__$$ |        $$ \\"
                    + "$$$$ |        $$ |  $$ |$$ |  $$ |$$ |$$$/ $$ |$$ |  $$ | #" + System.lineSeparator()
                    + "# $$ |     $$    $$/ $$    $$/ $$    $$/         $$   $"
                    + "$$/         $$ |  $$ |$$ |  $$ |$$ | $/  $$ |$$ |  $$ | #" + System.lineSeparator()
                    + "# $$/       $$$$$$/   $$$$$$/  $$$$$$$/           $$$$"
                    + "$$/          $$/   $$/ $$/   $$/ $$/      $$/ $$/   $$/  #" + System.lineSeparator()
                    + "#######################################################"
                    + "#########################################################";

    private static final String EXIT_LOGO =
            "#######################################"
                    + "####################################" + System.lineSeparator()
                    + "#           _____                ____"
                    + "_                    _____           #" + System.lineSeparator()
                    + "#          /\\    \\              |\\"
                    + "    \\                  /\\    \\          #" + System.lineSeparator()
                    + "#         /::\\    \\             |:\\"
                    + "____\\                /::\\    \\         #" + System.lineSeparator()
                    + "#        /::::\\    \\            |::"
                    + "|   |               /::::\\    \\        #" + System.lineSeparator()
                    + "#       /::::::\\    \\           |::"
                    + "|   |              /::::::\\    \\       #" + System.lineSeparator()
                    + "#      /:::/\\:::\\    \\          |::"
                    + "|   |             /:::/\\:::\\    \\      #" + System.lineSeparator()
                    + "#     /:::/__\\:::\\    \\         |::"
                    + "|   |            /:::/__\\:::\\    \\     #" + System.lineSeparator()
                    + "#    /::::\\   \\:::\\    \\        |:"
                    + ":|   |           /::::\\   \\:::\\    \\    #" + System.lineSeparator()
                    + "#   /::::::\\   \\:::\\    \\       |:"
                    + ":|___|______    /::::::\\   \\:::\\    \\   #" + System.lineSeparator()
                    + "#  /:::/\\:::\\   \\:::\\ ___\\      /"
                    + "::::::::\\    \\  /:::/\\:::\\   \\:::\\    \\  #" + System.lineSeparator()
                    + "# /:::/__\\:::\\   \\:::|    |    /:::"
                    + ":::::::\\____\\/:::/__\\:::\\   \\:::\\____\\ #" + System.lineSeparator()
                    + "# \\:::\\   \\:::\\  /:::|____|   /:::"
                    + "/~~~~/~~      \\:::\\   \\:::\\   \\::/    / #" + System.lineSeparator()
                    + "#  \\:::\\   \\:::\\/:::/    /   /:::/"
                    + "    /          \\:::\\   \\:::\\   \\/____/  #" + System.lineSeparator()
                    + "#   \\:::\\   \\::::::/    /   /:::/  "
                    + "  /            \\:::\\   \\:::\\    \\      #" + System.lineSeparator()
                    + "#    \\:::\\   \\::::/    /   /:::/  "
                    + "  /              \\:::\\   \\:::\\____\\     #" + System.lineSeparator()
                    + "#     \\:::\\  /:::/    /    \\::/   "
                    + " /                \\:::\\   \\::/    /     #" + System.lineSeparator()
                    + "#      \\:::\\/:::/    /      \\/____"
                    + "/                  \\:::\\   \\/____/      #" + System.lineSeparator()
                    + "#       \\::::::/    /              "
                    + "                   \\:::\\    \\          #" + System.lineSeparator()
                    + "#        \\::::/    /              "
                    + "                     \\:::\\____\\         #" + System.lineSeparator()
                    + "#         \\::/____/                "
                    + "                     \\::/    /         #" + System.lineSeparator()
                    + "#          ~~                       "
                    + "                     \\/____/          #" + System.lineSeparator()
                    + "####################################"
                    + "#######################################";

    private static final String WELCOME_MESSAGE = START_LOGO + System.lineSeparator()
            + "#             Hello, welcome to FOOD-O-RAMA! "
            + "The number one solution for food waste management.                #" + System.lineSeparator()
            + "#                  Type a command to get us sta"
            + "rted, or type help for a list of commands!                      #" + System.lineSeparator()
            + "###########################################"
            + "#####################################################################";

    private static final String NEWPAGE_MESSAGE = START_LOGO + System.lineSeparator()
            + "#                                            SUMMARY OF COMMANDS:                                  "
            + "            #" + System.lineSeparator()
            + "# 1. Adding a new Dish : 'add dish [DISH_NAME]'                                                    "
            + "            #" + System.lineSeparator()
            + "# 2. Adding Dish Wastage : 'add dish waste [DISH_NAME/INDEX]'                                      "
            + "            #" + System.lineSeparator()
            + "# 3. Setting Dish Limit : 'set dish limit [DISH_NAME/INDEX]'                                       "
            + "            #" + System.lineSeparator()
            + "# 4. Deleting a Dish : 'del dish [DISH_NAME/INDEX]                                                 "
            + "            #" + System.lineSeparator()
            + "# 5. Adding a new Ingredient : 'add ingr [INGR_NAME]'                                              "
            + "            #" + System.lineSeparator()
            + "# 6. Adding storage to an already existing Ingredient : 'add ingr stored [INGR_NAME/INDEX]'        "
            + "            #" + System.lineSeparator()
            + "# 7. Adding Ingredient Wastage : 'add ingr waste [INGR_NAME/INDEX]'                                "
            + "            #" + System.lineSeparator()
            + "# 8. Setting Ingredient Limit : 'set ingr limit [INGR_NAME/INDEX]'                                 "
            + "            #" + System.lineSeparator()
            + "# 9. Setting Ingredient Expiry Date : 'set ingr expiry [INGR_NAME/INDEX]'                          "
            + "            #" + System.lineSeparator()
            + "# 10. Linking an Ingredient to a Dish : 'link [DISH_NAME] / [INGR_NAME]'                           "
            + "            #" + System.lineSeparator()
            + "# 11. Deleting an Ingredient : 'del ingr [INGR_NAME/INDEX]'                                        "
            + "            #" + System.lineSeparator()
            + "# 12. Find a Dish/Ingredient : 'find dish [KEYWORD] | find ingr [KEYWORD]'                         "
            + "            #" + System.lineSeparator()
            + "# 13. Editing a Dish/Ingredient Name :                                                             "
            + "            #" + System.lineSeparator()
            + "#     'edit dish name [DISH_NAME/INDEX] | edit ingr name [INGR_NAME/INDEX]'                        "
            + "            #" + System.lineSeparator()
            + "# 14. Editing Ingredient Storage : 'edit ingr stored [INGR_NAME/INDEX]'                            "
            + "            #" + System.lineSeparator()
            + "# 15. Editing a Dish/Ingredient Waste :                                                            "
            + "            #" + System.lineSeparator()
            + "#     'edit dish waste [DISH_NAME/INDEX] | edit ingr waste [INGR_NAME/INDEX]'                      "
            + "            #" + System.lineSeparator()
            + "# 16. Viewing all Dishes/Ingredients : 'list dish | list ingr'                                     "
            + "            #" + System.lineSeparator()
            + "# 17. View graph of all Dishes/Ingredients : 'graph dish | graph ingr'                             "
            + "            #" + System.lineSeparator()
            + "# 18. Sort list of Dishes/Ingredients : 'sort dish | sort ingr'                                    "
            + "            #" + System.lineSeparator()
            + "# 19. Clearing saved lists: 'clear dish' | 'clear ingr' | 'clear all'                              "
            + "            #" + System.lineSeparator()
            + "# 20. Creating a Random Dish: 'rdish'                                                              "
            + "            #" + System.lineSeparator()
            + "# 21. View full list of commands and examples: 'help'                                              "
            + "            #" + System.lineSeparator()
            + "# 22. Exiting the program: 'bye'                                                                   "
            + "            #" + System.lineSeparator()
            + "###################################################################################################"
            + "#############" + System.lineSeparator()
            + "#                              For a full, detailed list of commands, type 'help'                  "
            + "            #" + System.lineSeparator()
            + "###################################################################################################"
            + "#############";

    protected static final String HELP_ME = LINE_DIVIDER + System.lineSeparator()
            + "Here are the commands you can use:" + System.lineSeparator()
            + "Dish Commands:" + System.lineSeparator()
            + "  1. Adding a Dish : 'add dish [DISH_NAME]'" + System.lineSeparator()
            + "     Example: 'add dish chicken rice'" + System.lineSeparator()
            + "  2. Adding Dish Wastage : 'add dish waste [DISH_NAME/INDEX]'. Type the [DISH_WASTE_WEIGHT in KG] "
            + "when prompted" + System.lineSeparator()
            + "     Example: 'add dish waste chicken rice' | '0.8'" + System.lineSeparator()
            + "  3. Setting Dish Limit : 'set dish limit [DISH_NAME/INDEX]'. Type the [DISH_LIMIT_WEIGHT in KG] "
            + "when prompted" + System.lineSeparator()
            + "     Example: 'set dish limit chicken rice' | '16.9'" + System.lineSeparator()
            + "  4. Finding a Dish : 'find dish [keyword]'" + System.lineSeparator()
            + "     Example: 'find dish chicken rice'" + System.lineSeparator()
            + "  5. Editing Dish Name : 'edit dish name [DISH_NAME/INDEX]'.\n     Type the [NEW_DISH_NAME] and "
            + "'y' to confirm and 'n' to disregard the change when prompted" + System.lineSeparator()
            + "     Example: 'edit dish name chicken rice' | 'hainanese chicken rice' | 'y'" + System.lineSeparator()
            + "  6. Editing Dish Waste : 'edit dish waste [DISH_NAME/INDEX]'.\n"
            + "     Type the [NEW_DISH_WASTE_WEIGHT in KG] and 'y' to confirm and 'n' to disregard the change "
            + "when prompted" + System.lineSeparator()
            + "     Example: 'edit dish waste chicken rice' | '6.5' | 'y'" + System.lineSeparator()
            + "  7. Deleting a Dish : 'del [DISH_NAME/INDEX]'.\n "
            + "    Type 'y' to confirm and 'n' to disregard the deletion when prompted" + System.lineSeparator()
            + "     Example: 'del dish chicken rice' | 'y'" + System.lineSeparator()
            + "  8. Viewing all Dishes : 'list dish'" + System.lineSeparator()
            + "  9. Sorting Dishes according to Dish Wastage : 'sort dish'" + System.lineSeparator()
            + "  10. Viewing Graph of Dish Wastage : 'graph dish'" + System.lineSeparator()
            + "  11. Clearing all Dishes : 'clear dish'" + System.lineSeparator()
            + "  12. Creating Random Dish : 'rdish'" + System.lineSeparator()
            + "\nIngredient Commands:" + System.lineSeparator()
            + "  13. Adding a new Ingredient : 'add ingr [INGR_NAME]' "
            + "Type the [INGR_STORAGE_WEIGHT in KG] when prompted" + System.lineSeparator()
            + "      Example: 'add ingr chicken' | '2'" + System.lineSeparator()
            + "  14. Adding storage to an already existing Ingredient : 'add ingr stored [INGR_NAME/INDEX]'. "
            + "Type the [INGR_STORAGE_WEIGHT in KG] when prompted" + System.lineSeparator()
            + "      Example: 'add ingr stored chicken' | '1.5'" + System.lineSeparator()
            + "  15. Adding Ingredient Wastage : 'add ingr waste [INGR_NAME/INDEX]'. "
            + "Type the [INGR_WASTE_WEIGHT in KG] when prompted" + System.lineSeparator()
            + "      Example: 'add ingr waste chicken' | '0.7'" + System.lineSeparator()
            + "  16. Setting Ingredient Limit : 'set ingr limit [INGR_NAME/INDEX]'. "
            + "Type the [INGR_LIMIT_WEIGHT in KG] when prompted" + System.lineSeparator()
            + "      Example: 'set ingr limit chicken' | '42.7'" + System.lineSeparator()
            + "  17. Setting Ingredient Expiry : 'set ingr expiry [INGR_NAME/INDEX]'. "
            + "Type the [INGR_EXPIRY_DATE in dd/mm/yyyy Format] when prompted" + System.lineSeparator()
            + "      Example: 'set ingr limit chicken' | '42.7'" + System.lineSeparator()
            + "  18. Linking an Ingredient to a Dish : 'link [DISH_NAME] / [INGR_NAME]'" + System.lineSeparator()
            + "      Example: 'link chicken rice / chicken'" + System.lineSeparator()
            + "  19. Finding an Ingredient : 'find ingr [keyword]'" + System.lineSeparator()
            + "      Example: 'find ingr chicken'" + System.lineSeparator()
            + "  20. Editing Ingredient Name : 'edit ingr name [INGR_NAME/INDEX]'.\n      Type the [NEW_INGR_NAME] and "
            + "'y' to confirm and 'n' to disregard the change when prompted" + System.lineSeparator()
            + "      Example: 'edit ingr name chicken' | 'chicken breast' | 'y'" + System.lineSeparator()
            + "  21. Editing Ingredient Waste : 'edit ingr waste [INGR_NAME/INDEX]'.\n      "
            + "Type the [NEW_INGR_WASTE_WEIGHT in KG] and "
            + "'y' to confirm and 'n' to disregard the change when prompted" + System.lineSeparator()
            + "      Example: 'edit ingr waste chicken' | '7.0' | 'y'" + System.lineSeparator()
            + "  22. Deleting an Ingredient : 'del [INGR_NAME/INDEX]'\n"
            + "      Type 'y' to confirm and 'n' to disregard the deletion when prompted" + System.lineSeparator()
            + "      Example: 'del ingr chicken' | 'y'" + System.lineSeparator()
            + "  23. Viewing all Ingredients : 'list ingr'" + System.lineSeparator()
            + "  24. Sorting Ingredients according to Ingredient Wastage : 'sort ingr'" + System.lineSeparator()
            + "  25. Viewing Graph of Ingredient Wastage : 'graph ingr'" + System.lineSeparator()
            + "  26. Clearing all Ingredients : 'clear ingr'" + System.lineSeparator()
            + "  27. Clearing both Dishes and Ingredients : 'clear all'" + System.lineSeparator()
            + "  28. Viewing this list of commands: help" + System.lineSeparator()
            + "  29. Exiting the program: bye" + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String EXIT_MESSAGE = EXIT_LOGO + System.lineSeparator()
            + "#       Thank you for using Food-O-Rama to track your food wastage.       #" + System.lineSeparator()
            + "#                Your data has been saved successfully.                   #" + System.lineSeparator()
            + "#                         Have a nice day!                                #" + System.lineSeparator()
            + "#                   Love, the Food-O-Rama Team <3                         #" + System.lineSeparator()
            + "###########################################################################";

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private static final String INVALID_COMMAND = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid command." + System.lineSeparator()
            + "Type 'help' to see a full list of commands with their examples!" + System.lineSeparator()
            + LINE_DIVIDER;


    public static final String INVALID_NUMBER = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, your input is not valid. The command has been disregarded." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String LIST_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, your input is missing a type parameter!" + System.lineSeparator()
            + "Please enter 'list [TYPE]', where [TYPE] is either 'dish' or 'ingr'." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_EXISTS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish already exists in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_NOT_EXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_NAME_MISSING = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the dish name cannot be blank." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_SORT = LINE_DIVIDER + System.lineSeparator()
            + "List of Dishes has been sorted.";

    private static final String DISH_LIST_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Dish list has been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_NAME_MISSING = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the ingredient name cannot be blank." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_INDEX_MISSING = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, your input is missing a parameter!" + System.lineSeparator()
            + "Please type the NAME or INDEX of the Ingredient you would like to edit." + System.lineSeparator()
            + "You can view the Ingredient Index by typing 'list ingr'." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_INDEX_MISSING = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, your input is missing a parameter!" + System.lineSeparator()
            + "Please type the NAME or INDEX of the Dish you would like to edit." + System.lineSeparator()
            + "You can view the Dish Index by typing 'list dish'." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_NOT_EXIST_EDIT = LINE_DIVIDER + System.lineSeparator()
            + "Ingredient does not exist!" + System.lineSeparator()
            + "Please type the correct NAME or INDEX of the Ingredient you would like to edit." + System.lineSeparator()
            + "You can view the Ingredient Index by typing 'list ingr'." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_NOT_EXIST_EDIT = LINE_DIVIDER + System.lineSeparator()
            + "Dish does not exist!" + System.lineSeparator()
            + "Please type the correct NAME or INDEX of the Dish you would like to edit." + System.lineSeparator()
            + "You can view the Dish Index by typing 'list dish'." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_NOT_EXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this ingredient does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;


    private static final String INGR_SORT = LINE_DIVIDER + System.lineSeparator()
            + "List of Ingredients has been sorted.";

    private static final String INGR_LIST_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Ingredient list has been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String ALL_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Both Dish and Ingredient lists have been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String FIND_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Missing keyword parameter, please enter a keyword!" + System.lineSeparator()
            + "Please type `find dish [KEYWORD]` or `find ingr [KEYWORD]`" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String FIND_INVALID_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Incorrect command parameter!" + System.lineSeparator()
            + "Please type `find dish [KEYWORD]` or `find ingr [KEYWORD]`." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String GRAPH_INVALID_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, your input is missing a type parameter!" + System.lineSeparator()
            + "Please enter 'graph [TYPE]', where [TYPE] is either 'dish' or 'ingr'." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INVALID_CONFIRMATION = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid input." + System.lineSeparator()
            + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INVALID_CONFIRMATION_SOFT_LIMIT = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid input. "
            + "Please type 'y/yes' to confirm or 'n/no' to type a new weight:" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INVALID_INGR_NAME = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, Ingredient Name cannot be a number. The command has been disregarded."
            + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INVALID_DISH_NAME = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, Dish Name cannot be a number. The command has been disregarded." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String DISREGARD_MSG = LINE_DIVIDER + System.lineSeparator()
            + "Your previous command is disregarded." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_CLEAR_DISH = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove all Dishes?" + System.lineSeparator()
            + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_CLEAR_ALL = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove all Dishes and Ingredients?" + System.lineSeparator()
            + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_CLEAR_INGR = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove all Ingredients?" + System.lineSeparator()
            + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INGR_INDEX_EXCEED_SIZE = LINE_DIVIDER + System.lineSeparator()
            + "Ingredient Index is not found in the Ingredient List." + System.lineSeparator()
            + "Type 'list ingr' to view the correct Ingredient Index of the Ingredient you want to edit."
            + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String DISH_INDEX_EXCEED_SIZE = LINE_DIVIDER + System.lineSeparator()
            + "Dish Index is not found in the Dish List." + System.lineSeparator()
            + "Type 'list dish' to view the correct Dish Index of the Dish you want to edit." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String EXPIRY_INCORRECT_FORMAT = LINE_DIVIDER + System.lineSeparator()
            + "The expiry date is in the incorrect format." + System.lineSeparator()
            + "Enter an expiry date with the format 'dd/MM/yyyy': " + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String EXPIRY_LONG_DATE = LINE_DIVIDER + System.lineSeparator()
            + "The expiry date is unusually longer than 10 years." + System.lineSeparator()
            + "Are you sure you want to continue saving this expiry date? " + System.lineSeparator()
            + "Please type 'y'/'yes' to confirm, or 'n'/'no' to input a different expiry date: "
            + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String EXPIRY_PASSED_DATE = LINE_DIVIDER + System.lineSeparator()
            + "The expiry date cannot be set to a date before today." + System.lineSeparator()
            + "Please enter a valid expiry date: " + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INVALID_INDEX = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the index given as input must be an integer!" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String LINK_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Your command is missing parameters!" + System.lineSeparator()
            + "Please enter the link command in the format 'link [DISH_NAME] / [INGR_NAME]'." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final int INDEX_OFFSET = 1;
    private static final int STARTING_CHAR_OFFSET = 65;
    private static final int INCREMENTED_STARTING_CHAR_OFFSET = 64;
    private static final int SCALING_OFFSET = 10;
    private static final int ROW_OFFSET = 1;
    private static final int COLUMN_MULTIPLIER = 2;

    public void printLogo() {
        System.out.println(START_LOGO);
    }

    public void printWelcomeMsg() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printExitMsg() {
        System.out.println(EXIT_MESSAGE);
    }

    public String getLineDivider() {
        return LINE_DIVIDER;
    }

    public String getInvalidCommandMsg() {
        return INVALID_COMMAND;
    }

    public String getInvalidNumberMsg() {
        return INVALID_NUMBER;
    }

    public String getListMissingParamMsg() {
        return LIST_MISSING_PARAM;
    }

    public String getDishNameMissingMsg() {
        return DISH_NAME_MISSING;
    }

    public String getDishNotExistMsg(String dishName) {
        assert dishName != null : "dishName cannot be null";
        return LINE_DIVIDER + System.lineSeparator()
                + "Sorry, the dish '" + dishName + "' does not exist in your list." + System.lineSeparator()
                + LINE_DIVIDER;
    }

    public void printDishNotExistMsg() {
        System.out.println(DISH_NOT_EXIST);
    }

    public void printDishExistsMsg() {
        System.out.println(DISH_EXISTS);
    }

    public void printAddedDish(String dishName) {
        assert dishName != null : "dishName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Dish added to list: " + dishName + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printDishNameRemoved(String dishName) {
        assert dishName != null : "dishName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "'" + dishName + "' has been removed from your dish list!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printDishList(ArrayList<Dish> dishList) {
        assert dishList != null : "dishList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Here are the dishes you have:");
        for (int i = 0; i < dishList.size(); i++) {
            System.out.println((i + INDEX_OFFSET) + ". " + dishList.get(i));
        }
        if (dishList.size() == 0) {
            System.out.println("Sorry, there are no Dishes recorded!");
        }
        System.out.println("You can use command 'add' to add new dishes!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printAddedIngredient(Ingredient ingredientToAdd, double ingredientWeight) {
        assert ingredientToAdd != null : "ingredientList cannot be null";
        assert ingredientWeight != -1 : "ingredientWeight cannot be negative";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Ingredient added to list: " + ingredientToAdd.getIngredientName()
                + " (Storage weight: " + ingredientWeight + " kg)" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printIngrList(ArrayList<Ingredient> ingredientList) {
        assert ingredientList != null : "ingredientList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Here are the ingredients you have: ");
        for (int i = 0; i < ingredientList.size(); i++) {
            System.out.println((i + INDEX_OFFSET) + ". " + ingredientList.get(i));
        }
        if (ingredientList.size() == 0) {
            System.out.println("Sorry, there are no Ingredients recorded!");
        }
        System.out.println("You can use command 'add' to add new ingredients!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printIngrNameRemoved(String ingredientName) {
        assert ingredientName != null : "ingredientName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "'" + ingredientName + "' has been removed from your ingredient list!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public String getIngrNameMissingMsg() {
        return INGR_NAME_MISSING;
    }

    public String getIngrIndexMissingMsg() {
        return INGR_INDEX_MISSING;
    }

    public String getDishIndexMissingMsg() {
        return DISH_INDEX_MISSING;
    }

    public String getInvalidIndexMsg() {
        return INVALID_INDEX;
    }

    public String getIngrNotExistEdit() {
        return INGR_NOT_EXIST_EDIT;
    }

    public String getDishNotExistEdit() {
        return DISH_NOT_EXIST_EDIT;
    }

    public String getIngrNotExistMsg() {
        return INGR_NOT_EXIST;
    }

    public String getIngrIndexExceedSizeMsg() {
        return INGR_INDEX_EXCEED_SIZE;
    }

    public String getDishIndexExceedSizeMsg() {
        return DISH_INDEX_EXCEED_SIZE;
    }

    public void printIngrNotExistMsg() {
        System.out.println(INGR_NOT_EXIST);
    }

    public String getDishExistsMsg(String dishName) {
        assert dishName != null : "dishName cannot be null";
        return LINE_DIVIDER + System.lineSeparator()
                + "Sorry, the Dish '" + dishName + "' already exists in your list." + System.lineSeparator()
                + LINE_DIVIDER;
    }

    public String getIngrExistsMsg(String ingrName) {
        assert ingrName != null : "ingrName cannot be null";
        return LINE_DIVIDER + System.lineSeparator()
                + "Sorry, the Ingredient '" + ingrName + "' already exists in your list." + System.lineSeparator()
                + LINE_DIVIDER;
    }

    public static String getHelpMsg() {
        return HELP_ME;
    }

    public void printHelpMsg() {
        System.out.println(HELP_ME);
    }

    public void printDishListCleared() {
        System.out.println(DISH_LIST_CLEARED);
    }

    public void printIngrListCleared() {
        System.out.println(INGR_LIST_CLEARED);
    }

    public void printAllCleared() {
        System.out.println(ALL_CLEARED);
    }

    public void printSortIngrMsg() {
        System.out.println(INGR_SORT);
    }

    public void printSortDishMsg() {
        System.out.println(DISH_SORT);
    }

    public void printConfirmDelDish(String dishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to remove '" + dishName + "'?" + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printConfirmClearDish() {
        System.out.println(CONFIRM_CLEAR_DISH);
    }

    public void printConfirmDelIngr(String ingrName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to remove '" + ingrName + "'?" + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printConfirmClearIngr() {
        System.out.println(CONFIRM_CLEAR_INGR);
    }

    public void printConfirmClearAll() {
        System.out.println(CONFIRM_CLEAR_ALL);
    }

    public void printAskNewNameDish(String dishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "What would you like to change the Dish Name '" + dishName + "' to?"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printAskNewWastageDish(String dishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "What would you like to change the wastage weight (in kg) of '" + dishName + "' to?"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printAskNewStorageIngr(String ingrName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "What would you like to change the storage weight (in kg) of '" + ingrName + "' to?"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printConfirmDishNameEditMsg(String dishName, String newName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to change Dish Name from '" + dishName + "' to '" + newName + "'?"
                + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printConfirmDishWastageEditMsg(Double currWastage, Double newWastage) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to change the wastage weight (in kg) from '" + currWastage + "' to '"
                + newWastage + "'?" + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printConfirmIngrStorageEditMsg(Double currStorage, Double newStorage) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to change the storage weight (in kg) from '" + currStorage + "' to '"
                + newStorage + "'?" + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printDishNameChanged(String dishName, String newName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "'" + dishName + "' has been updated to '" + newName + "'!"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printDishWastageChanged(String dishName, Double newWastage) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Wastage of '" + dishName + "' has been updated to " + newWastage + " kg!"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printIngrStorageChanged(String dishName, Double newStorage) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Storage of '" + dishName + "' has been updated to " + newStorage + " kg!"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printAskNewNameIngr(String ingrName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "What would you like to change the Ingredient Name '" + ingrName
                + "' to?" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printConfirmIngrEditMsg(String ingrName, String newName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to change the Ingredient name from '" + ingrName + "' to '"
                + newName + "'?" + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printIngrNameChanged(String ingrName, String newName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "'" + ingrName + "' has been updated to '" + newName + "'!"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printAskIngrExpiryDate(String ingrName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "What is the expiry date of '" + ingrName + "' in 'dd/MM/yyyy' format?"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printSetIngrExpiryDate(String ingrName, LocalDate date, long daysAway) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The expiry date of '" + ingrName + "' has been set to " + date.format(dtf)
                + " (" + daysAway + " day(s) from today)"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public String getIncorrectExpiryDateFormatMsg() {
        return EXPIRY_INCORRECT_FORMAT;
    }

    public void printIncorrectExpiryDateFormatMsg() {
        System.out.println(EXPIRY_INCORRECT_FORMAT);
    }

    public void printLongExpiryDateMsg() {
        System.out.println(EXPIRY_LONG_DATE);
    }

    public void printPassedExpiryDateMsg() {
        System.out.println(EXPIRY_PASSED_DATE);
    }

    public void printDisregardMsg() {
        System.out.println(DISREGARD_MSG);
    }

    public void printInvalidConfirmation() {
        System.out.println(INVALID_CONFIRMATION);
    }

    public void printInvalidConfirmationSoftLimit() {
        System.out.println(INVALID_CONFIRMATION_SOFT_LIMIT);
    }

    public void printInvalidIngredientName() {
        System.out.println(INVALID_INGR_NAME);
    }

    public void printInvalidDishName() {
        System.out.println(INVALID_DISH_NAME);
    }

    public String getInvalidIngredientName() {
        return INVALID_INGR_NAME;
    }

    public String getInvalidDishName() {
        return INVALID_DISH_NAME;
    }

    /**
     * Prints the bars for the graph given the coordinates of a particular point in the grid.
     *
     * @param graphPortions maximum number of bars in the graph
     * @param lengths       collection of the lengths of all the bars in the graph
     * @param i             y coordinate of the point
     * @param j             x coordinate of the point
     * @author Dniv-ra
     */
    private void printGraph(int graphPortions, ArrayList<Double> lengths, int i, int j) {
        if (i < graphPortions) {
            printBars(graphPortions, i, j, lengths.get(j / 2));
        } else if (i == graphPortions) {
            printAxis(j);
        } else {
            System.out.print("   ");
        }
    }

    /**
     * Prints the X axis for the graph.
     *
     * @param j x coordinate of the point
     * @author Dniv-ra
     */
    private void printAxis(int j) {
        if (j % 2 == 0) {
            System.out.print("   ");
        } else {
            System.out.print(" " + (char) (j / 2 + STARTING_CHAR_OFFSET) + " ");
        }
    }

    /**
     * Prints the bars for the graph given the coordinates of a particular point in the grid.
     *
     * @param graphPortions maximum number of bars in the graph
     * @param i             y coordinate of the point
     * @param j             x coordinate of the point
     * @param length        length of the j/2th object
     * @author Dniv-ra
     */
    private void printBars(int graphPortions, int i, int j, double length) {
        int integralPart = (int) Math.floor(length);
        double fractionalPart = length - integralPart;
        if (j % 2 == 0) {
            System.out.print("   "); //Every other column is blank
        } else if (i >= graphPortions - INDEX_OFFSET - integralPart) {
            if (i == graphPortions - INDEX_OFFSET - integralPart) {
                if (fractionalPart == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print("[" + (int) (fractionalPart * SCALING_OFFSET) + "]");
                }
            } else {
                System.out.print("[|]");
            }
        } else {
            System.out.print("   ");
        }
    }

    /**
     * Prints the wastage of an array of dishes in a graphical format.
     *
     * @param dishList list of dishes to be converted into a bar graph
     * @author Dniv-ra
     */
    public void printDishListGraph(ArrayList<Dish> dishList) {
        assert dishList != null : "dishList cannot be null";
        int graphPortions = 10;
        if (!dishList.isEmpty()) {
            System.out.println(LINE_DIVIDER + System.lineSeparator());
            //Get the n values for the dishes
            ArrayList<Double> lengths = new ArrayList<>();
            double max = DishList.getGreatestWaste();
            for (int i = 0; i < dishList.size(); i++) {
                lengths.add(dishList.get(i).getGraphHeight(max, graphPortions));
            }
            //2d visualization
            //Nested for 2n columns, 10 rows (n rows but only 10 will be for graph rest for legend)
            int listSize = dishList.size();
            int rows = (listSize > graphPortions) ? listSize + ROW_OFFSET : graphPortions + ROW_OFFSET;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < listSize * COLUMN_MULTIPLIER; j++) {
                    printGraph(graphPortions, lengths, i, j);
                }
                if (i <= listSize) {
                    printDishLegend(dishList, i);
                } else {
                    System.out.print(System.lineSeparator());
                }
            }
            System.out.println(LINE_DIVIDER);
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("Sorry, there are no dishes to graph!");
            System.out.println(LINE_DIVIDER);
        }
    }

    /**
     * Prints the legend and the scale at the side of the graph.
     *
     * @param dishList list of dishes to be used for the legend
     * @param i        current row in the grid
     * @author Dniv-ra
     */
    private void printDishLegend(ArrayList<Dish> dishList, int i) {
        if (i == 0) {
            System.out.print("     Legend:         ");
            System.out.print("     Scale: 1 unit [|] = " + DishList.getGreatestWaste() / SCALING_OFFSET + "kg");
        } else {
            System.out.print("     " + (char) (i + INCREMENTED_STARTING_CHAR_OFFSET) + ". "
                    + dishList.get(i - INDEX_OFFSET).getDishName()
                    + ": " + dishList.get(i - INDEX_OFFSET).getWastage() + "kg");
        }
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints the wastage of an array of ingredients in a graphical format.
     *
     * @param ingredientList list of ingredients to be converted into a bar graph
     * @author Dniv-ra
     */
    public void printIngrListGraph(ArrayList<Ingredient> ingredientList) {
        assert ingredientList != null : "ingredientList cannot be null";
        int graphPortions = 10;
        if (!ingredientList.isEmpty()) {
            System.out.println(LINE_DIVIDER + System.lineSeparator());
            //Get the n values for the ingredients
            ArrayList<Double> lengths = new ArrayList<>();
            double max = IngredientList.getGreatestWaste();
            for (int i = 0; i < ingredientList.size(); i++) {
                lengths.add(ingredientList.get(i).getGraphHeight(max, graphPortions));
            }
            //2d visualization
            //Nested for 2n columns, 10 rows (n rows but only 10 will be for graph rest for legend)
            int listSize = ingredientList.size();
            int rows = (listSize > graphPortions) ? listSize + ROW_OFFSET : graphPortions + ROW_OFFSET;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < listSize * COLUMN_MULTIPLIER; j++) {
                    printGraph(graphPortions, lengths, i, j);
                }
                if (i <= listSize) {
                    printIngrLegend(ingredientList, i);
                } else {
                    System.out.print(System.lineSeparator());
                }
            }
            System.out.println(LINE_DIVIDER);
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("Sorry, there are no ingredients to graph!");
            System.out.println(LINE_DIVIDER);
        }
    }

    /**
     * Prints the legend and the scale at the side of the graph.
     *
     * @param ingredientList list of ingredients to be used for the legend
     * @param i              current row in the grid
     * @author Dniv-ra
     */
    private void printIngrLegend(ArrayList<Ingredient> ingredientList, int i) {
        //Scale on first line
        if (i == 0) {
            System.out.print("     Legend:         ");
            System.out.print("     Scale: 1 unit = " + IngredientList.getGreatestWaste() / SCALING_OFFSET + "kg");
        } else {
            System.out.print("     " + (char) (i + INCREMENTED_STARTING_CHAR_OFFSET) + ". "
                    + ingredientList.get(i - INDEX_OFFSET).getIngredientName()
                    + ": " + ingredientList.get(i - INDEX_OFFSET).getWastage() + "kg");
        }
        System.out.print(System.lineSeparator());
    }

    public void printMatchedDishes(ArrayList<Dish> matchedDishList) {
        assert matchedDishList != null : "matchedDishList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "As requested, here are the matching dishes in your list:");
        if (DishList.dishList.size() == 0) {
            System.out.println("Sorry, there are no Dishes recorded!");
        } else if (matchedDishList.size() == 0) {
            System.out.println("Sorry, the keyword cannot be found!");
        } else {
            int dishListIndex;
            for (Dish element : matchedDishList) {
                dishListIndex = DishList.dishList.indexOf(element);
                System.out.println(dishListIndex + INDEX_OFFSET + ". " + element.toString());
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Prints the list of ingredients that match the KEYWORD input by the user in the find command.
     *
     * @param matchedIngrList the list of ingredients that match the KEYWORD
     * @author renzocanare
     */
    public void printMatchedIngredients(ArrayList<Ingredient> matchedIngrList) {
        assert matchedIngrList != null : "matchedIngrList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "As requested, here are the matching ingredients in your list:");
        if (IngredientList.ingredientList.size() == 0) {
            System.out.println("Sorry, there are no Ingredients recorded!");
        } else if (matchedIngrList.size() == 0) {
            System.out.println("Sorry, the keyword cannot be found!");
        } else {
            int ingrListIndex;
            for (Ingredient element : matchedIngrList) {
                ingrListIndex = IngredientList.ingredientList.indexOf(element);
                System.out.println(ingrListIndex + INDEX_OFFSET + ". " + element.toString());
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    public void printAddedPartOf(String ingredientName, String dishName) {
        assert ingredientName != null : "ingredientName cannot be null";
        assert dishName != null : "dishName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Successfully added '" + ingredientName + "' as an ingredient of '" + dishName + "'!"
                + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printEnterStoredWeightOf(String name) {
        assert name != null : "name cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Please enter the storage weight of '" + name + "' in kg:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printEnterWasteWeightOf(String name) {
        assert name != null : "name cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Please enter the wastage weight of '" + name + "' in kg:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printEnterWeightOf(String name) {
        assert name != null : "name cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Please enter the weight of '" + name + "' in kg:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printEnterLimitFor(String name) {
        assert name != null : "name cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Please enter the limit for '" + name + "' in kg:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public String getMissingParameters() {
        return LINK_MISSING_PARAM;
    }

    public String getFindMissingParamMsg() {
        return FIND_MISSING_PARAM;
    }

    public String getFindInvalidParamMsg() {
        return FIND_INVALID_PARAM;
    }

    public String getGraphInvalidParamMsg() {
        return GRAPH_INVALID_PARAM;
    }

    public void printWastage(String name, double wastage) {
        assert name != null : "name cannot be null";
        assert wastage != -1 : "wastage cannot be negative";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Wastage of '" + name + "' has been added and is now " + wastage + " kg!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printStorage(String name, double storage) {
        assert name != null : "name cannot be null";
        assert storage != -1 : "storage cannot be negative";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Storage of '" + name + "' has been added and is now " + storage + " kg!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void clearTerminalAndPrintNewPage() {
        ClearScreen.clearConsole();
        System.out.println(NEWPAGE_MESSAGE);
    }

    public void printLimitExceeded(String input) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Wastage of '" + input + "' has exceeded the limit!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printLimitSet(String name, double limit) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The limit for '" + name + "' is now " + limit + " kg!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printExcessParamMsg(String name) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Warning: The command '" + name + "' does not need any excess parameters!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public String getFormatMessage() {
        String dishFormat = "Dish Format: [Name] | [Amount wasted (kg)] | "
                + "[Wastage divided by number of constituents] "
                + "| [Wastage limit (if present else -1)] | [ingredient 1|ingredient 2|etc]";
        String dishExample = "Example (no constituents, no limit): prata|2.0|2.0|-1" + System.lineSeparator()
                + "Example (2 constituents, no limit): prata|2.0|1.0|-1|flour|egg" + System.lineSeparator()
                + "Example (2 constituents, limit of 3): prata|2.0|1.0|3|flour|egg";
        String ingrFormat = "Ingredients: [Name] | [Amount stored (kg)] | [Amount wasted (kg)] | "
                + "[Wastage limit (if present else -1)] | [Expiry Date in format dd/MM/yyyy (if set)]";
        String ingrExample = "Example (no limit, expiry not set): chicken|2.33|1.0|-1|null" + System.lineSeparator()
                + "Example (limit of 2.5, expiry set): chicken|2.33|1.0|2.5|30/10/2021";
        return dishFormat + System.lineSeparator()
                + dishExample + System.lineSeparator()
                + ingrFormat + System.lineSeparator()
                + ingrExample + System.lineSeparator();
    }

    public void printRandomDishName(String randomDishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Here's an idea for a new Dish!" + System.lineSeparator()
                + randomDishName + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printInvalidIngrWeight(String ingredientName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The weight of '" + ingredientName + "' cannot be negative!" + System.lineSeparator()
                + "Please enter a weight that is not negative:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printInvalidIngrWasteValue(String ingredientName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The weight of '" + ingredientName + "' wasted cannot be negative!" + System.lineSeparator()
                + "Please enter a weight that is not negative:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printInvalidUpdateIngrValue(String ingredientName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The weight of '" + ingredientName + "' to be stored cannot be negative!" + System.lineSeparator()
                + "Please enter a weight that is not negative:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printInvalidIngrLimitValue(String ingredientName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The limit of '" + ingredientName + "'  cannot be negative!" + System.lineSeparator()
                + "Please enter a weight that is not negative:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printInvalidDishWasteValue(String dishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The weight of '" + dishName + "' wasted cannot be negative!" + System.lineSeparator()
                + "Please enter a weight that is not negative:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printDishWasteValueHigh(String dishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The weight of '" + dishName + "' wasted is unusually greater than 10,000kg!" + System.lineSeparator()
                + "Are you sure you want to add this weight?" + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to type a new weight:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printIngrValueHigh(String ingrName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The weight of '" + ingrName + "' is unusually greater than 10,000kg!" + System.lineSeparator()
                + "Are you sure you want to add this weight?" + System.lineSeparator()
                + "Enter 'y' to continue, or 'n' to type a new weight" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printIngrWasteValueHigh(String ingrName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The weight of '" + ingrName + "' wasted is unusually greater than 10,000kg!" + System.lineSeparator()
                + "Are you sure you want to add this weight?" + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to type a new weight:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public String printNumericalInputInvalid(String type) {
        return LINE_DIVIDER + System.lineSeparator()
                + "The " + type + " is out of bounds. The command has been disregarded."
                + System.lineSeparator() + LINE_DIVIDER;
    }

    public void printInvalidDishLimitValue(String dishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The limit of '" + dishName + "'  cannot be negative!" + System.lineSeparator()
                + "Please enter a weight that is not negative:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printConfirmDishWaste(String dishName, double dishWasteWeight) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to add wasted '" + dishName + "' that weighs " + dishWasteWeight + "kg?"
                + System.lineSeparator()
                + "Please type 'y'/'yes' to confirm, or 'n'/'no' to disregard:" + System.lineSeparator()
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public String getBlankName(String type) {
        return LINE_DIVIDER + System.lineSeparator()
                + "Sorry, the " + type + " name cannot be blank! The command has been disregarded."
                + System.lineSeparator() + LINE_DIVIDER;
    }

}
