package seedu.duke;

import java.util.ArrayList;

public class Ui {
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
            + "Sorry, this ingredient already exists in your list." + System.lineSeparator()
            + LINE_DIVIDER;

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

    public static final String FIND_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Please enter a keyword!" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String FIND_INCORRECT_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Incorrect paramater! Please type find <dish/ingredient>" + System.lineSeparator()
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

    public void printInvalidCommandMsg() {
        System.out.println(INVALID_COMMAND);
    }

    public void printListMissingParamMsg() {
        System.out.println(LIST_MISSING_PARAM);
    }

    public void printDishNotExistMsg() {
        System.out.println(DISH_NOTEXIST);
    }

    public void printDishExistsMsg() {
        System.out.println(DISH_EXISTS);
    }

    public void printIngrNotExistMsg() {
        System.out.println(INGR_NOTEXIST);
    }

    public void printIngrExistsMsg() {
        System.out.println(INGR_EXISTS);
    }

    public void printInvalidParamMsg() {
        System.out.println(INVALID_PARAMETERS);
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

    public void printAddedDish(String dishName) {
        assert dishName != null : "dishName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
            + "Dish added to list: " + dishName + System.lineSeparator()
            + LINE_DIVIDER);
    }

    public void printDishList(ArrayList<Dish> dishList) {
        assert dishList != null : "dishList cannot be null";
        System.out.println(getLineDivider());
        System.out.println("Here are the dishes you have:");
        for (int i = 0; i < dishList.size(); i++) {
            System.out.println((i + 1) + ". " + dishList.get(i));
        }
        System.out.println("You can use command 'add' to add new dishes!");
        System.out.println(getLineDivider());
    }

    public void printDishListGraph(ArrayList<Dish> dishList) {
        assert dishList != null : "dishList cannot be null";
        double max = DishList.getGreatestWaste();
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Here are the dishes you have:");
        for (int i = 0; i < dishList.size(); i++) {
            System.out.println((i + 1) + ". " + dishList.get(i).toGraph(max));
        }
        System.out.println("You can use command 'add' to add new dishes!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printDishNameRemoved(String dishName) {
        assert dishName != null : "dishName cannot be null";
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + dishName + " has been removed!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printAddedIngredient(Ingredient ingredientToAdd, double ingredientWeight) {
        System.out.println(LINE_DIVIDER+ System.lineSeparator()
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

    public void printIngrListGraph(ArrayList<Ingredient> ingredientList) {
        assert ingredientList !=null : "ingredientList cannot be null";
        double max = IngredientList.getGreatestWaste();
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Here are the dishes you have: ");
        for (int i = 0; i < ingredientList.size(); i++) {
            System.out.println((i + 1) + ". " + ingredientList.get(i).toGraph(max));
        }
        System.out.println("You can use command 'add' to add new dishes!"+ System.lineSeparator()
                + LINE_DIVIDER);
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

    public void printAddedConstituentOf(String ingredientName, String dishName) {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "Successfully added " + ingredientName + " as ingredient of " + dishName + "!" + System.lineSeparator()
                + LINE_DIVIDER);
    }

    public void printFindMissingParamMsg() {
        System.out.println(FIND_MISSING_PARAM);
    }

    public void printFindIncorrectParamMsg() {
        System.out.println(FIND_INCORRECT_PARAM);
    }

    public void printWastage(String name, double wastage) {
        System.out.println("Wastage of " + name + " is now " + wastage + " kg");
    }

    public void printStorage(String name, double storage) {
        System.out.println("Storage of " + name + " is now " + storage + " kg");
    }

    public void clearTerminal() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("");
        }
        System.out.flush();
    }
}
