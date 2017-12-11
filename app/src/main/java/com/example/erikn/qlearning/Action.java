package com.example.erikn.qlearning;

/**
 * Created by erikn on 2017-12-07.
 */

public abstract class Action {

    private State start;
    private State goal;
    private double score;

    public Action(State start, State goal){
        this.start = start;
        this.goal = goal;
        score = 0.0;
    }

    public void setScore(double score){this.score = score;}
    public double getScore() {return score;}

    public State getGoal() {return goal;}
    public State getStart() {return start;}

    public abstract String getKey();
    public final double getPenalty(){
        if(score == 100.0){
            return 1.0;
        }
        return calcPenalty();
    }

    protected abstract double calcPenalty();
}
