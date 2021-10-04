package seedu.duke;

import java.util.Scanner;

public class StonksXD {
    private Ui ui;
    private FinancialTracker finances;

    public StonksXD(){
        this.ui = new Ui();
        this.finances = new FinancialTracker();
    }

    public void run(){

    }

    public static void main(String[] args) {
        new StonksXD().run();
    }
}
