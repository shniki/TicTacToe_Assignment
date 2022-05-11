package com.example.tictactoeassignment;

public class Winner {

    private String name;
    private long time;

    public Winner(String name, long time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Long getTime() {
        return time;
    }

    public void setName(String name) {this.name = name;}

    public void setTime(long time) {this.time = time;}  // todo: change this to match format of time

    public int compare(Winner win){
        return (int)(this.time-win.time);
    }
}
