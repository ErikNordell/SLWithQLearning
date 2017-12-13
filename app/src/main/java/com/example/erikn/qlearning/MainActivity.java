package com.example.erikn.qlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private QLearning qLearning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qLearning = new QLearning();

        createModel();

        qLearning.runQLearning(qLearning.getState("Rådhuset11"));

        Log.e("qLearning.toString", qLearning.toString());
        Log.e("Rådhuset11 ", qLearning.toStringState("Rådhuset11"));
        Log.e("Kista11 ", qLearning.toStringState("Kista11"));

        Log.e("Kista11 ", "test1");

        /*ArrayList<Action> bestActions = qLearning.getBestActionSequens(qLearning.getState("Kista11"), qLearning.getState("Rådhuset11"));
        Log.e("Kista11 ", "test2");

        for(Action action : bestActions){
            Log.e("Kista11 ", "test3");
            Log.e("Best Way", action.getStart().getKey() + " to "+action.getGoal().getKey() + ", Score: " + action.getScore());
        }
        */
    }


    private void createModel() {
        addBlueLine();
    }

    private void addBlueLine() {
        addBlueStations();
        addBlueConnections();
    }


    private void addBlueConnections() {
        String allInOne = ("Akalla.11.Husby.11.3!" +
                "Husby.11.Kista.11.3!" +
                "Kista.11.Hallonbergen.11.3!" +
                "Hallonbergen.11.Näckrosen.11.3!" +
                "Näckrosen.11.Solna Centrum.11.3!" +
                "Solna Centrum.11.Västra Skogen.11.3!" +
                "Västra Skogen.11.Stadshagen.11.3!" +
                "Stadshagen.11.Fridhemsplan.11.3!" +
                "Fridhemsplan.11.Rådhuset.11.3!" +
                "Rådhuset.11.T-Centralen.11.3!" +
                "T-Centralen.11.Kungsträdgården.11.3!" +
                "Hjulsta.10.Tensta.10.3!" +
                "Tensta.10.Rinkeby.10.3!" +
                "Rinkeby.10.Rissne.10.3!" +
                "Rissne.10.Duvbo.10.3!" +
                "Duvbo.10.Sundbybergs centrum.10.3!" +
                "Sundbybergs centrum.10.Solna strand.10.3!" +
                "Solna strand.10.Huvudsta.10.3!" +
                "Huvudsta.10.Västra Skogen.10.3!" +
                "Västra Skogen.10.Stadshagen.10.3!" +
                "Stadshagen.10.Fridhemsplan.10.3!" +
                "Fridhemsplan.10.Rådhuset.10.3!" +
                "Rådhuset.10.T-Centralen.10.3!" +
                "T-Centralen.10.Kungsträdgården.10.3!" +
                "Kungsträdgården.10.Kungsträdgården.11.3!" +
                "T-Centralen.11.T-Centralen.10.3!" +
                "Rådhuset.11.Rådhuset.10.3!" +
                "Fridhemsplan.11.Fridhemsplan.10.3!" +
                "Stadshagen.11.Stadshagen.10.3!" +
                "Västra Skogen.11.Västra Skogen.10.3!");

        String[] arrayUnFormatConnections = allInOne.split("!");
        for(String unFormatConnection : arrayUnFormatConnections){
            String[] connection = unFormatConnection.split("\\.");

            State state1 = qLearning.getState(connection[0]+connection[1]);
            State state2 = qLearning.getState(connection[2]+connection[3]);

            Connection to2 = new Connection(state1,state2, Integer.parseInt(connection[4]));
            Connection to1 = new Connection(state2,state1, Integer.parseInt(connection[4]));

            qLearning.addAction(to2);
            qLearning.addAction(to1);

            qLearning.getState(connection[0]+connection[1]).addConnectingAction(to1);
            qLearning.getState(connection[0]+connection[1]).addPossibleAction(to2);
            qLearning.getState(connection[2]+connection[3]).addConnectingAction(to2);
            qLearning.getState(connection[2]+connection[3]).addPossibleAction(to1);

        }
    }


    private void addBlueStations() {
        String allInOne = ("Akalla.11!" +
                "Husby.11!" +
                "Kista.11!" +
                "Hallonbergen.11!" +
                "Näckrosen.11!" +
                "Solna Centrum.11!" +
                "Västra Skogen.11!" +
                "Stadshagen.11!" +
                "Fridhemsplan.11!" +
                "Rådhuset.11!" +
                "T-Centralen.11!" +
                "Kungsträdgården.11!" +
                "Hjulsta.10!" +
                "Tensta.10!" +
                "Rinkeby.10!" +
                "Rissne.10!" +
                "Duvbo.10!" +
                "Sundbybergs centrum.10!" +
                "Solna strand.10!" +
                "Huvudsta.10!" +
                "Västra Skogen.10!" +
                "Stadshagen.10!" +
                "Fridhemsplan.10!" +
                "Rådhuset.10!" +
                "T-Centralen.10!" +
                "Kungsträdgården.10!");
        String[] arrayUnFormatStation = allInOne.split("!");
        for(String unFormatStation : arrayUnFormatStation){
            String[] station = unFormatStation.split("\\.");
            Station addStation = new Station(station[0],Integer.parseInt(station[1]));
            qLearning.addState(addStation);
            Connection addConnection = new Connection(addStation,addStation,0);
            qLearning.addAction(addConnection);
            addStation.addConnectingAction(addConnection);
            addStation.addPossibleAction(addConnection);
        }
    }


}
