package com.example.erikn.qlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private QLearning qLearning;
    Spinner spinnerFrom;
    Spinner spinnerTo;
    List<String> names;
    String[] stationName = {"Akalla11",
            "Husby11",
            "Kista11",
            "Hallonbergen11",
            "Näckrosen11",
            "Solna Centrum11",
            "Västra Skogen11",
            "Stadshagen11",
            "Fridhemsplan11",
            "Rådhuset11",
            "T-Centralen11",
            "Kungsträdgården11",
            "Hjulsta10",
            "Tensta10",
            "Rinkeby10",
            "Rissne10",
            "Duvbo10",
            "Sundbybergs centrum10",
            "Solna strand10",
            "Huvudsta10",
            "Västra Skogen10",
            "Stadshagen10",
            "Fridhemsplan10",
            "Rådhuset10",
            "T-Centralen10",
            "Kungsträdgården10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qLearning = new QLearning();

        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        spinnerTo.setOnItemSelectedListener(this);
        List<String> names = new ArrayList<>();
        for (String s : stationName) {
            names.add(s);
        }
        /*
        names = new ArrayList<String>();
        for (Station station : stationHashMap.values()) {
            names.add(station.getName()+ "  " + station.getLine());
        }
        */

        ArrayAdapter<String> fromAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        ArrayAdapter<String> toAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);


        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(fromAdapter);
        spinnerTo.setAdapter(toAdapter);



        createModel();

        fillSpinners();

        /*
        qLearning.runQLearning(qLearning.getState("Rådhuset11"));

        Log.e("qLearning.toString", qLearning.toString());

        Log.e("write all ", qLearning.toStringState("Rådhuset11"));

        Log.e("write all  ", qLearning.toStringState("Fridhemsplan11"));
        Log.e("write all  ", qLearning.toStringState("Stadshagen11"));
        Log.e("write all  ", qLearning.toStringState("Västra skogen11"));
        Log.e("write all ", qLearning.toStringState("Solna Centrum11"));
        Log.e("write all  ", qLearning.toStringState("Näckrosen11"));
        Log.e("write all  ", qLearning.toStringState("Hallonbergen11"));

        Log.e("write all  ", qLearning.toStringState("Kista11"));



        ArrayList<Action> bestActions = qLearning.getBestActionSequens(qLearning.getState("Kista11"), qLearning.getState("Rådhuset11"));


        String wayToGo = "";
        for(Action action : bestActions){
            wayToGo += "" + action.getStart().getKey() + " to "+action.getGoal().getKey() + "\n";
            Log.e("Best Way", action.getStart().getKey() + " to "+action.getGoal().getKey() + ", Score: " + action.getScore());
        }

        TextView mainText = (TextView) findViewById(R.id.mainText);
        mainText.setText(wayToGo);
        */

    }

    private void fillSpinners() {
        String[] stationName = {"Akalla11",
                "Husby11",
                "Kista11",
                "Hallonbergen11",
                "Näckrosen11",
                "Solna Centrum11",
                "Västra Skogen11",
                "Stadshagen11",
                "Fridhemsplan11",
                "Rådhuset11",
                "T-Centralen11",
                "Kungsträdgården11",
                "Hjulsta10",
                "Tensta10",
                "Rinkeby10",
                "Rissne10",
                "Duvbo10",
                "Sundbybergs centrum10",
                "Solna strand10",
                "Huvudsta10",
                "Västra Skogen10",
                "Stadshagen10",
                "Fridhemsplan10",
                "Rådhuset10",
                "T-Centralen10",
                "Kungsträdgården10"};

        List<String> namesList = new ArrayList<>();
        for (String s : stationName) {
            namesList.add(s);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, namesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerTo);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

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
        for (String unFormatConnection : arrayUnFormatConnections) {
            String[] connection = unFormatConnection.split("\\.");

            State state1 = qLearning.getState(connection[0] + connection[1]);
            State state2 = qLearning.getState(connection[2] + connection[3]);

            Connection to2 = new Connection(state1, state2, Integer.parseInt(connection[4]));
            Connection to1 = new Connection(state2, state1, Integer.parseInt(connection[4]));

            qLearning.addAction(to2);
            qLearning.addAction(to1);

            qLearning.getState(connection[0] + connection[1]).addConnectingAction(to1);
            qLearning.getState(connection[0] + connection[1]).addPossibleAction(to2);
            qLearning.getState(connection[2] + connection[3]).addConnectingAction(to2);
            qLearning.getState(connection[2] + connection[3]).addPossibleAction(to1);

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
        for (String unFormatStation : arrayUnFormatStation) {
            String[] station = unFormatStation.split("\\.");
            Station addStation = new Station(station[0], Integer.parseInt(station[1]));
            qLearning.addState(addStation);
            Connection addConnection = new Connection(addStation, addStation, 10);
            qLearning.addAction(addConnection);
            addStation.addConnectingAction(addConnection);
            addStation.addPossibleAction(addConnection);
        }
    }


   /* @Override
    public void onClick(View view) {
        Log.e("onClick","start");
        if(view.equals(findViewById(R.id.button))){
            Log.e("onClick","view==button");
            runQLearing();
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "selected : " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void buttonOnClick(View v) {
        Button button = (Button) v;
        ((Button) v).setText("Q-Learning activated");
        Log.e(" hej ", " hej ");
        runQLearing();
    }


    private void runQLearing() {
        Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        String to = (String) spinnerTo.getSelectedItem();
        String from = (String) spinnerFrom.getSelectedItem();

        qLearning.runQLearning(qLearning.getState(to));
        ArrayList<Action> bestActions = qLearning.getBestActionSequens(qLearning.getState(from), qLearning.getState(to));

        String wayToGo = "";
        for (Action action : bestActions) {
            wayToGo += "" + action.getStart().getKey() + " to " + action.getGoal().getKey() + "\n";
            Log.e("Best Way", action.getStart().getKey() + " to " + action.getGoal().getKey() + ", Score: " + action.getScore());
        }

        TextView mainText = (TextView) findViewById(R.id.mainText);
        mainText.setText(wayToGo);
        

    }


}
