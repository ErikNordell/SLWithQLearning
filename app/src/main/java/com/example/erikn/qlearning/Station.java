package com.example.erikn.qlearning;

/**
 * Created by erikn on 2017-12-07.
 */

public class Station extends State {

    private int line;
    private String name;

    public Station(String name, int line){
        super();
        this.name = name;
        this.line = line;
    }

    @Override
    public String getKey() {
        return name + line;
    }

    @Override
    public String toString() {
        return name + " " + line;
    }
}
