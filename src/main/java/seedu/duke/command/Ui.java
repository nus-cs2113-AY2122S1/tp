package seedu.duke.command;

public class Ui {
    protected String LINE_DIVIDER = "____________________________________________________________";
    protected String START_LOGO =
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

    protected String EXIT_LOGO =
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

    protected String WELCOME_MESSSAGE = START_LOGO + System.lineSeparator()
            + "Hello, welcome to FOOD-O-RAMA! The number one solution for food waste management." + System.lineSeparator()
            + "Type a command to get us started!" + System.lineSeparator()
            + LINE_DIVIDER;

    protected String EXIT_MESSAGE = EXIT_LOGO + System.lineSeparator()
            + "Thank you for using Food-O-Rama to track your food wastage." + System.lineSeparator()
            + "Have a nice day!" + System.lineSeparator()
            + LINE_DIVIDER;

    protected String INVALID_COMMAND = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, that is an invalid command." + System.lineSeparator()
            + LINE_DIVIDER;

    protected String LIST_MISSING_PARAM = LINE_DIVIDER + System.lineSeparator()
            + "Missing parameters. Please input: list <TYPE>" + System.lineSeparator()
            + LINE_DIVIDER;

    protected String DISH_NOTEXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    protected String DISH_EXISTS = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this dish does not exist in your list." + System.lineSeparator()
            + LINE_DIVIDER;

    protected String INGR_NOTEXIST = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, this ingredient does not exist in your list." + System.lineSeparator()
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
}
