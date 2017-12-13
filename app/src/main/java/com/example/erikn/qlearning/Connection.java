package com.example.erikn.qlearning;


/**
 * Created by erikn on 2017-12-07.
 */

public class Connection extends Action {

    private double connectionTime;

    public  Connection(State start, State goal, int connectionTime){
        super(start, goal);
        this.connectionTime = connectionTime * 1.0;
    }

    @Override
    public String getKey() {
        return super.getStart().getKey()+super.getGoal().getKey();
    }

    @Override
    protected double calcPenalty() {

        //return 0.8;

        Station s = (Station) super.getStart();
        Station g = (Station) super.getGoal();
        double sensorValue = 1.0;

        if(s.getLine()!=g.getLine()){
            Sensor sensor = new Sensor();
            sensorValue = ((60.0-sensor.getValue())/(60.0));
        }


        return ((60.0-connectionTime)/(60.0));


    }

    @Override
    public String toString() {
        return super.getStart().toString() + " connecting to " + super.getGoal().toString();
    }
}
