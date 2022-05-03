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

    public int compare(Winner win){
        return (int)(this.time-win.time);
    }
}
