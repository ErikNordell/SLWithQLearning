package com.example.erikn.qlearning;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by erikn on 2017-12-07.
 */

public abstract class State {
    ArrayList<Action> possibleActions;
    ArrayList<Action> connectingActions;


    public State(){
        possibleActions = new ArrayList<>();
        connectingActions = new ArrayList<>();
    }

    public Action getBestConnectingAction(){
        Action best = null;
        //Log.e("hej","hej");
        for(Action action : connectingActions){
            //Log.e(action.getKey(), " " + action.getScore());
            if(best ==null){
                best = action;
            }else{
                if(best.getScore()<action.getScore()){
                    best = action;
                }
            }
        }
        return best;
    }

    public void addPossibleAction(Action action){
        possibleActions.add(action);
    }

    public void addConnectingAction(Action action){
        connectingActions.add(action);
    }

    public abstract String getKey();


    public void setScoreForConnectingActions(double score) {
        for(Action action : connectingActions){
            Log.e("Score ", action.getKey() + " " +score);
            action.setScore(score);
        }
    }
}
