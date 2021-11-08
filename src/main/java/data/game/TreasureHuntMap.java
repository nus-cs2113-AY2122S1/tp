package data.game;

import java.io.Serializable;

public class TreasureHuntMap implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    private final int[][][] easyMapList = {{{1, 1, 1, 1, 1, 1}, {1, 2, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1}, {1, 0, 0, -1, 0, 1}, {1, 1, 1, 1, 1, 1}},
        {{1, 1, 1, 1, 1, 1}, {1, 2, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 1},{1, 0, -1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1}},
        {{1, 1, 1, 1, 1, 1}, {1, 2, 0, 0, 1, 1}, {1, 1, 1, 0, 0, 1}, {1, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, -1, 1}, {1, 1, 1, 1, 1, 1}}};
    private final int[][][] difficultMapList = {{{1, 1, 1, 1, 1, 1, 1, 1}, {1, 2, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 0, 1, 1, 1, 1},
            {1, -1, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1}},
        {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 2, 1, 0, 0, 0, 1, 0, 1, -1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}};

    public final int[][] getEasyMap() {
        final double randomNum = Math.random();
        return this.easyMapList[(int) (randomNum * 100) % this.easyMapList.length];
    }

    public final int[][] getDifficultMap() {
        final double randomNum = Math.random();
        return this.difficultMapList[(int) (randomNum * 100) % this.difficultMapList.length];
    }
}
