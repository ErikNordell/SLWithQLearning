package com.example.erikn.qlearning;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by erikn on 2017-12-07.
 */

public class QLearning {

    HashMap<String, State> stateHashMap;
    HashMap<String, Action> actionHashMap;

    public QLearning(){
        stateHashMap = new HashMap<>();
        actionHashMap = new HashMap<>();
    }

    public void addState(State state){
        stateHashMap.put(state.getKey(),state);
    }
    public void addAction(Action action){
        actionHashMap.put(action.getKey(),action);
    }

    public void runQLearning(State goal){
        setStartScore(goal);
        calcQLearning(50);
    }

    private void calcQLearning(int x) {
        for(int i = 0 ; i < x ; i++){
            for(Action a: actionHashMap.values()){
                if(a.getStart().getBestConnectingAction()!=null){
                    a.setScore( a.getStart().getBestConnectingAction().getScore() * a.getPenalty());
                }
            }
        }
    }

    private void setStartScore(State goal) {
        for(Action a : actionHashMap.values()){
            a.setScore(0.0);
        }
        goal.setScoreForConnectingActions(100.0);
    }

    @Override
    public String toString() {
        String result = "";
        for(Action a: actionHashMap.values()){
            if(a.getStart().getBestConnectingAction()!=null){
                result += "Best connection to " + a.getStart().toString() + ": "+a.getStart().getBestConnectingAction().getScore() + "\n";
            }
        }
        return result;
    }

    public State getState(String key) {
        return stateHashMap.get(key);
    }




}
