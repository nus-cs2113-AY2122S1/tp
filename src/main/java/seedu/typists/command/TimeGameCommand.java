package seedu.typists.command;

import seedu.typists.exception.IncompleteCommandException;
import seedu.typists.exception.InvalidCommandException;
import seedu.typists.game.TimeModeGame;

import java.util.ArrayList;

import static seedu.typists.Main.content;
import static seedu.typists.Main.LINE_LENGTH;


public class TimeGameCommand implements Command {
    private static final String TIME_SIGNIFIER = "-t";
    private static final String START_SIGNIFIER = "-sn";
    private static final String CONTENT_SIGNIFIER = "-c";

    @Override
    public void run(ArrayList<String> args) {
        boolean startNow = getBoolean(args, START_SIGNIFIER);
        boolean setContent = getBoolean(args, CONTENT_SIGNIFIER);
        try {
            int timeInSeconds = getTime(args, TIME_SIGNIFIER);
            TimeModeGame game = createGame(timeInSeconds, startNow, setContent);
            game.runGame();
            game.gameSummary();
        } catch (InvalidCommandException e) {
            System.out.println(
                    "Please enter time in multiple of 30 seconds.\n"
                    + "e.g. time -t 30 "
            );
        } catch (IncompleteCommandException | IndexOutOfBoundsException e) {
            System.out.println(
                    "Please specify duration of the game using "
                    + TIME_SIGNIFIER + "\n"
                    + "e.g. time -t 60 "
            );
        } catch (NumberFormatException e) {
            System.out.println("Duration should be a number");
        }
    }

    /** Determine whether the user command has the signifier. **/
    public boolean getBoolean(ArrayList<String> args, String key) {
        return args.contains(key);
    }

    public int getTime(ArrayList<String> args, String key) throws InvalidCommandException, IncompleteCommandException {
        if (!args.contains(key)) {
            throw new IncompleteCommandException();
        }
        int index = args.indexOf("-t");
        int time = Integer.parseInt(args.get(index + 1));
        if (time % 30 != 0 || time == 0) {
            throw new InvalidCommandException();
        }
        return time;
    }

    public TimeModeGame createGame(int timeInSeconds, boolean isReady, boolean setContent) {
        if (setContent) {
            content.setContent();
        }
        return new TimeModeGame(timeInSeconds,content.getContent(), LINE_LENGTH, isReady);
    }
}
