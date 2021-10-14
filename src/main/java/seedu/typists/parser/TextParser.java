package seedu.typists.parser;

public class TextParser {
    protected String input;
    protected Boolean isExit;


    public TextParser(String input, String text) {
        this.input = input;
        this.isExit = !input.equals(text);
    }

    /**
     * Get exit.
     * @return obtain the Exit status
     */
    public Boolean getIsExit() {
        return isExit;
    }
}
