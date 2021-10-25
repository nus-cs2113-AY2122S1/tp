package seedu.typists.command;

import static seedu.typists.Main.content;

public class ContentCommand implements Command {
    @Override
    public void run() {
        content.setContent();
    }

}
