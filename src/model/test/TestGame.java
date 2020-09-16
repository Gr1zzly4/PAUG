package model.test;

import model.Game;

public class TestGame {

    private Game testGame = new Game("Mike");

    public TestGame(){}

    public void test(){

        System.out.println("                ---Test Game---");

        testGame.setCurrentScore(360);
        testGame.addScore();
        System.out.println("        Game addScoreTest:");

        if((testGame.getScoreBoard()[0].getTime() == 1) && (testGame.getScoreBoard()[0].getName() == "Mike"))
            System.out.println(true);
        else
            System.out.println(false);


    }



}
