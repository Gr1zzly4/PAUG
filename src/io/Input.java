package io;

import model.Map;
import model.Map.TileType;
import model.ScoreBoard;


public class Input {
    public static void main(String[] args) {
       /* LvlIO lvlLouder = new LvlIO();
        Map lvl = new Map();
        lvl = new Map(lvlLouder.loadLvl());

        System.out.println(lvl.getStorage(0,0));
*/
        ScoreIO test = new ScoreIO();
        ScoreBoard[] testScore1 = new ScoreBoard[5];
        ScoreBoard[] testScore = new ScoreBoard[5];
        test.saveScore(testScore);

        testScore1 = test.loadScore();
    }
}
