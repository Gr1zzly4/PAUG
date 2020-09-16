package model;

import io.LvlIO;
import io.ScoreIO;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class Game {

    private String playerName;
    private LvlIO lvlLouder = new LvlIO();
    private ScoreIO scoreLouder = new ScoreIO();
    private Map lvl;
    private Character player;

    private ScoreBoard[] gameBoard;

    private double currentScore;

    private int trigger;
    private boolean[] mInputs;

    private gameState game;

    private boolean[] mPrevInputs;
    public Game (String name){
        playerName = name;
        gameBoard = new ScoreBoard[5];

        gameBoard = scoreLouder.loadScore();

        currentScore = 0;
        //lvl = new Map();
        lvl = new Map(lvlLouder.loadLvl());
        trigger = 0;
        player = new Character(lvl);
        game = gameState.MenuScreen;
    }

    public ScoreBoard[] getScoreBoard() {
        return gameBoard;
    }

    public void update()
    {
        if(game == gameState.Lvl){
            this.player.CharacterUpdate();

            if(!player.lifeState){
                game = gameState.LoseScreen;
            }

            if(player.winState){
                currentScore = getWinTime();
                addScore();
                game = gameState.WinScreen;
            }
        }
    }

    public void setCurrentScore(double currentScore) {
        this.currentScore = currentScore;
    }

    public double getWinTime(){
        double end = System.currentTimeMillis();
        end = end - currentScore;

        return end;
    }

    public void addScore(){
        for(int i = 0; i < 5; i++){
            if(this.gameBoard[i].getTime() > currentScore/360){
                for(int j = 4; j > i; j--){
                    this.gameBoard[j] = this.gameBoard[j-1];
                }

                this.gameBoard[i] = new ScoreBoard( playerName,currentScore/360);
                break;

            }
        }
    }

    public String getScoreFirstString(int i){
        String str = (i + 1) + " " + gameBoard[i].getName() + " " + new DecimalFormat("#0.00").format(gameBoard[i].getTime())  + " sec.";
        return str;
    }

    public String getCurrentScoreString(){
        String str ;
        str = "" +  new DecimalFormat("#0.00").format(currentScore/360);
        return str;
    }

    public void start (){
        if(game == gameState.Lvl) {
            currentScore = System.currentTimeMillis();
            this.mInputs = new boolean[(int) Character.KeyInput.Count.ordinal()];
            this.mPrevInputs = new boolean[(int) Character.KeyInput.Count.ordinal()];
            player = new Character(lvl);
            player.CharacterInit(mInputs, mPrevInputs);
        }
    }

    public gameState getGameState(){return game;}

    public void keyMenuPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            trigger--;
            if(trigger <0)
                trigger = 0;
        }

        if(key == KeyEvent.VK_S) {
           trigger++;
           if(trigger > 3)
               trigger = 3;
        }

        if(key == KeyEvent.VK_SPACE) {
           switch (trigger){
               case 0:{
                   this.game = gameState.Lvl;
                   start();
                   break;

               }

               case 1:{
                    this.game = gameState.HelpScreen;
                   break;

               }

               case 2:{
                   this.game = gameState.ScoreScreen;
                   break;

               }

               case 3:{
                   this.game = gameState.Exit;
                   break;

               }
           }
        }
    }

    public void keyWinAndLoseScreen(KeyEvent e){
        int key = e.getKeyCode();

        if(trigger >1)
            trigger = 1;

        if(key == KeyEvent.VK_W) {
            trigger--;
            if(trigger <0)
                trigger = 0;
        }

        if(key == KeyEvent.VK_S) {
            trigger++;
            if(trigger > 1)
                trigger = 1;
        }

        if(key == KeyEvent.VK_SPACE) {
            switch (trigger){
                case 0:{
                    this.game = gameState.Lvl;
                    start();
                    break;

                }

                case 1:{
                    this.game = gameState.MenuScreen;
                    break;

                }

            }
        }
    }

    public void keyHelpScreen(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE) {
            this.game = gameState.MenuScreen;
        }
    }

    public int getTrigger(){
        return trigger;
    }

    public void keyPressed(KeyEvent e) {player.keyPressed(e);};

    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    public ScoreBoard[] getGameBoard(){return gameBoard;}
    public Map getLvl(){return lvl;}

    public Map.TileType getStorage(int x, int y){ return  lvl.getStorage(x, y);}
    public int playerY(){return (int)player.getPosY();}

    public int playerX(){return (int)player.getPosX();}


    public enum gameState{
        MenuScreen,
        Lvl,
        WinScreen,
        LoseScreen,
        HelpScreen,
        ScoreScreen,
        Exit
    }
}
