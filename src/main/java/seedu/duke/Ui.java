package seedu.duke;

public class Ui {
    //Todo help
    private static final String LINE_DIVIDER = "____________________________________________________________";
    private static final String START_LOGO =
            "################################################################################################################\n" +
                    "#  ________  ______    ______   _______            ______           _______    ______   __       __   ______   #\n" +
                    "# /        |/      \\  /      \\ /       \\          /      \\         /       \\  /      \\ /  \\     /  | /      \\  #\n" +
                    "# $$$$$$$$//$$$$$$  |/$$$$$$  |$$$$$$$  |        /$$$$$$  |        $$$$$$$  |/$$$$$$  |$$  \\   /$$ |/$$$$$$  | #\n" +
                    "# $$ |__   $$ |  $$ |$$ |  $$ |$$ |  $$ | ______ $$$  \\$$ | ______ $$ |__$$ |$$ |__$$ |$$$  \\ /$$$ |$$ |__$$ | #\n" +
                    "# $$    |  $$ |  $$ |$$ |  $$ |$$ |  $$ |/      |$$$$  $$ |/      |$$    $$< $$    $$ |$$$$  /$$$$ |$$    $$ | #\n" +
                    "# $$$$$/   $$ |  $$ |$$ |  $$ |$$ |  $$ |$$$$$$/ $$ $$ $$ |$$$$$$/ $$$$$$$  |$$$$$$$$ |$$ $$ $$/$$ |$$$$$$$$ | #\n" +
                    "# $$ |     $$ \\__$$ |$$ \\__$$ |$$ |__$$ |        $$ \\$$$$ |        $$ |  $$ |$$ |  $$ |$$ |$$$/ $$ |$$ |  $$ | #\n" +
                    "# $$ |     $$    $$/ $$    $$/ $$    $$/         $$   $$$/         $$ |  $$ |$$ |  $$ |$$ | $/  $$ |$$ |  $$ | #\n" +
                    "# $$/       $$$$$$/   $$$$$$/  $$$$$$$/           $$$$$$/          $$/   $$/ $$/   $$/ $$/      $$/ $$/   $$/  #\n" +
                    "################################################################################################################\n";

    private static final String EXIT_LOGO =
            "###########################################################################\n" +
                    "#           _____                _____                    _____           #\n" +
                    "#          /\\    \\              |\\    \\                  /\\    \\          #\n" +
                    "#         /::\\    \\             |:\\____\\                /::\\    \\         #\n" +
                    "#        /::::\\    \\            |::|   |               /::::\\    \\        #\n" +
                    "#       /::::::\\    \\           |::|   |              /::::::\\    \\       #\n" +
                    "#      /:::/\\:::\\    \\          |::|   |             /:::/\\:::\\    \\      #\n" +
                    "#     /:::/__\\:::\\    \\         |::|   |            /:::/__\\:::\\    \\     #\n" +
                    "#    /::::\\   \\:::\\    \\        |::|   |           /::::\\   \\:::\\    \\    #\n" +
                    "#   /::::::\\   \\:::\\    \\       |::|___|______    /::::::\\   \\:::\\    \\   #\n" +
                    "#  /:::/\\:::\\   \\:::\\ ___\\      /::::::::\\    \\  /:::/\\:::\\   \\:::\\    \\  #\n" +
                    "# /:::/__\\:::\\   \\:::|    |    /::::::::::\\____\\/:::/__\\:::\\   \\:::\\____\\ #\n" +
                    "# \\:::\\   \\:::\\  /:::|____|   /:::/~~~~/~~      \\:::\\   \\:::\\   \\::/    / #\n" +
                    "#  \\:::\\   \\:::\\/:::/    /   /:::/    /          \\:::\\   \\:::\\   \\/____/  #\n" +
                    "#   \\:::\\   \\::::::/    /   /:::/    /            \\:::\\   \\:::\\    \\      #\n" +
                    "#    \\:::\\   \\::::/    /   /:::/    /              \\:::\\   \\:::\\____\\     #\n" +
                    "#     \\:::\\  /:::/    /    \\::/    /                \\:::\\   \\::/    /     #\n" +
                    "#      \\:::\\/:::/    /      \\/____/                  \\:::\\   \\/____/      #\n" +
                    "#       \\::::::/    /                                 \\:::\\    \\          #\n" +
                    "#        \\::::/    /                                   \\:::\\____\\         #\n" +
                    "#         \\::/____/                                     \\::/    /         #\n" +
                    "#          ~~                                            \\/____/         #\n" +
                    "###########################################################################";

    private static final String WELCOME_MESSSAGE = START_LOGO + System.lineSeparator()
            + "Hello, welcome to FOOD-O-RAMA! The number one solution for food waste management."
            + System.lineSeparator()
            + "Type a command to get us started!" + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String EXIT_MESSAGE = EXIT_LOGO + System.lineSeparator()
            + "Thank you for using Food-O-Rama to track your food wastage." + System.lineSeparator()
            + "Have a nice day!" + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String INVALID_COMMAND = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid command." + System.lineSeparator()
            + LINE_DIVIDER;

    private static final String LIST_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Missing parameters. Please input: list <TYPE>" + System.lineSeparator()
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

    protected static final String HELP_ME = LINE_DIVIDER + System.lineSeparator()
            + "Here are the commands you can use: \n"
            + "\n1. Adding a Dish : 'add dish [dishName]' \n"
            + "    Example: 'add dish chicken rice' \n"
            + "2. Adding Dish Wastage : 'add dish waste [dishName]' followed by '[weight in KG]' \n"
            + "    Example: 'add dish waste chicken rice' ; '0.8' \n"
            + "3. Deleting a Dish : 'del [dishName] \n"
            + "    Example: 'del dish chicken rice' \n"
            + "4. Viewing all Dishes : 'list dish' \n"
            + "\n5. Adding a new Ingredient : 'add ingr [ingrName]' followed by '[weight in KG]' \n"
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
            + System.lineSeparator()
            + LINE_DIVIDER;

    public String getLogo() {
        return START_LOGO;
    }

    public String getWelcomeMsg() {
        return WELCOME_MESSSAGE;
    }

    public String getExitMsg() {
        return EXIT_MESSAGE;
    }

    public String getLineDivider() {
        return LINE_DIVIDER;
    }

    public String getInvalidCommandMsg() {
        return INVALID_COMMAND;
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

    public String getHelpMsg() {
        return HELP_ME;
    }
}
