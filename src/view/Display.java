package view;

import io.LvlIO;
import model.*;
import io.ScoreIO;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Display {
    private static String playerName;
    private static JButton ok = new JButton("ok");
    private static JTextField name = new JTextField("",20);
    private static JFrame start = new JFrame("Name");
    public static class OkActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(name.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter your name", "Message", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            start.setVisible(false);
            start.dispose();
            playerName = name.getText();
            JFrame frame = new JFrame("Dungeon of PAUG");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setMinimumSize(new Dimension(1000,1000));
            frame.setResizable(true);
            frame.add(new Main(frame));
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {

        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setBounds(600, 600, 780, 100);
        start.setVisible(true);
        Container box = new Container();
        box = start.getContentPane();
        box.setLayout(new FlowLayout());
        box.add(name);
        ok.addActionListener(new OkActionListener());
        box.add(ok);
    }

    //переименовать на мелкие
    public static class Main extends JPanel implements ActionListener {

        private final ScoreIO scoreWriter = new ScoreIO();
        private final LvlIO lvlWriter = new LvlIO();
        private final Image background = new ImageIcon("backgroudn.jpg").getImage();
        private final Image menuStartBlock = new ImageIcon("menuStartBlock.png").getImage();
        private final Image menuHelpBlock = new ImageIcon("menuHelpBlock.png").getImage();
        private final Image menuExitBlock = new ImageIcon("menuExitBlock.png").getImage();
        private final Image scoreBoard = new ImageIcon("scoreBoard.png").getImage();
        private final Image menuBlock = new ImageIcon("menuBlock.png").getImage();
        private final Image scoreBlock = new ImageIcon("scoreBoardBlock.png").getImage();
        private final Image plr = new ImageIcon("boy.png").getImage();
        private final Image scoreSolo = new ImageIcon("scoresoloboard.png").getImage();
        private final Image loseBlock = new ImageIcon("youlose.png").getImage();
        private final Image winBlock = new ImageIcon("youwin.png").getImage();
        private final Image water = new ImageIcon("water.jpg").getImage();
        private final Image menuSelectZone = new ImageIcon("selectZone.png").getImage();
        private final Image helpScreen = new ImageIcon("helpScreen.png").getImage();
        private final Image menuStartBack = new ImageIcon("menuStart.jpg").getImage();
        private final Image finish = new ImageIcon("finish.png").getImage();
        private final Image restart = new ImageIcon("winRestartBlock.png").getImage();
        private final Image block = new ImageIcon("block.png").getImage();

        Game myGame;

        JFrame frame;

        public Main(JFrame frame) {
            myGame = new Game(playerName);

            Timer timer = new Timer((int) Constants.DT, this);
            timer.start();
            myGame.start();

            this.frame = frame;

            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {

                    switch (myGame.getGameState()){

                        case Lvl:{
                            myGame.keyPressed(e);
                            break;
                        }

                        case ScoreScreen:{}

                        case HelpScreen:{
                            myGame.keyHelpScreen(e);
                            break;
                        }

                        case LoseScreen:{

                            break;
                        }

                        case WinScreen: {
                            myGame.keyWinAndLoseScreen(e);
                            break;
                        }

                        case MenuScreen: {
                            myGame.keyMenuPressed(e);
                            break;
                        }


                    }

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    myGame.keyReleased(e);
                }

            });

        }

        public void paint(Graphics g) {

            switch (myGame.getGameState()){

                case WinScreen:{
                    paintWinMenu(g);
                    break;
                }

                case MenuScreen:{
                    paintMenu(g);
                    break;
                }

                case Lvl:{
                    paintLvl(g);
                    break;
                }

                case ScoreScreen:{
                    this.paintScoreScreen(g);
                    break;
                }

                case HelpScreen:{
                    this.paintHelp(g);
                    break;
                }

                case LoseScreen:{
                    this.paintLoseMenu(g);
                    break;
                }

                case Exit:{
                    lvlWriter.saveLvl(myGame.getLvl());
                    scoreWriter.saveScore(myGame.getScoreBoard());
                    this.frame.setVisible(false);
                    this.frame.dispose();
                    break;
                }
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            myGame.update();
            repaint();

        }

        public void paintLvl(Graphics g){
            g.drawImage(background, 0, 0, frame.getWidth(), frame.getHeight(), null);
            for (int i = 0; i < 34; i++) {
                for (int j = 0; j < 18; j++) {
                    switch (myGame.getStorage(i, j)) {

                        case Block: {
                            g.drawImage(block, SetWorld.SetXWrold((i - 15) * 60, frame), SetWorld.SetYWrold((15 - j) * 60, frame), -(int) Constants.C_HALF_BLOCK_SIZE * 2, (int) Constants.C_HALF_BLOCK_SIZE * 2, null);
                            break;
                        }

                        case Water: {
                            g.drawImage(water, SetWorld.SetXWrold((i - 15) * 60, frame), SetWorld.SetYWrold((15 - j) * 60, frame), -(int) Constants.C_HALF_BLOCK_SIZE * 2, (int) Constants.C_HALF_BLOCK_SIZE * 2, null);
                            break;
                        }

                        case Finish: {
                            g.drawImage(finish, SetWorld.SetXWrold((i - 15 - 1) * 60, frame), SetWorld.SetYWrold((15 - j) * 60, frame), -(int) Constants.C_HALF_BLOCK_SIZE * 2, (int) Constants.C_HALF_BLOCK_SIZE * 2, null);
                            g.drawImage(finish, SetWorld.SetXWrold((i - 15) * 60, frame), SetWorld.SetYWrold((15 - j) * 60, frame), -(int) Constants.C_HALF_BLOCK_SIZE * 2, (int) Constants.C_HALF_BLOCK_SIZE * 2, null);
                            break;
                        }

                        case Free: {

                        }

                    }
                }
            }

            g.drawImage(plr, SetWorld.SetXWrold(myGame.playerX(), frame), SetWorld.SetYWrold(myGame.playerY(), frame) - (int) Constants.C_HALF_CHARACTER_SIZE_Y, -(int) Constants.C_HALF_CHARACTER_SIZE_X * 2, (int) Constants.C_HALF_CHARACTER_SIZE_Y * 2, null);
        }

        public void paintMenu(Graphics g){
            int i = myGame.getTrigger();
            g.drawImage(menuStartBack, 0, 0, frame.getWidth(), frame.getHeight(), null);
            g.drawImage(menuStartBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 1) * 60, frame), null);
            g.drawImage(menuHelpBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 5) * 60, frame), null);
            g.drawImage(scoreBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 9) * 60, frame), null);
            g.drawImage(menuExitBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 13) * 60, frame), null);
            g.drawImage(menuSelectZone, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 1 - i* 4) * 60, frame), null);

        }

        public void paintHelp(Graphics g){
            g.drawImage(menuStartBack, 0, 0, frame.getWidth(), frame.getHeight(), null);
            g.drawImage(helpScreen, SetWorld.SetXWrold((4 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 2) * 60, frame), null);
            g.drawImage(menuBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 13) * 60, frame), null);
            g.drawImage(menuSelectZone, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 13) * 60, frame), null);
        }

        public void paintScoreScreen(Graphics g){
            g.drawImage(menuStartBack, 0, 0, frame.getWidth(), frame.getHeight(), null);
            g.drawImage(menuBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 13) * 60, frame), null);
            g.drawImage(menuSelectZone, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 13) * 60, frame), null);

            g.drawImage(scoreBoard, SetWorld.SetXWrold((10 - 15 ) * 60 - 15, frame), SetWorld.SetYWrold((15 - 2 ) * 60, frame), null);

            g.setFont(new Font("TimesRoman", Font.PLAIN , SetWorld.SetYWrold((15 - 2) * 60,frame)));
            for (int i = 0; i < 5; i++) {
                g.drawString(myGame.getScoreFirstString(i),SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 3 - i*2) * 60 - 30, frame));
            }

        }

        public void paintLoseMenu(Graphics g){
            int i = myGame.getTrigger();
            g.drawImage(menuStartBack, 0, 0, frame.getWidth(), frame.getHeight(), null);
            g.drawImage(loseBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 5) * 60, frame), null);
            g.drawImage(restart, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 9) * 60, frame), null);
            g.drawImage(menuBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 13) * 60, frame), null);
            g.drawImage(menuSelectZone, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 9 - i*4) * 60, frame), null);
        }

        public void paintWinMenu(Graphics g){
            int i = myGame.getTrigger();
            g.drawImage(menuStartBack, 0, 0, frame.getWidth(), frame.getHeight(), null);
            g.drawImage(winBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 5) * 60, frame), null);
            g.drawImage(restart, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 9) * 60, frame), null);
            g.drawImage(menuBlock, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 13) * 60, frame), null);
            g.drawImage(menuSelectZone, SetWorld.SetXWrold((10 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 9 - i * 4) * 60, frame), null);
            g.drawImage(scoreSolo, SetWorld.SetXWrold((8 - 15 ) * 60 - 30, frame), SetWorld.SetYWrold((15 - 1 ) * 60, frame), null);
            g.setFont(new Font("TimesRoman", Font.PLAIN , SetWorld.SetYWrold((15 - 2) * 60,frame)));
            g.drawString("Your score is " + myGame.getCurrentScoreString() + " sec",SetWorld.SetXWrold((9 - 15 ) * 60, frame), SetWorld.SetYWrold((15 - 3) * 60, frame));
        }

    }
    final public static class SetWorld{
        public static int SetXWrold(double x,JFrame frame){
            return(frame.getWidth()/2 + (int)x - (int)Constants.C_HALF_BLOCK_SIZE*3 );
        }
        public static int SetYWrold(double y,JFrame frame){
            return(frame.getHeight() - (int) Constants.C_HALF_BLOCK_SIZE - 150 - (int)y);
        }
    }
}