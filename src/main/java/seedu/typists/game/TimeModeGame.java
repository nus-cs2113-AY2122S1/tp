package seedu.typists.game;

import seedu.typists.ui.TextUi;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static seedu.typists.common.Messages.SAMPLE_TEXT;

public class TimeModeGame extends Game {
    private final TextUi uiBot;

    public TimeModeGame() {
        this.uiBot = new TextUi();
    }

    /** Currently still a dummy */
    public String getTargetWordSet() {
        return SAMPLE_TEXT;
    }

    public void startGame(String targetWordSet, int timeLimitSeconds) {
        uiBot.showTargetWordSet(targetWordSet);
        /*
        TimerTask game = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer ends");
            }
        };

        Timer timer = new Timer(){
            @Override
            public void cancel() {
                super.cancel();
                System.out.println("Timer abort.");
            }
        };
        timer.schedule(game, timeLimitSeconds*1000 );
        System.out.println("counting " + timeLimitSeconds + " starting now:" );
        //task to do within timeLimit
        timer.cancel();
        */

        //method 1 trying
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime);

        //method 2 trying 
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a number: ");
        long limit = 5000L;
        long startTime = System.currentTimeMillis();
        Long l = reader.nextLong();
        if ((startTime + limit) < System.currentTimeMillis())
            System.out.println("Sorry, your answer is too late");
        else
            System.out.println("Your answer is on time");

    }

}
