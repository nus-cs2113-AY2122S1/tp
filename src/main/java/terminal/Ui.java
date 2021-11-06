package terminal;

import java.util.Scanner;

public class Ui {

    private static final String LOGO =
            "   ##     ####    ####    ####   #    #  #    #  #  #    #    ##    #####  ######\n"
                    + "  #  #   #    #  #    #  #    #  #    #  ##  ##  #  ##   #   #  #     #    #\n"
                    + " #    #  #       #       #    #  #    #  # ## #  #  # #  #  #    #    #    #####\n "
                    + "######  #       #       #    #  #    #  #    #  #  #  # #  ######    #    #\n"
                    + " #    #  #    #  #    #  #    #  #    #  #    #  #  #   ##  #    #    #    #\n"
                    + " #    #   ####    ####    ####    ####   #    #  #  #    #  #    #    #    ######\n";

    private static final String ERROR_HEADER = "Error";
    private static final String SUCCESS_HEADER = "Success";

    private static Ui ui;

    private Ui() {

    }

    public static Ui getUi() {
        if (ui == null) {
            ui = new Ui();
        }
        return ui;
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }

    public void printGreeting() {
        System.out.println(LOGO);
    }

    public String getUserInput() {
        System.out.print("user:> ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printErrorMessage(String msg) {
        System.out.println(ERROR_HEADER + ": " + msg);
    }

    public void printSuccessMessage(String msg) {
        System.out.println(SUCCESS_HEADER + ": " + msg);
    }
}
