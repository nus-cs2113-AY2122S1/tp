package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Gordon {
    public static void main(String[] args) throws GordonException {

        System.out.println("You can now add your own recipes!");
        Parser parser = new Parser();
        parser.parseMaster();
    }
}
