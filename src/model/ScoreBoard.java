package model;

public class ScoreBoard {
    private String name;
    private double time;

    public ScoreBoard(String playerName, double playerTime){
        this.name = playerName;
        this.time = playerTime;
    }

    public double getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
