package seedu.foodorama;

import java.util.ArrayList;

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

    private static final String WELCOME_MESSSAGE = START_LOGO + System.lineSeparator()
            + "#             Hello, welcome to FOOD-O-RAMA! "
            + "The number one solution for food waste management.                #" + System.lineSeparator()
            + "#                  Type a command to get us sta"
            + "rted, or type help for a list of commands!                      #" + System.lineSeparator()
            + "###########################################"
            + "#####################################################################";

    private static final String NEWPAGE_MESSSAGE = START_LOGO + System.lineSeparator()
            + "#                                            SUMMARY OF COMMANDS:                                  "
            + "            #" + System.lineSeparator()
            + "# 1. Adding a Dish : 'add dish [dishName]'                                                         "
            + "            #" + System.lineSeparator()
            + "# 2. Adding Dish Wastage : 'add dish waste [dishName]' followed by '[weight in KG]'                "
            + "            #" + System.lineSeparator()
            + "# 3. Adding Dish Limit : 'set dish limit [dishName]' followed by '[weight in KG]'                  "
            + "            #" + System.lineSeparator()
            + "# 4. Deleting a Dish : 'del dish [dishName]                                                        "
            + "            #" + System.lineSeparator()
            + "# 5. Adding a new Ingredient : 'add ingr [ingrName]' followed by '[weight in KG]'                  "
            + "            #" + System.lineSeparator()
            + "# 6. Adding storage to an already existing Ingredient : 'add ingr stored [ingrName]'               "
            + "            #" + System.lineSeparator()
            + "#    followed by '[weight in KG]'                                                                  "
            + "            #" + System.lineSeparator()
            + "# 7. Adding Ingredient Wastage : 'add ingr waste [ingrName]' followed by '[weight in KG]'          "
            + "            #" + System.lineSeparator()
            + "# 8. Adding Ingredient Limit : 'set ingr limit [ingrName]' followed by '[weight in KG]'            "
            + "            #" + System.lineSeparator()
            + "# 9. Linking an Ingredient to a Dish : 'link [dishName] / [ingrName]'                              "
            + "            #" + System.lineSeparator()
            + "# 10. Deleting an Ingredient : 'del ingr [ingrName]'                                               "
            + "            #" + System.lineSeparator()
            + "# 11. Find a Dish/Ingredient : 'find dish [KEYWORD] | find ingr [KEYWORD]'                         "
            + "            #" + System.lineSeparator()
            + "# 12. Viewing all Dishes/Ingredients : 'list dish | list ingr'                                     "
            + "            #" + System.lineSeparator()
            + "# 13. View graph of all Dishes/Ingredients : 'graph dish | graph ingr'                             "
            + "            #" + System.lineSeparator()
            + "# 14. Sort list of Dishes/Ingredients : 'sort dish | sort ingr'                                    "
            + "            #" + System.lineSeparator()
            + "# 15. Clearing saved lists: 'clear dish' | 'clear ingr' | 'clear all'                              "
            + "            #" + System.lineSeparator()
            + "# 16. View full list of commands and examples: 'help'                                              "
            + "            #" + System.lineSeparator()
            + "# 17. Exiting the program: 'bye'                                                                   "
            + "            #" + System.lineSeparator()
            + "###################################################################################################"
            + "#############" + System.lineSeparator()
            + "#                              For a full, detailed list of commands, type 'help'                  "
            + "            #" + System.lineSeparator()
            + "###################################################################################################"
            + "#############";

    protected static final String HELP_ME = LINE_DIVIDER + System.lineSeparator()
            + "Here are the commands you can use:" + System.lineSeparator()
            + "1. Adding a Dish : 'add dish [DISH_NAME]'" + System.lineSeparator()
            + "    Example: 'add dish chicken rice'" + System.lineSeparator()
            + "2. Adding Dish Wastage : 'add dish waste [DISH_NAME]' followed by "
            + "'[weight in KG]'" + System.lineSeparator()
            + "    Example: 'add dish waste chicken rice' ; '0.8'" + System.lineSeparator()
            + "3. Adding Dish Limit : 'set dish limit [DISH_NAME]' followed by "
            + "'[weight in KG]'" + System.lineSeparator()
            + "    Example: 'set dish limit chicken rice' ; '16.9'" + System.lineSeparator()
            + "4. Finding a Dish : 'find dish [keyword]'" + System.lineSeparator()
            + "    Example: 'find dish chicken rice'" + System.lineSeparator()
            + "5. Deleting a Dish : 'del [DISH_NAME]" + System.lineSeparator()
            + "    Example: 'del dish chicken rice'" + System.lineSeparator()
            + "6. Viewing all Dishes : 'list dish'" + System.lineSeparator()
            + "7. Sorting Dishes according to Dish Wastage : 'sort dish'" + System.lineSeparator()
            + "8. Viewing Graph of Dish Wastage : 'graph dish'" + System.lineSeparator()
            + "9. Clearing all Dishes : 'clear dish'" + System.lineSeparator()
            + "10. Adding a new Ingredient : 'add ingr [INGR_NAME]' "
            + "followed by '[weight in KG]'" + System.lineSeparator()
            + "    Example: 'add ingr chicken' ; '2'" + System.lineSeparator()
            + "11. Adding storage to an already existing Ingredient : 'add ingr stored [INGR_NAME]'"
            + " followed by '[weight in KG]'" + System.lineSeparator()
            + "    Example: 'add ingr stored chicken' ; '1.5'" + System.lineSeparator()
            + "12. Adding Ingredient Wastage : 'add ingr waste [INGR_NAME]' followed by "
            + "'[weight in KG]'" + System.lineSeparator()
            + "    Example: 'add ingr waste chicken' ; '0.7'" + System.lineSeparator()
            + "13. Adding Ingredient Limit : 'set ingr limit [INGR_NAME]' followed by "
            + "'[weight in KG]'" + System.lineSeparator()
            + "    Example: 'set ingr limit chicken' ; '42.7'" + System.lineSeparator()
            + "14. Linking an Ingredient to a Dish : 'link [DISH_NAME] / [INGR_NAME]'" + System.lineSeparator()
            + "    Example: 'link chicken rice / chicken'" + System.lineSeparator()
            + "15. Finding an Ingredient : 'find ingr [keyword]'" + System.lineSeparator()
            + "    Example: 'find ingr chicken'" + System.lineSeparator()
            + "16. Deleting an Ingredient : 'del [INGR_NAME]'" + System.lineSeparator()
            + "    Example: 'del ingr chicken'" + System.lineSeparator()
            + "17. Viewing all Ingredients : 'list ingr'" + System.lineSeparator()
            + "18. Sorting Ingredients according to Ingredient Wastage : 'sort ingr'" + System.lineSeparator()
            + "19. Viewing Graph of Ingredient Wastage : 'graph ingr'" + System.lineSeparator()
            + "20. Clearing all Ingredients : 'clear ingr'" + System.lineSeparator()
            + "21. Clearing both Dishes and Ingredients : 'clear all'" + System.lineSeparator()
            + "22. Viewing this list of commands: help" + System.lineSeparator()
            + "23. Exiting the program: bye" + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String EXIT_MESSAGE = EXIT_LOGO + System.lineSeparator()
            + "#       Thank you for using Food-O-Rama to track your food wastage.       #" + System.lineSeparator()
            + "#                Your data has been saved successfully.                   #" + System.lineSeparator()
            + "#                         Have a nice day!                                #" + System.lineSeparator()
            + "#                   Love, the Food-O-Rama Team <3                         #" + System.lineSeparator()
            + "###########################################################################";

    private static final String INVALID_COMMAND = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid command." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INVALID_PARAMETERS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, please input a valid parameter." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INVALID_NUMBER = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, please input a valid number" + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String LIST_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, please input: list [TYPE]." + System.lineSeparator()
            + "[TYPE]: dish to list dishes, ingr to list ingredients." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_EXISTS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish already exists in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_NOTEXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISHNAME_MISSING = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the dish name cannot be blank." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_SORT = LINE_DIVIDER + System.lineSeparator()
            + "List of Dishes has been sorted." + System.lineSeparator();

    private static final String DISH_LIST_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Dish list has been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGRNAME_MISSING = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the ingredient name cannot be blank." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_NOTEXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this ingredient does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_EXISTS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this ingredient already exists in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_SORT = LINE_DIVIDER + System.lineSeparator()
            + "List of Ingredients has been sorted." + System.lineSeparator();

    private static final String INGR_LIST_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Ingredient list has been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String ALL_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Both Dish and Ingredient lists have been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String FIND_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Please enter a keyword!" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String FIND_INVALID_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Incorrect paramater! Please type find <dish/ingredient>" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String GRAPH_INVALID_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, please input: graph [TYPE]." + System.lineSeparator()
            + "[TYPE]: dish to list dishes, ingr to list ingredients." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INVALID_CONFIRMATION = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid command. Please type y to confirm or n to disregard" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String DISREGARD_MSG = LINE_DIVIDER + System.lineSeparator()
            + "Your previous command is disregarded." + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_DEL_DISH = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove this Dish? Type y to confirm or n to disregard" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_CLEAR_DISH = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove all Dishes? Type y to confirm or n to disregard" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_CLEAR_ALL = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove all Dishes and Ingredients? "
            + "Type y to confirm or n to disregard" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_DEL_INGR = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove this Ingredient? Type y to confirm or n to disregard"
            + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String CONFIRM_CLEAR_INGR = LINE_DIVIDER + System.lineSeparator()
            + "Are you sure you want to remove all Ingredients? Type y to confirm or n to disregard"
            + System.lineSeparator()
            + LINE_DIVIDER;

    public static final String INGR_INDEX_EXCEED_SIZE = LINE_DIVIDER + System.lineSeparator()
            + "Ingredient Index exceeds size of Ingredient List. Type 'list ingr' to view the correct"
            + " Ingredient Index of the Ingredient you want to edit."
            + System.lineSeparator()
            + LINE_DIVIDER;



    public void printLogo() {
        System.out.println(START_LOGO);
    }

    public void printWelcomeMsg() {
        System.out.println(WELCOME_MESSSAGE);
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
        return DISHNAME_MISSING;
    }

    public String getDishNotExistMsg(String dishName) {
        assert dishName != null : "dishName cannot be null";
        return LINE_DIVIDER + System.lineSeparator()
                + "Sorry, the dish " + dishName + " does not exist in your list." + System.lineSeparator()
                + LINE_DIVIDER;
    }


    public void printDishNotExistMsg() {
        System.out.println(DISH_NOTEXIST);
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
                + dishName + " has been removed!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printDishList(ArrayList<Dish> dishList) {
        assert dishList != null : "dishList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Here are the dishes you have:");
        for (int i = 0; i < dishList.size(); i++) {
            System.out.println((i + 1) + ". " + dishList.get(i));
        }
        System.out.println("You can use command 'add' to add new dishes!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printAddedIngredient(Ingredient ingredientToAdd, double ingredientWeight) {
        assert ingredientToAdd != null : "ingredientList cannot be null";
        assert ingredientWeight != -1 : "ingredientWeight cannot be negative";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Ingredient added to list: " + ingredientToAdd.getIngredientName()
                + " (Weight: " + ingredientWeight + " kg)" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printIngrList(ArrayList<Ingredient> ingredientList) {
        assert ingredientList != null : "ingredientList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Here are the ingredients you have: ");
        for (int i = 0; i < ingredientList.size(); i++) {
            System.out.println((i + 1) + ". " + ingredientList.get(i));
        }
        System.out.println("You can use command 'add' to add new ingredients!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printIngrNameRemoved(String ingredientName) {
        assert ingredientName != null : "ingredientName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + ingredientName + " has been removed!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public String getIngrNameMissingMsg() {
        return INGRNAME_MISSING;
    }

    public String getIngrNotExistMsg() {
        return INGR_NOTEXIST;
    }

    public String getIngrIndexExceedSizeMsg() {
        return INGR_INDEX_EXCEED_SIZE;
    }

    public void printIngrNotExistMsg() {
        System.out.println(INGR_NOTEXIST);
    }

    public String getIngrExistsMsg(String ingrName) {
        assert ingrName != null : "ingrName cannot be null";
        return LINE_DIVIDER + System.lineSeparator()
                + "Sorry, the ingredient" + ingrName + "already exists in your list." + System.lineSeparator()
                + LINE_DIVIDER;
    }

    public void printInvalidParamMsg() {
        System.out.println(INVALID_PARAMETERS);
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

    public void printConfirmDelDish() {
        System.out.println(CONFIRM_DEL_DISH);
    }

    public void printConfirmClearDish() {
        System.out.println(CONFIRM_CLEAR_DISH);
    }

    public void printConfirmDelIngr() {
        System.out.println(CONFIRM_DEL_INGR);
    }

    public void printConfirmClearIngr() {
        System.out.println(CONFIRM_CLEAR_INGR);
    }

    public void printConfirmClearAll() {
        System.out.println(CONFIRM_CLEAR_ALL);
    }

//    public void printAskNewNameDish(String dishName) {
//        System.out.println("What would you like to change the Dish Name '"
//                + dishName
//                + "' to?");
//    }
//
//    public void printAskNewNameDish(String dishName) {
//        System.out.println("What would you like to change the Ingredient Name '"
//                + dishName
//                + "' to?");
//    }

    public void printDishNameChanged(String dishName) {
        System.out.println("Dish Name has been updated to '"
                + dishName
                + "'!");
    }

    public void printAskNewNameIngr(String ingrName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "What would you like to change the Ingredient Name '" + ingrName
                + "' to?" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printConfirmIngrEditMsg(String ingrName, String newName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Are you sure you want to change ingredient name from '" + ingrName + "' to '"
                + newName + "'?" + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printIngrNameChanged(String ingrName) {
        System.out.println(LINE_DIVIDER+ System.lineSeparator()
                + "Ingredient Name has been updated to '" + ingrName + "'!"
                + System.lineSeparator() + LINE_DIVIDER);
    }

    public void printDisregardMsg() {
        System.out.println(DISREGARD_MSG);
    }

    public void printInvalidConfirmation() {
        System.out.println(INVALID_CONFIRMATION);
    }

    public void printDishListGraph(ArrayList<Dish> dishList) {
        assert dishList != null : "dishList cannot be null";
        int graphPortions = 10;
        if (!dishList.isEmpty()) {
            System.out.println(LINE_DIVIDER + System.lineSeparator());
            //Get the n values for the dishes
            ArrayList<Integer> lengths = new ArrayList();
            double max = DishList.getGreatestWaste();
            for (int i = 0; i < dishList.size(); i++) {
                lengths.add(dishList.get(i).getGraphHeight(max, graphPortions));
            }
            //2d visualization
            //Nested for 2n columns, 10 rows (n rows but only 10 will be for graph rest for legend)
            int listSize = dishList.size();
            int rows = (listSize > graphPortions) ? listSize : graphPortions + 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < listSize * 2; j++) {
                    if (i < graphPortions) {
                        if (j % 2 == 0) {
                            System.out.print("  "); //Every other column is blank
                        } else if (i > graphPortions - 1 - lengths.get(j / 2)) {
                            System.out.print("[|]");
                        } else {
                            System.out.print("   ");
                        }
                    } else if (i == graphPortions) {
                        if (j % 2 == 0) {
                            System.out.print("   ");
                        } else {
                            System.out.print((char)(j / 2 + 65) + " ");
                        }
                    } else {
                        System.out.print("   ");
                    }
                }
                if (i < listSize) {
                    System.out.println("     " + (char) (i + 65) + ". " + dishList.get(i).getDishName()
                        + ":" + dishList.get(i).getWastage() + "kg");
                } else {
                    System.out.print(System.lineSeparator());
                }
            }
            System.out.println(LINE_DIVIDER);
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("There are no dishes to graph");
            System.out.println(LINE_DIVIDER);
        }
    }

    public void printIngrListGraph(ArrayList<Ingredient> ingredientList) {
        assert ingredientList != null : "ingredientList cannot be null";
        int graphPortions = 10;
        if (!ingredientList.isEmpty()) {
            System.out.println(LINE_DIVIDER + System.lineSeparator());
            //Get the n values for the ingredients
            ArrayList<Integer> lengths = new ArrayList();
            double max = IngredientList.getGreatestWaste();
            for (int i = 0; i < ingredientList.size(); i++) {
                lengths.add(ingredientList.get(i).getGraphHeight(max, graphPortions));
            }
            //2d visualization
            //Nested for 2n columns, 10 rows (n rows but only 10 will be for graph rest for legend)
            int listSize = ingredientList.size();
            int rows = (listSize > graphPortions) ? listSize : graphPortions + 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < listSize * 2; j++) {
                    if (i < graphPortions) {
                        if (j % 2 == 0) {
                            System.out.print("  "); //Every other column is blank
                        } else if (i > graphPortions - 1 - lengths.get(j / 2)) {
                            System.out.print("[|]");
                        } else {
                            System.out.print("   ");
                        }
                    } else if (i == graphPortions) {
                        if (j % 2 == 0) {
                            System.out.print("   ");
                        } else {
                            System.out.print((char)(j / 2 + 65) + " ");
                        }
                    } else {
                        System.out.print("   ");
                    }
                }
                if (i < listSize) {
                    System.out.println("     " + (char) (i + 65) + ". " + ingredientList.get(i).getIngredientName()
                            + ":" + ingredientList.get(i).getWastage() + "kg");
                } else {
                    System.out.print(System.lineSeparator());
                }
            }
            System.out.println(LINE_DIVIDER);
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("There are no ingredients to graph");
            System.out.println(LINE_DIVIDER);
        }
    }

    public void printMatchedDishes(ArrayList<Dish> matchedDishList) {
        assert matchedDishList != null : "matchedDishList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "As requested, here are the matching dishes in your list:");
        if (DishList.dishList.size() == 0) {
            System.out.println("There are no dishes recorded!");
        } else if (matchedDishList.size() == 0) {
            System.out.println("The keyword cannot be found!");
        } else {
            int taskCounter = 0;
            for (Dish element : matchedDishList) {
                System.out.println(taskCounter + 1 + ". " + element.toString());
                taskCounter++;
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    public void printMatchedIngredients(ArrayList<Ingredient> matchedIngrList) {
        assert matchedIngrList != null : "matchedIngrList cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "As requested, here are the matching ingredients in your list:");
        if (IngredientList.ingredientList.size() == 0) {
            System.out.println("There are no ingredients recorded!");
        } else if (matchedIngrList.size() == 0) {
            System.out.println("The keyword cannot be found!");
        } else {
            int taskCounter = 0;
            for (Ingredient element : matchedIngrList) {
                System.out.println(taskCounter + 1 + ". " + element.toString());
                taskCounter++;
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    public void printAddedPartOf(String ingredientName, String dishName) {
        assert ingredientName != null : "ingredientName cannot be null";
        assert dishName != null : "dishName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Successfully added " + ingredientName + " as ingredient of " + dishName + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printEnterWeightOf(String name) {
        assert name != null : "name cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Enter the weight of " + name + " in kg:" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printEnterLimitFor(String name) {
        assert name != null : "name cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Enter the limit for " + name + " in kg:" + System.lineSeparator()
                + LINE_DIVIDER);
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
                + "Wastage of " + name + " is now " + wastage + " kg" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printStorage(String name, double storage) {
        assert name != null : "name cannot be null";
        assert storage != -1 : "storage cannot be negative";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Storage of " + name + " is now " + storage + " kg" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void clearTerminalAndPrintNewPage() {
        ClearScreen.clearConsole();
        System.out.println(NEWPAGE_MESSSAGE);
    }

    public void printLimitExceeded(String input) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Wastage of " + input + " has exceeded the limit" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printLimitSet(String name, double limit) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "The limit for " + name + " is now " + limit + " kg" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printExcessParamMsg(String name) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Warning: The command " + name + " does not need any excess parameters" + System.lineSeparator()
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
                + "[Wastage limit (if present else -1)]";
        String ingrExample = "Example (no limit): chicken|2.33|1.0|-1" + System.lineSeparator()
                + "Example (limit of 2.5): chicken|2.33|1.0|2.5";
        return dishFormat + System.lineSeparator()
                + dishExample + System.lineSeparator()
                + ingrFormat + System.lineSeparator()
                + ingrExample + System.lineSeparator();
    }
}
