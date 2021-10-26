package seedu.typists.command;

import seedu.typists.game.GameRecord;
import seedu.typists.game.GameRecordsManager;
import seedu.typists.ui.ViewCommandUi;

import java.util.ArrayList;

public class ViewCommand implements Command {
    private int numberOfRecords = 10;
    private String gameMode;
    private ArrayList<String> args;
    private static final String GAME_MODE_OPTION = "g";
    private static final String NUMBER_OF_RECORDS_OPTION = "n";
    private static final String HELP_OPTION = "h";


    public void run(ArrayList<String> args) {
        this.args = args;
        boolean isHelp = isHelpCommand();
        if (isHelp) {
            ViewCommandUi.displayHelp();
        } else {
            executeCommand();
        }
    }

    private void executeCommand() {
        GameRecordsManager gameRecordsManager = GameRecordsManager.getGameRecordsManager();
        parseCommand();
        ArrayList<GameRecord> gameRecords = gameRecordsManager.getGameRecords(gameMode, numberOfRecords);
        ViewCommandUi.displayGameRecords(gameRecords);
    }


    private boolean isHelpCommand() {
        int indexHelp = args.indexOf("-" + HELP_OPTION);
        if (indexHelp != -1) {
            return true;
        } else {
            return false;
        }

    }

    private void parseCommand() {
        getGameMode();
        getNumberOfRecords();
    }

    private void getNumberOfRecords() {
        int indexNumberOfRecords = args.indexOf("-" + NUMBER_OF_RECORDS_OPTION);
        if (indexNumberOfRecords == -1) {
            return;
        } else {
            try {
                numberOfRecords = Integer.parseInt(args.get(indexNumberOfRecords + 1));
            } catch (NumberFormatException e) {
                System.out.print("Invalid argument for number of records option.\n"
                        + "Option only accepts integer values."
                        + "Input \"View -h\" for help\n");
            }
        }

    }

    private void getGameMode() {
        int indexGameMode = args.indexOf("-" + GAME_MODE_OPTION);
        if (indexGameMode == -1) {
            System.out.print("Invalid command.\n"
                    + "Input \"View -h\" for help\n");
            return;
        } else {
            gameMode = args.get(indexGameMode + 1);
        }
        if (gameMode != "time" && gameMode != "word") {
            System.out.print("Invalid argument for game mode option.\n"
                    + "Input \"View -h\" for help\n");
            return;
        } else {
            gameMode = gameMode.substring(0, 1).toUpperCase() + gameMode.substring(1) + "-limited";
        }
    }

}
