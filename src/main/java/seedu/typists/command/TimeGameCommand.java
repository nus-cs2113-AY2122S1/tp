package seedu.typists.command;

import seedu.typists.exception.InvalidCommandException;
import seedu.typists.game.Game;
import seedu.typists.game.TimeModeGame;

import java.util.ArrayList;

import static seedu.typists.Main.LINE_LENGTH;
import static seedu.typists.Main.content;

public class TimeGameCommand extends GameCommand {
    private static final String TIME_SIGNIFIER = "-time";

    @Override
    public Game createGame(ArrayList<String> args, boolean isReady, boolean setContent) {
        try {
            int timeInSeconds = getNumber(args, TIME_SIGNIFIER);
            if (setContent) {
                content.setContent();
            }
            return new TimeModeGame(timeInSeconds, content.getContent(), LINE_LENGTH, isReady);
        } catch (NumberFormatException e) {
            System.out.println("Duration should be a number");
        } catch (InvalidCommandException e) {
            System.out.println(
                    "Please enter time in multiple of 30 seconds.\n"
                            + "e.g. game -t 30 "
            );
        }
        return null;
    }

    @Override
    public int getNumber(ArrayList<String> args, String gameType)
            throws InvalidCommandException {
        int n = super.getNumber(args, gameType);
        if ((n % 30 != 0 || n == 0) && n != -1 ) {
            throw new InvalidCommandException();
        }
        return n;
    }
}
