package seedu.duke;

import java.util.ArrayList;

public class Ui {
    //Todo help
    private static final String LINE_DIVIDER = "____________________________________________________________";
    private static final String START_LOGO =
            "######################################################"
            + "##########################################################\n"
            + "#  ________  ______    ______   _______            ___"
            + "___           _______    ______   __       __   ______   #\n"
            + "# /        |/      \\  /      \\ /       \\          / "
            + "     \\         /       \\  /      \\ /  \\     /  | /      \\  #\n"
            + "# $$$$$$$$//$$$$$$  |/$$$$$$  |$$$$$$$  |        /$$$$$$  "
            + "|        $$$$$$$  |/$$$$$$  |$$  \\   /$$ |/$$$$$$  | #\n"
            + "# $$ |__   $$ |  $$ |$$ |  $$ |$$ |  $$ | ______ $$$  \\$"
            + "$ | ______ $$ |__$$ |$$ |__$$ |$$$  \\ /$$$ |$$ |__$$ | #\n"
            + "# $$    |  $$ |  $$ |$$ |  $$ |$$ |  $$ |/      |$$$$  $$"
            + " |/      |$$    $$< $$    $$ |$$$$  /$$$$ |$$    $$ | #\n"
            + "# $$$$$/   $$ |  $$ |$$ |  $$ |$$ |  $$ |$$$$$$/ $$ $$ $"
            + "$ |$$$$$$/ $$$$$$$  |$$$$$$$$ |$$ $$ $$/$$ |$$$$$$$$ | #\n"
            + "# $$ |     $$ \\__$$ |$$ \\__$$ |$$ |__$$ |        $$ \\"
            + "$$$$ |        $$ |  $$ |$$ |  $$ |$$ |$$$/ $$ |$$ |  $$ | #\n"
            + "# $$ |     $$    $$/ $$    $$/ $$    $$/         $$   $"
            + "$$/         $$ |  $$ |$$ |  $$ |$$ | $/  $$ |$$ |  $$ | #\n"
            + "# $$/       $$$$$$/   $$$$$$/  $$$$$$$/           $$$$"
            + "$$/          $$/   $$/ $$/   $$/ $$/      $$/ $$/   $$/  #\n"
            + "#######################################################"
            + "#########################################################\n";

    private static final String EXIT_LOGO =
            "#######################################"
            + "####################################\n"
            + "#           _____                ____"
            + "_                    _____           #\n"
            + "#          /\\    \\              |\\"
            + "    \\                  /\\    \\          #\n"
            + "#         /::\\    \\             |:\\"
            + "____\\                /::\\    \\         #\n"
            + "#        /::::\\    \\            |::"
            + "|   |               /::::\\    \\        #\n"
            + "#       /::::::\\    \\           |::"
            + "|   |              /::::::\\    \\       #\n"
            + "#      /:::/\\:::\\    \\          |::"
            + "|   |             /:::/\\:::\\    \\      #\n"
            + "#     /:::/__\\:::\\    \\         |::"
            + "|   |            /:::/__\\:::\\    \\     #\n"
            + "#    /::::\\   \\:::\\    \\        |:"
            + ":|   |           /::::\\   \\:::\\    \\    #\n"
            + "#   /::::::\\   \\:::\\    \\       |:"
            + ":|___|______    /::::::\\   \\:::\\    \\   #\n"
            + "#  /:::/\\:::\\   \\:::\\ ___\\      /"
            + "::::::::\\    \\  /:::/\\:::\\   \\:::\\    \\  #\n"
            + "# /:::/__\\:::\\   \\:::|    |    /:::"
            + ":::::::\\____\\/:::/__\\:::\\   \\:::\\____\\ #\n"
            + "# \\:::\\   \\:::\\  /:::|____|   /:::"
            + "/~~~~/~~      \\:::\\   \\:::\\   \\::/    / #\n"
            + "#  \\:::\\   \\:::\\/:::/    /   /:::/"
            + "    /          \\:::\\   \\:::\\   \\/____/  #\n"
            + "#   \\:::\\   \\::::::/    /   /:::/  "
            + "  /            \\:::\\   \\:::\\    \\      #\n"
            + "#    \\:::\\   \\::::/    /   /:::/  "
            + "  /              \\:::\\   \\:::\\____\\     #\n"
            + "#     \\:::\\  /:::/    /    \\::/   "
            + " /                \\:::\\   \\::/    /     #\n"
            + "#      \\:::\\/:::/    /      \\/____"
            + "/                  \\:::\\   \\/____/      #\n"
            + "#       \\::::::/    /              "
            + "                   \\:::\\    \\          #\n"
            + "#        \\::::/    /              "
            + "                     \\:::\\____\\         #\n"
            + "#         \\::/____/                "
            + "                     \\::/    /         #\n"
            + "#          ~~                       "
            + "                     \\/____/         #\n"
            + "####################################"
            + "#######################################";

