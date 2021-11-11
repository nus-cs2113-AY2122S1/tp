package seedu.module;

public class Attributes {
    private boolean mpes1;
    private boolean mpes2;
    private boolean su;

    public String isSUable() {
        return (su) ? "Yes" : "No";
    }
}
