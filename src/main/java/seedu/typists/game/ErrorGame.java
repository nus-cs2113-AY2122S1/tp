package seedu.typists.game;

import seedu.typists.content.Animation;
import seedu.typists.ui.TextUi;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.typists.common.Utils.getWordLine;

public class ErrorGame extends Game {
    private final TextUi ui;

    protected ArrayList<String> inputLines;
    protected final ArrayList<String[]> wordLines;

    public ErrorGame(String targetWordSet, int wordsPerLine) {
        ui = new TextUi();
        inputLines = new ArrayList<>();
        wordLines = getWordLine(targetWordSet, wordsPerLine);
        try {
            startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean readyToStartGame() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("yes")) {
            ui.printScreen("Do you want to start now? (enter !bye to quit the game at any time)");
            command = in.nextLine();
        }
        return true;
    }

    public void startGame() throws InterruptedException {
        if (readyToStartGame()) {
            String userInput = "";
            Scanner in = new Scanner(System.in);
            for (int i = 0; i < wordLines.size(); i++) {
                ui.printLine(wordLines.get(i));
                userInput = in.nextLine();
                if (userInput.equals("!bye")) {
                    break;
                }
                else {
                    inputLines.add(userInput);
                    Animation animation = new Animation();
                    animation.resetAnimLeft();
                    int k = 0;
                    while (k < 6) {
                        animation.animateLeft("Line " + (i + 1) + " out of " + wordLines.size());
                        Thread.sleep(300);
                        k++;
                    }
                    System.out.println("");
                }
            }
            System.out.println("Game Finished.");
        }
    }
}
