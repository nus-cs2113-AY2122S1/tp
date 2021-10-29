package seedu.duke.ui;

import static java.lang.System.out;

public class UiInvalid extends Ui {
    public static void printFindModNull() {
        out.println("No modules found");
    }

    public static void printFindUniNull() {
        out.println("No universities found");
    }
}
