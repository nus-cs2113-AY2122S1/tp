package seedu.budgettracker.ui;

//@@author yeoweihngwhyelab
public class Delay {
    //@@author yeoweihngwhyelab
    public static void loadingBar(int ms) {
        for (int i = 0; i < 56; i++) {
            System.out.print("=");
            wait(ms);
        }

        System.out.println();
    }

    //@@author yeoweihngwhyelab
    /**
     * Causes a delay in execution of code for specified duration
     * in ms before returning control back to the caller of this
     * method.
     *
     * @param ms The amount of millisecond to delay execution.
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
