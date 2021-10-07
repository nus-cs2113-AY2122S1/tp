package game;

import java.util.ArrayList;

public class GameRecord {
    public static ArrayList<Game> games;

    public GameRecord() {
        this.games = new ArrayList<Game>();
    }

    public GameRecord(ArrayList<Game> games) {
        this.games = games;
    }

}
