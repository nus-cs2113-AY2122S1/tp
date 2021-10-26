package seedu.typists.command;

import static seedu.typists.Main.uiBot;

public class ExitCommand implements Command {
    @Override
    public void run() {
        uiBot.showBye();
    }
}
