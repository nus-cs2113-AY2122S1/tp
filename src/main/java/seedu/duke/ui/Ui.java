package seedu.duke.ui;

import java.util.Scanner;

public class Ui {
    public static final String LINE =
            "_______________________________________________________________________________"
                    + System.lineSeparator();

    /** Temporary logo. */
    public static final String LOGO =
              "   _   _       _   _   ____           ____     _   _   ____     ____   __   __ "
            + System.lineSeparator()
            + "  | \\ |\"|   U |\"|u| | / __\"| u     U | __\")uU |\"|u| | |  _\"\\   |  _\"\\  \\ \\ / / "
            + System.lineSeparator()
            + " <|  \\| |>   \\| |\\| |<\\___ \\/       \\|  _ \\/ \\| |\\| |/| | | | /| | | |  \\ V /  "
            + System.lineSeparator()
            + " U| |\\  |u    | |_| | u___) |        | |_) |  | |_| |U| |_| |\\U| |_| |\\U_|\"|_u "
            + System.lineSeparator()
            + "  |_| \\_|    <<\\___/  |____/>>       |____/  <<\\___/  |____/ u |____/ u  |_|   "
            + System.lineSeparator()
            + "  ||   \\\\,-.(__) )(    )(  (__)     _|| \\\\_ (__) )(    |||_     |||_ .-,//|(_  "
            + System.lineSeparator()
            + "  (_\")  (_/     (__)  (__)         (__) (__)    (__)  (__)_)   (__)_) \\_) (__) "
            + System.lineSeparator();

    private final Scanner sc = new Scanner(System.in);

    public String readUserResponse() {
        return sc.nextLine().strip();
    }

    /** Greets user by displaying logo. */
    public void printGreeting() {
        System.out.print(LINE);
        System.out.print(LOGO);
        System.out.println(LINE);
    }
}
