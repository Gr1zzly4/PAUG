package io;

import model.ScoreBoard;

import java.io.*;

public  class ScoreIO {
    public ScoreIO(){}

    public  void saveScore (ScoreBoard[] scoreBoard){
        File f = new File(System.getProperty(".//..//PAUG"), "Score.txt");

        try (PrintWriter pw = new PrintWriter(f)) {
            for (ScoreBoard d : scoreBoard) {
                pw.println(d.getName());
                pw.println(d.getTime());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public  ScoreBoard[]  loadScore() {
        ScoreBoard[] scoreBoard = new ScoreBoard[5];

        try (BufferedReader br = new BufferedReader (new FileReader(".//..//PAUG//Score.txt"))){

            String s;
            double ptr;
            String strPtr;

            for (int i = 0; i < 5; i++){
                s = br.readLine();
                strPtr = s;
                s = br.readLine();
                ptr = Double.parseDouble(s);

                scoreBoard[i] = new ScoreBoard(strPtr,ptr);
            }

            return scoreBoard;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scoreBoard;
    }
}