    private static final String WELCOME_MESSSAGE = START_LOGO + System.lineSeparator()
            + "Hello, welcome to FOOD-O-RAMA! The number one solution for food waste management."
            + System.lineSeparator()
            + "Type a command to get us started, or type help for a list of commands!" + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String EXIT_MESSAGE = EXIT_LOGO + System.lineSeparator()
            + "Thank you for using Food-O-Rama to track your food wastage." + System.lineSeparator()
            + "Have a nice day!" + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INVALID_COMMAND = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid command." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String LIST_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, please input: list [TYPE]." + System.lineSeparator()
            + "[TYPE]: dish to list dishes, ingr to list ingredients." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_NOTEXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_EXISTS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish already exists in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_NOTEXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this ingredient does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_EXISTS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this ingredient already exists in your list.";

    private static final String INVALID_PARAMETERS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, please input a valid number." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String DISH_LIST_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Dish list has been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INGR_LIST_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Ingredient list has been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String ALL_CLEARED = LINE_DIVIDER + System.lineSeparator()
            + "Both Dish and Ingredient lists have been cleared." + System.lineSeparator()
            + LINE_DIVIDER;

    protected static final String HELP_ME = LINE_DIVIDER + System.lineSeparator()
            + "Here are the commands you can use: \n"
            + "1. Adding a Dish : 'add dish [dishName]' \n"
            + "    Example: 'add dish chicken rice' \n"
            + "2. Adding Dish Wastage : 'add dish waste [dishName]' followed by '[weight in KG]' \n"
            + "    Example: 'add dish waste chicken rice' ; '0.8' \n"
            + "3. Deleting a Dish : 'del [dishName] \n"
            + "    Example: 'del dish chicken rice' \n"
            + "4. Viewing all Dishes : 'list dish' \n"
            + "5. Adding a new Ingredient : 'add ingr [ingrName]' followed by '[weight in KG]' \n"
            + "    Example: 'add ingr chicken' ; '2' \n"
            + "6. Adding storage to an already existing Ingredient : 'add ingr stored [ingrName]'"
            + " followed by '[weight in KG]' \n"
            + "    Example: 'add ingr stored chicken' ; '1.5' \n"
            + "7. Adding Ingredient Wastage : 'add ingr waste [ingrName]' followed by '[weight in KG]' \n"
            + "    Example: 'add ingr waste chicken' ; '0.7' \n"
            + "8. Linking an Ingredient to a Dish : 'add dish constituent [dishName] / [ingrName]'\n"
            + "    Example: 'add constituent chicken rice / chicken' \n"
            + "9. Deleting an Ingredient : 'del [ingrName]' \n"
            + "    Example: 'del ingr chicken' \n"
            + "10. Viewing all Ingredients : 'list ingr' \n"
            + "11. Viewing this list of commands: help \n"
            + "12. Exiting the program: bye"
            + System.lineSeparator()
            + LINE_DIVIDER;

    public String FIND_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Please enter a keyword!" + System.lineSeparator()
            + LINE_DIVIDER;

    public String getLogo() {
        return START_LOGO;
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

    public void printInvalidCommandMsg() {
        System.out.println(INVALID_COMMAND);
    }

    public String getListMissingParamMsg() {
        return LIST_MISSING_PARAM;
    }

    public String getDishNotExistMsg() {
        return DISH_NOTEXIST;
    }

    public String getDishExistsMsg() {
        return DISH_EXISTS;
    }

    public String getIngrNotExistMsg() {
        return INGR_NOTEXIST;
    }

    public String getIngrExistsMsg() {
        return INGR_EXISTS;
    }

    public String getInvalidParamMsg() {
        return INVALID_PARAMETERS;
    }

    public String getHelpMsg() {
        return HELP_ME;
    }

    public String getDishListCleared() {
        return DISH_LIST_CLEARED;
    }

    public String getIngrListCleared() {
        return INGR_LIST_CLEARED;
    }

    public String getAllCleared() {
        return ALL_CLEARED;
    }

    public void printAddedDish(String dishName) {
        assert dishName != null : "dishName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
            + "Dish added to list: " + dishName + System.lineSeparator()
            + LINE_DIVIDER);
    }

    public void printDishList(ArrayList<Dish> dishList) {
        assert dishList != null : "dishList cannot be null";
        System.out.println("Here are the dishes you have: ");
        for (int i = 0; i < dishList.size(); i++) {
            System.out.println((i + 1) + ". " + dishList.get(i));
        }
        System.out.println("You can use command 'add' to add new dishes!");
    }

    public void printDishNameRemoved(String dishName) {
        assert dishName != null : "dishName cannot be null";
        System.out.println("Dish, " + dishName + " has been removed!");
    }

    public void printMatchedDishes(ArrayList<Dish> matchedDishList) {
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
}
