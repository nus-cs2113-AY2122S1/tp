package seedu.typists.parser;

import seedu.typists.content.Content;

import static seedu.typists.Main.content;

public class ContentCommand implements Command{
    @Override
    public void run() {
        content.setContent();
    }

}
