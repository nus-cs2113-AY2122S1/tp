package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Gordon {
    public static void main(String[] args) {
        Cookbook mainCookbook = new Cookbook();
        Parser parser = new Parser();
        parser.parseMaster(mainCookbook);
    }
}
