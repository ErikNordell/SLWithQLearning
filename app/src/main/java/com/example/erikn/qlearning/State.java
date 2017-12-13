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
        for(Action action : connectingActions){
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

    public Action getBestPossibleAction(){
        Action best = null;
        for(Action action : possibleActions){
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


    public String actionsToString() {
        String result = "";
        /*for(Action action : connectingActions){
            result += action.getStart() + ", score: " + action.getScore() + ", ";
        }*/
        result += "\nConnecting to: ";
        for(Action action : possibleActions){
            result += action.getGoal() + ", score: " + action.getScore() + "\n";
        }
        return result;
    }
}
