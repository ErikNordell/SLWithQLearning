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
        int count = 0;
        for(int i = 0 ; i < x ; i++){
            for(Action a: actionHashMap.values()){
                count++;
                if(a.getGoal().getBestPossibleAction()!=null){
                    a.setScore( a.getGoal().getBestPossibleAction().getScore() * a.getPenalty());
                }
            }
        }
        Log.e("Count ", "" + count);
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

    public String toStringState(String key){
        String result = "";
        State state = stateHashMap.get(key);
        if(state!=null){
            result += state.toString() + ": " + state.actionsToString();
        }
        return result;
    }


    public ArrayList<Action> getBestActionSequens(State start, State goal) {
        //Log.e("Kista11 ", "test");
        ArrayList<Action> bestWay = new ArrayList<>();
        boolean onRoute = true;
        while(onRoute){
            //Log.e("Kista11 ", start.toString());
            Action action = start.getBestPossibleAction();
            bestWay.add(action);
            start = action.getGoal();
            if(start.equals(goal)){
                onRoute = false;
            }
        }
        return bestWay;
    }

}
